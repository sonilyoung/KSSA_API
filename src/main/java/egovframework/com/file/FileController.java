
package egovframework.com.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.adm.login.service.LoginService;
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
import egovframework.com.global.util.FileUtils;
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
@RequestMapping("/file")
@Api(tags = "Login Management API")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    private OfficeMessageSource officeMessageSource;

    @Autowired
    private LoginService loginService;
    
    
    @Autowired
    private FileStorageService fileStorageService;
    
    
    @Autowired
    private FileService fileService;    

    
    /**
     * 파일 다운
     * 
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/fileDown", method = RequestMethod.GET)
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_AUTHORIZATION)
    @ApiOperation(value = "file Down", notes = "file Down")	
	public void fileDown(
			@RequestPart(value = "params", required = true) AttachFile params
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		if(StringUtils.isEmpty(params.getAttachFileId())){
	           throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
	                    new String[] {"atchFileId", "atchFileId 파일아이디"});
		}
			
		if(StringUtils.isEmpty(params.getFileSn())){				
           throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"fileSn", "fileSn 파일순서"});
		}			
		
		AttachFile attachDetail = fileService.selectFile(params);
		request.setAttribute("downFile", attachDetail.getFilePath() + File.separator +  attachDetail.getSaveFileName());
		request.setAttribute("orginFile",  attachDetail.getOriginalFileName());
		request.setAttribute("fileSize",  attachDetail.getFileSize());
		FileUtils.downFile(request, response);		
	}     
	
    /** 
     * 업로드 파일 삭제
     */
    @PostMapping("/deleteFile")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_AUTHORIZATION)
    @ApiOperation(value = "file delete", notes = "file delete")
    public BaseResponse<Integer> deleteFile(
    		@RequestPart(value = "params", required = true) AttachFile params
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
    	
		if(StringUtils.isEmpty(params.getAttachFileId())){
	           throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
	                    new String[] {"atchFileId", "atchFileId 파일아이디"});
		}
			
		if(StringUtils.isEmpty(params.getFileSn())){				
        throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                 new String[] {"fileSn", "fileSn 파일순서"});
		}		

		AttachFile attachDetail = fileService.selectFile(params);
		int iRslt = 0;		
		if(attachDetail!=null) {
            // DB 정보 삭제
            iRslt = fileService.deleteFile(params);
		}
		
		if(iRslt > 0) {
			return new BaseResponse<Integer>(BaseResponseCode.SUCCESS);
		}else{
			return new BaseResponse<Integer>(BaseResponseCode.DELETE_ERROR);	
		}
    } 
    	
	
    
}
