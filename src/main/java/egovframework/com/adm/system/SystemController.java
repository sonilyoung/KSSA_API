
package egovframework.com.adm.system;

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
import egovframework.com.adm.system.service.SystemService;
import egovframework.com.adm.system.vo.Board;
import egovframework.com.file.service.FileStorageService;
import egovframework.com.file.vo.AttachFile;
import egovframework.com.global.annotation.SkipAuth;
import egovframework.com.global.authorization.SkipAuthLevel;
import egovframework.com.global.http.BaseApiMessage;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
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
@RequestMapping("/adm")
@Api(tags = "Notice Management API")
public class SystemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private SystemService systemService;    
    
    @Autowired
    private FileStorageService fileStorageService;    
    				
    /**
     * 공지사항조회
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectNoticeList.do")
    @ApiOperation(value = "공지사항", notes = "공지사항목록조회.")
    public BaseResponse<List<Board>> selectNoticeList(HttpServletRequest request, @RequestBody Board params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		
		try {
			//공지사항조회
	        return new BaseResponse<List<Board>>(systemService.selectNoticeList(params));
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
    public BaseResponse<Board> selectNotice(HttpServletRequest request, @RequestBody Board params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		try {
			//공지사항조회
	        return new BaseResponse<Board>(systemService.selectNotice(params));
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
    @ApiOperation(value = "xray 이미지 업로드", notes = "공지사항등록")
    public BaseResponse<Board> insertNotice(
    		HttpServletRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart(value = "params", required = false) Board params)
            throws Exception {
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
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
		params.setUpdateId(login.getUserId());
		int result = systemService.insertNotice(params);
		
        if (file != null) {
        	int i = 1;
            saveFiles = new ArrayList<>();
            // 파일 생성
        	AttachFile detail = fileStorageService.createFile(file);
            
            if (detail != null) {
                detail.setFileSn(i++);
                saveFiles.add(detail);
            }
        }
        // 파일 정보 생성
        //fileStorageService.saveFiles(saveFiles, deleteFiles);

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
     * @param param
     * @return Company
     */
    @ApiOperation(value = "공지사항", notes = "공지사항수정.")
    @PostMapping(value="/updateNotice.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Board> updateNotice(
    		HttpServletRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart(value = "params", required = false) Board params)
            throws Exception {    
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		

		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Board>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		
		try {
			//공지사항등록
			params.setUpdateId(login.getUserId());
			int result = systemService.updateNotice(params);
			
			if(result>0) {
				return new BaseResponse<Board>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage());
			}else {
				return new BaseResponse<Board>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage());
			}
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
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
    public BaseResponse<Integer> deleteNotice(HttpServletRequest request, @RequestBody Board params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}				
		
		try {
			//공지사항삭제
			int result = systemService.deleteNotice(params);
			
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
