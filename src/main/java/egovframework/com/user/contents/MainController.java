
package egovframework.com.user.contents;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.adm.login.service.LoginService;
import egovframework.com.adm.login.vo.Login;
import egovframework.com.adm.system.vo.Board;
import egovframework.com.file.service.FileService;
import egovframework.com.file.service.FileStorageService;
import egovframework.com.file.vo.AttachFile;
import egovframework.com.global.OfficeMessageSource;
import egovframework.com.global.annotation.SkipAuth;
import egovframework.com.global.authorization.SkipAuthLevel;
import egovframework.com.global.http.BaseApiMessage;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import egovframework.com.user.contents.service.MainService;
import io.swagger.annotations.ApiOperation;

/**
 * 컨텐츠 컨트롤러 클래스
 * 
 * @author iyson
 * @since 2022.12.28
 * @version 1.0
 * @see
 *
 */
@RestController
@RequestMapping("/board")
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private OfficeMessageSource officeMessageSource;

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private MainService mainService; 
    
    @Autowired
    private FileStorageService fileStorageService;  
    
    @Autowired
    private FileService fileService;
    
    //최신뉴스,관련법령,교육자료
    private static final String[] REST_ROOM = {"news", "laws", "datum"};
    
    
    /**
     * 공지사항조회메인
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectMainNoticeList.do")
    @ApiOperation(value = "공지사항", notes = "공지사항목록조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<List<Board>> selectMainNoticeList(HttpServletRequest request, @RequestBody Board params) {
		
		try {
			//공지사항조회
	        return new BaseResponse<List<Board>>(mainService.selectMainNoticeList(params));
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }     
    
    
    /**
     * Info조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectMainInfoList.do")
    @ApiOperation(value = "Info", notes = "Info목록조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<List<Board>> selectMainInfoList(HttpServletRequest request, @RequestBody Board params) {
		
		try {
			//Info조회
	        return new BaseResponse<List<Board>>(mainService.selectMainInfoList(params));
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }        
    
    
    /**
     * 공지사항조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectNoticeList.do")
    @ApiOperation(value = "공지사항", notes = "공지사항목록조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<List<Board>> selectNoticeList(HttpServletRequest request, @RequestBody Board params) {
		
		try {
			//공지사항조회
			List<Board> resultList = mainService.selectNoticeList(params);
			for(Board b : resultList) {
				AttachFile attachFile = new AttachFile();
				attachFile.setFileTarget(b.getSeqId());
				List<AttachFile> fList = fileService.selectFileAll(attachFile);			
				b.setFileList(fList);
			}
	        return new BaseResponse<List<Board>>(resultList);
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
    
    /**
     * 공지사항상세
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectNotice.do")
    @ApiOperation(value = "공지사항상세", notes = "공지사항상세조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Board> selectNotice(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		try {
			//공지사항조회
			mainService.updateNoticeHitCnt(params);
			Board result = mainService.selectNotice(params);
			AttachFile attachFile = new AttachFile();
			attachFile.setFileTarget(params.getSeqId());
			List<AttachFile> fList = fileService.selectFileAll(attachFile);
			result.setFileList(fList);
			
	        return new BaseResponse<Board>(result);
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
        
    


    /**
     * 공지사항등록
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/insertNotice.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "공지사항등록", notes = "공지사항등록")
    public BaseResponse<Board> insertNotice(
    		HttpServletRequest request,
            @RequestPart(value = "files", required = false) MultipartFile files,
            @RequestPart(value = "params", required = true) Board params)
            throws Exception {
		
    	Login login = loginService.getLoginInfo(request);
		if (login != null) {
			params.setInsertId(login.getUserId());
			params.setUserName(login.getUserNm());
		}    	
    	
        List<AttachFile> saveFiles = null;
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getUserName())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "UserName" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		
		//공지사항등록
		int result = mainService.insertNotice(params);
		
        if (files != null) {
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(files);
        	detail.setInsertId(params.getInsertId());
        	detail.setFileTarget(params.getSeqId());
            if (detail != null) {
                detail.setFileSn(i++);
                saveFiles.add(detail);
            }
            // 파일 정보 생성
            fileService.insertFile(saveFiles);            
        }
        List<AttachFile> resultFile = saveFiles != null ? saveFiles : new ArrayList<AttachFile>();
        Board resultDetail = mainService.selectNotice(params);
        resultDetail.setFileList(resultFile);
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), resultDetail);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), resultDetail);
		}        
    }  

    
    
    
    /**
     * 공지사항수정
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/updateNotice.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "공지사항수정", notes = "공지사항수정")
    public BaseResponse<Board> updateNotice(
    		HttpServletRequest request,
            @RequestPart(value = "files", required = false) MultipartFile files,
            @RequestPart(value = "params", required = true) Board params)
            throws Exception {
 
    	
    	Login login = loginService.getLoginInfo(request);
		if (login != null) {
			params.setInsertId(login.getUserId());
			params.setUserName(login.getUserNm());
		}    	
    	
        List<AttachFile> saveFiles = null;
        
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}        
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getUserName())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "UserName" + BaseApiMessage.REQUIRED.getCode());
		}	
		
		//공지사항등록
		int result = mainService.updateNotice(params);
		
        if (files != null) {
        	AttachFile af = new AttachFile();
        	af.setFileTarget(params.getSeqId());
        	fileService.deleteFileAll(af);
        	
        	List<AttachFile> fList = fileService.selectFileAll(af);
        	
        	if(fList!=null) {
        		for(AttachFile fd : fList) {
        			fileStorageService.deleteFile(fd);		
        		}
        	}
        	
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(files);
        	detail.setInsertId(params.getInsertId());
        	detail.setFileTarget(params.getSeqId());            
            if (detail != null) {
                detail.setFileSn(i++);
                saveFiles.add(detail);
            }
            
            // 파일 정보 생성
            fileService.insertFile(saveFiles);            
        }
        
        List<AttachFile> resultFile = saveFiles != null ? saveFiles : new ArrayList<AttachFile>();
        Board resultDetail = mainService.selectNotice(params);
        resultDetail.setFileList(resultFile);        
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), resultDetail);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), resultDetail);
		}        
    }  
    
    
    /**
     * 공지사항삭제
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/deleteNotice.do")
    @ApiOperation(value = "공지사항", notes = "공지사항삭제.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Integer> deleteNotice(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getSeqIdList())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "SeqIdList" + BaseApiMessage.REQUIRED.getCode());
		}				
		
		try {
			int result = 0;
			for(Long s : params.getSeqIdList()) {
				Board bs = new Board();
				bs.setSeqId(s);
				//공지사항삭제
				result = mainService.deleteNotice(bs);		
				
	        	AttachFile af = new AttachFile();
	        	af.setFileTarget(bs.getSeqId());
	        	fileService.deleteFileAll(af);
	        	
	        	List<AttachFile> fList = fileService.selectFileAll(af);
	        	
	        	if(fList!=null) {
	        		for(AttachFile fd : fList) {
	        			fileStorageService.deleteFile(fd);		
	        		}
	        	}					
			}
			
			if(result>0) {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_SUCCESS, BaseResponseCode.DELETE_SUCCESS.getMessage());
			}else {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_ERROR, BaseResponseCode.DELETE_ERROR.getMessage());
			}
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }      
    
    
    /**
     * FAQ조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectFAQList.do")
    @ApiOperation(value = "FAQ", notes = "FAQ목록조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<List<Board>> selectFAQList(HttpServletRequest request, @RequestBody Board params) {
		
		try {
			//FAQ조회
			List<Board> resultList = mainService.selectFAQList(params);
			for(Board b : resultList) {
				AttachFile attachFile = new AttachFile();
				attachFile.setFileTarget(b.getSeqId());
				List<AttachFile> fList = fileService.selectFileAll(attachFile);			
				b.setFileList(fList);
			}			
	        return new BaseResponse<List<Board>>(resultList);
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
    
    /**
     * FAQ상세
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectFAQ.do")
    @ApiOperation(value = "FAQ상세", notes = "FAQ상세조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Board> selectFAQ(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		try {
			//FAQ조회
			mainService.updateFAQHitCnt(params);
			Board result = mainService.selectFAQ(params);
			AttachFile attachFile = new AttachFile();
			attachFile.setFileTarget(params.getSeqId());
			List<AttachFile> fList = fileService.selectFileAll(attachFile);
			result.setFileList(fList);
			
	        return new BaseResponse<Board>(result);
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
        
    


    /**
     * FAQ등록
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/insertFAQ.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "FAQ등록", notes = "FAQ등록")
    public BaseResponse<Board> insertFAQ(
    		HttpServletRequest request,
            @RequestPart(value = "files", required = false) MultipartFile files,
            @RequestPart(value = "params", required = true) Board params)
            throws Exception {
		
    	Login login = loginService.getLoginInfo(request);
		if (login != null) {
			params.setInsertId(login.getUserId());
			params.setUserName(login.getUserNm());
		}    	
    	
        List<AttachFile> saveFiles = null;
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getUserName())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "UserName" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		
		//FAQ등록
		int result = mainService.insertFAQ(params);
		
        if (files != null) {
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(files);
        	detail.setInsertId(params.getInsertId());
        	detail.setFileTarget(params.getSeqId());
            if (detail != null) {
                detail.setFileSn(i++);
                saveFiles.add(detail);
            }
            // 파일 정보 생성
            fileService.insertFile(saveFiles);            
        }
        List<AttachFile> resultFile = saveFiles != null ? saveFiles : new ArrayList<AttachFile>();
        Board resultDetail = mainService.selectFAQ(params);
        resultDetail.setFileList(resultFile);          
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), resultDetail);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), resultDetail);
		}        
    }  

    
    
    
    /**
     * FAQ수정
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/updateFAQ.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "FAQ수정", notes = "FAQ수정")
    public BaseResponse<Board> updateFAQ(
    		HttpServletRequest request,
            @RequestPart(value = "files", required = false) MultipartFile files,
            @RequestPart(value = "params", required = true) Board params)
            throws Exception {
 
    	
    	Login login = loginService.getLoginInfo(request);
		if (login != null) {
			params.setInsertId(login.getUserId());
			params.setUserName(login.getUserNm());
		}    	
    	
        List<AttachFile> saveFiles = null;
        
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}        
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getUserName())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "UserName" + BaseApiMessage.REQUIRED.getCode());
		}	
		
		//FAQ등록
		int result = mainService.updateFAQ(params);
		
        if (files != null) {
        	AttachFile af = new AttachFile();
        	af.setFileTarget(params.getSeqId());
        	fileService.deleteFileAll(af);
        	
        	List<AttachFile> fList = fileService.selectFileAll(af);
        	
        	if(fList!=null) {
        		for(AttachFile fd : fList) {
        			fileStorageService.deleteFile(fd);		
        		}
        	}
        	
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(files);
        	detail.setInsertId(params.getInsertId());
        	detail.setFileTarget(params.getSeqId());            
            if (detail != null) {
                detail.setFileSn(i++);
                saveFiles.add(detail);
            }
            
            // 파일 정보 생성
            fileService.insertFile(saveFiles);            
        }
        
        List<AttachFile> resultFile = saveFiles != null ? saveFiles : new ArrayList<AttachFile>();
        Board resultDetail = mainService.selectFAQ(params);
        resultDetail.setFileList(resultFile);           
                
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), resultDetail);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), resultDetail);
		}        
    }  
    
    
    /**
     * FAQ삭제
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/deleteFAQ.do")
    @ApiOperation(value = "FAQ", notes = "FAQ삭제.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Integer> deleteFAQ(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getSeqIdList())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "SeqIdList" + BaseApiMessage.REQUIRED.getCode());
		}				
		
		try {
			
			int result = 0;
			for(Long s : params.getSeqIdList()) {
				Board bs = new Board();
				bs.setSeqId(s);
				//공지사항삭제
				result = mainService.deleteFAQ(bs);		
				
	        	AttachFile af = new AttachFile();
	        	af.setFileTarget(bs.getSeqId());
	        	fileService.deleteFileAll(af);
	        	
	        	List<AttachFile> fList = fileService.selectFileAll(af);
	        	
	        	if(fList!=null) {
	        		for(AttachFile fd : fList) {
	        			fileStorageService.deleteFile(fd);		
	        		}
	        	}					
			}			
			
			if(result>0) {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_SUCCESS, BaseResponseCode.DELETE_SUCCESS.getMessage());
			}else {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_ERROR, BaseResponseCode.DELETE_ERROR.getMessage());
			}
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }      
    
    /**
     * Info조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectInfoList.do")
    @ApiOperation(value = "Info", notes = "Info목록조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<List<Board>> selectInfoList(HttpServletRequest request, @RequestBody Board params) {
		
		try {
			//Info조회
			List<Board> resultList = mainService.selectInfoList(params);
			for(Board b : resultList) {
				AttachFile attachFile = new AttachFile();
				attachFile.setFileTarget(b.getSeqId());
				List<AttachFile> fList = fileService.selectFileAll(attachFile);			
				b.setFileList(fList);
			}			
	        return new BaseResponse<List<Board>>(resultList);	        
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
    
    /**
     * Info상세
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectInfo.do")
    @ApiOperation(value = "Info상세", notes = "Info상세조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Board> selectInfo(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		try {
			//Info조회
			mainService.updateInfoHitCnt(params);
			Board result = mainService.selectInfo(params);
			AttachFile attachFile = new AttachFile();
			attachFile.setFileTarget(params.getSeqId());
			List<AttachFile> fList = fileService.selectFileAll(attachFile);
			result.setFileList(fList);
			
	        return new BaseResponse<Board>(result);
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
        
    


    /**
     * Info등록
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/insertInfo.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "Info등록", notes = "Info등록")
    public BaseResponse<Board> insertInfo(
    		HttpServletRequest request,
            @RequestPart(value = "files", required = false) MultipartFile files,
            @RequestPart(value = "params", required = true) Board params)
            throws Exception {
		
    	Login login = loginService.getLoginInfo(request);
		if (login != null) {
			params.setInsertId(login.getUserId());
			params.setUserName(login.getUserNm());
		}    	
    	
        List<AttachFile> saveFiles = null;
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getUserName())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "UserName" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		
		//Info등록
		int result = mainService.insertInfo(params);
		
        if (files != null) {
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(files);
        	detail.setInsertId(params.getInsertId());
        	detail.setFileTarget(params.getSeqId());
            if (detail != null) {
                detail.setFileSn(i++);
                saveFiles.add(detail);
            }
            // 파일 정보 생성
            fileService.insertFile(saveFiles);            
        }
        List<AttachFile> resultFile = saveFiles != null ? saveFiles : new ArrayList<AttachFile>();
        Board resultDetail = mainService.selectInfo(params);
        resultDetail.setFileList(resultFile);           
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), resultDetail);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), resultDetail);
		}        
    }  

    
    
    
    /**
     * Info수정
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/updateInfo.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "Info수정", notes = "Info수정")
    public BaseResponse<Board> updateInfo(
    		HttpServletRequest request,
            @RequestPart(value = "files", required = false) MultipartFile files,
            @RequestPart(value = "params", required = true) Board params)
            throws Exception {
 
    	
    	Login login = loginService.getLoginInfo(request);
		if (login != null) {
			params.setInsertId(login.getUserId());
			params.setUserName(login.getUserNm());
		}    	
    	
        List<AttachFile> saveFiles = null;
        
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}        
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getUserName())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "UserName" + BaseApiMessage.REQUIRED.getCode());
		}	
		
		//Info등록
		int result = mainService.updateInfo(params);
		
        if (files != null) {
        	AttachFile af = new AttachFile();
        	af.setFileTarget(params.getSeqId());
        	fileService.deleteFileAll(af);
        	
        	List<AttachFile> fList = fileService.selectFileAll(af);
        	
        	if(fList!=null) {
        		for(AttachFile fd : fList) {
        			fileStorageService.deleteFile(fd);		
        		}
        	}
        	
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(files);
        	detail.setInsertId(params.getInsertId());
        	detail.setFileTarget(params.getSeqId());            
            if (detail != null) {
                detail.setFileSn(i++);
                saveFiles.add(detail);
            }
            
            // 파일 정보 생성
            fileService.insertFile(saveFiles);            
        }else {
        	AttachFile af = new AttachFile();
        	af.setFileTarget(params.getSeqId());
        	fileService.deleteFileAll(af);
        	
        	List<AttachFile> fList = fileService.selectFileAll(af);
        	
        	if(fList!=null) {
        		for(AttachFile fd : fList) {
        			fileStorageService.deleteFile(fd);		
        		}
        	}      	
        }
        
        List<AttachFile> resultFile = saveFiles != null ? saveFiles : new ArrayList<AttachFile>();
        Board resultDetail = mainService.selectInfo(params);
        resultDetail.setFileList(resultFile);           
        
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), resultDetail);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), resultDetail);
		}        
    }  
    
    
    /**
     * Info삭제
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/deleteInfo.do")
    @ApiOperation(value = "Info", notes = "Info삭제.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Integer> deleteInfo(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getSeqIdList())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "SeqIdList" + BaseApiMessage.REQUIRED.getCode());
		}				
		
		try {
			
			int result = 0;
			for(Long s : params.getSeqIdList()) {
				Board bs = new Board();
				bs.setSeqId(s);
				//공지사항삭제
				result = mainService.deleteInfo(bs);		
				
	        	AttachFile af = new AttachFile();
	        	af.setFileTarget(bs.getSeqId());
	        	fileService.deleteFileAll(af);
	        	
	        	List<AttachFile> fList = fileService.selectFileAll(af);
	        	
	        	if(fList!=null) {
	        		for(AttachFile fd : fList) {
	        			fileStorageService.deleteFile(fd);		
	        		}
	        	}					
			}					
			
			if(result>0) {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_SUCCESS, BaseResponseCode.DELETE_SUCCESS.getMessage());
			}else {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_ERROR, BaseResponseCode.DELETE_ERROR.getMessage());
			}
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }      
    

    
    
    
    /**
     * ReferenceRoom조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectReferenceRoomList.do")
    @ApiOperation(value = "ReferenceRoom", notes = "ReferenceRoom목록조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<List<Board>> selectReferenceRoomList(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getPath())){				
			return new BaseResponse<List<Board>>(BaseResponseCode.PARAMS_ERROR, "Path" + BaseApiMessage.REQUIRED.getCode());
		}	    	
    	
		try {
			//ReferenceRoom조회
			List<Board> resultList = new ArrayList<Board>(); 
			if(params.getPath().equals(REST_ROOM[0])) {
				resultList = mainService.selectNewsList(params);
			}else if(params.getPath().equals(REST_ROOM[1])){
				resultList = mainService.selectLawsList(params);
			}else if(params.getPath().equals(REST_ROOM[2])){
				resultList = mainService.selectDatumList(params);
			}
			
			for(Board b : resultList) {
				AttachFile attachFile = new AttachFile();
				attachFile.setFileTarget(b.getSeqId());
				attachFile.setMemo(params.getPath());
				List<AttachFile> fList = fileService.selectFileAll(attachFile);			
				b.setFileList(fList);
			}			
	        return new BaseResponse<List<Board>>(resultList);	        
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
    
    /**
     * ReferenceRoom상세
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectReferenceRoom.do")
    @ApiOperation(value = "ReferenceRoom상세", notes = "ReferenceRoom상세조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Board> selectReferenceRoom(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getPath())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Path" + BaseApiMessage.REQUIRED.getCode());
		}	    	
    	    	
    	
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		try {
			//ReferenceRoom조회
			Board result = new Board();
			if(params.getPath().equals(REST_ROOM[0])) {
				mainService.updateNewsHitCnt(params);
				result = mainService.selectNews(params);
			}else if(params.getPath().equals(REST_ROOM[1])){
				mainService.updateLawsHitCnt(params);
				result = mainService.selectLaws(params);
			}else if(params.getPath().equals(REST_ROOM[2])){
				mainService.updateDatumHitCnt(params);
				result = mainService.selectDatum(params);
			}			
			
			AttachFile attachFile = new AttachFile();
			attachFile.setFileTarget(params.getSeqId());
			attachFile.setMemo(params.getPath());
			List<AttachFile> fList = fileService.selectFileAll(attachFile);
			result.setFileList(fList);
			
	        return new BaseResponse<Board>(result);
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
        
    


    /**
     * ReferenceRoom등록
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/insertReferenceRoom.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "ReferenceRoom등록", notes = "ReferenceRoom등록")
    public BaseResponse<Board> insertReferenceRoom(
    		HttpServletRequest request,
            @RequestPart(value = "files", required = false) MultipartFile files,
            @RequestPart(value = "params", required = true) Board params)
            throws Exception {
		
		if(StringUtils.isEmpty(params.getPath())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Path" + BaseApiMessage.REQUIRED.getCode());
		}	    	
    	    	
    	
    	Login login = loginService.getLoginInfo(request);
		if (login != null) {
			params.setInsertId(login.getUserId());
			params.setUserName(login.getUserNm());
		}    	
    	
        List<AttachFile> saveFiles = null;
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getUserName())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "UserName" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		
		//ReferenceRoom등록
		int result = 0;
		
		if(params.getPath().equals(REST_ROOM[0])) {
			result = mainService.insertNews(params);
		}else if(params.getPath().equals(REST_ROOM[1])){
			result = mainService.insertLaws(params);
		}else if(params.getPath().equals(REST_ROOM[2])){
			result = mainService.insertDatum(params);
		}		
		
        if (files != null) {
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(files);
        	detail.setInsertId(params.getInsertId());
        	detail.setFileTarget(params.getSeqId());
            if (detail != null) {
                detail.setFileSn(i++);
                detail.setMemo(params.getPath());
                saveFiles.add(detail);
            }
            // 파일 정보 생성
            fileService.insertFile(saveFiles);            
        }
        List<AttachFile> resultFile = saveFiles != null ? saveFiles : new ArrayList<AttachFile>();
		Board resultDetail = new Board();
		if(params.getPath().equals(REST_ROOM[0])) {
			resultDetail = mainService.selectNews(params);
		}else if(params.getPath().equals(REST_ROOM[1])){
			resultDetail = mainService.selectLaws(params);
		}else if(params.getPath().equals(REST_ROOM[2])){
			resultDetail = mainService.selectDatum(params);
		}	        
        
        resultDetail.setFileList(resultFile);           
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), resultDetail);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), resultDetail);
		}        
    }  

    
    
    
    /**
     * ReferenceRoom수정
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/updateReferenceRoom.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "ReferenceRoom수정", notes = "ReferenceRoom수정")
    public BaseResponse<Board> updateReferenceRoom(
    		HttpServletRequest request,
            @RequestPart(value = "files", required = false) MultipartFile files,
            @RequestPart(value = "params", required = true) Board params)
            throws Exception {
 
		if(StringUtils.isEmpty(params.getPath())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Path" + BaseApiMessage.REQUIRED.getCode());
		}	    	
    	
    	Login login = loginService.getLoginInfo(request);
		if (login != null) {
			params.setInsertId(login.getUserId());
			params.setUserName(login.getUserNm());
		}    	
    	
        List<AttachFile> saveFiles = null;
        
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}        
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getUserName())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "UserName" + BaseApiMessage.REQUIRED.getCode());
		}	
		
		//ReferenceRoom수정
		int result = 0;
		
		if(params.getPath().equals(REST_ROOM[0])) {
			result = mainService.updateNews(params);
		}else if(params.getPath().equals(REST_ROOM[1])){
			result = mainService.updateLaws(params);
		}else if(params.getPath().equals(REST_ROOM[2])){
			result = mainService.updateDatum(params);
		}
		
		
        if (files != null) {
        	AttachFile af = new AttachFile();
        	af.setFileTarget(params.getSeqId());
        	fileService.deleteFileAll(af);
        	
        	List<AttachFile> fList = fileService.selectFileAll(af);
        	
        	if(fList!=null) {
        		for(AttachFile fd : fList) {
        			fileStorageService.deleteFile(fd);		
        		}
        	}
        	
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(files);
        	detail.setInsertId(params.getInsertId());
        	detail.setFileTarget(params.getSeqId());            
            if (detail != null) {
                detail.setFileSn(i++);
                detail.setMemo(params.getPath());
                saveFiles.add(detail);
            }
            
            // 파일 정보 생성
            fileService.insertFile(saveFiles);            
        }
        
        List<AttachFile> resultFile = saveFiles != null ? saveFiles : new ArrayList<AttachFile>();
		Board resultDetail = new Board();
		if(params.getPath().equals(REST_ROOM[0])) {
			resultDetail = mainService.selectNews(params);
		}else if(params.getPath().equals(REST_ROOM[1])){
			resultDetail = mainService.selectLaws(params);
		}else if(params.getPath().equals(REST_ROOM[2])){
			resultDetail = mainService.selectDatum(params);
		}	
        resultDetail.setFileList(resultFile);           
        
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), resultDetail);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), resultDetail);
		}        
    }  
    
    
    /**
     * ReferenceRoom삭제
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/deleteReferenceRoom.do")
    @ApiOperation(value = "ReferenceRoom", notes = "ReferenceRoom삭제.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Integer> deleteReferenceRoom(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getPath())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "Path" + BaseApiMessage.REQUIRED.getCode());
		}	    	
    	
		if(StringUtils.isEmpty(params.getSeqIdList())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "SeqIdList" + BaseApiMessage.REQUIRED.getCode());
		}				
		
		try {
			
			int result = 0;
			for(Long s : params.getSeqIdList()) {
				Board bs = new Board();
				bs.setSeqId(s);
				params.setSeqId(s);
				if(params.getPath().equals(REST_ROOM[0])) {
					result = mainService.deleteNews(params);
				}else if(params.getPath().equals(REST_ROOM[1])){
					result = mainService.deleteLaws(params);
				}else if(params.getPath().equals(REST_ROOM[2])){
					result = mainService.deleteDatum(params);
				}
				
	        	AttachFile af = new AttachFile();
	        	af.setFileTarget(bs.getSeqId());
	        	af.setMemo(params.getPath());
	        	fileService.deleteFileAll(af);
	        	
	        	List<AttachFile> fList = fileService.selectFileAll(af);
	        	
	        	if(fList!=null) {
	        		for(AttachFile fd : fList) {
	        			fileStorageService.deleteFile(fd);		
	        		}
	        	}					
			}					
			
			if(result>0) {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_SUCCESS, BaseResponseCode.DELETE_SUCCESS.getMessage());
			}else {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_ERROR, BaseResponseCode.DELETE_ERROR.getMessage());
			}
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }          
    
    
    /**
     * deleteFile삭제
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/deleteFile.do")
    @ApiOperation(value = "deleteFile", notes = "deleteFile삭제.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Integer> deleteFile(HttpServletRequest request, @RequestBody Board params) {
		
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		if(StringUtils.isEmpty(params.getAttachFileId())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "AttachFileId" + BaseApiMessage.REQUIRED.getCode());
		}				
		
		try {
			
			int result = 0;
        	AttachFile af = new AttachFile();
        	af.setAttachFileId(params.getAttachFileId());
        	af.setMemo(params.getPath());
        	af.setFileTarget(params.getSeqId());
        	
        	fileService.deleteFile(af);
        	AttachFile fList = fileService.selectFile(af);
   			fileStorageService.deleteFile(fList);		
			
			if(result>0) {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_SUCCESS, BaseResponseCode.DELETE_SUCCESS.getMessage());
			}else {
				return new BaseResponse<Integer>(BaseResponseCode.DELETE_ERROR, BaseResponseCode.DELETE_ERROR.getMessage());
			}
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }         
}
