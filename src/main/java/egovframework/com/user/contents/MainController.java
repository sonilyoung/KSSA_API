
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
	        return new BaseResponse<List<Board>>(mainService.selectNoticeList(params));
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
        params.setFileList(resultFile);
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), params);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), params);
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
        params.setFileList(resultFile);
        
		if(result > 0) {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage(), params);
		}else {
			return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage(), params);
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
		
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}				
		
		try {
			//공지사항삭제
			int result = mainService.deleteNotice(params);
			
        	AttachFile af = new AttachFile();
        	af.setFileTarget(params.getSeqId());
        	fileService.deleteFileAll(af);
        	
        	List<AttachFile> fList = fileService.selectFileAll(af);
        	
        	if(fList!=null) {
        		for(AttachFile fd : fList) {
        			fileStorageService.deleteFile(fd);		
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

}
