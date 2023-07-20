
package egovframework.com.test;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.global.annotation.SkipAuth;
import egovframework.com.global.authorization.SkipAuthLevel;
import egovframework.com.global.common.GlobalsProperties;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import io.swagger.annotations.ApiOperation;

/**
 * 테스트
 */
@RestController
@RequestMapping("/test")
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
    /*파일업로드 저장경로*/
    public static final String FILE_UPLOAD_PATH = GlobalsProperties.getProperty("file.upload.path");
    
    public static final int XBT_TARGET_NAME = 5500;

    
	@GetMapping("/test.do")
	@ApiOperation(value = "test", notes = "test")
	@SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
	public BaseResponse<Integer> index(HttpServletRequest request, @RequestParam(required = false) String params) {

		return new BaseResponse<Integer>(BaseResponseCode.SUCCESS, BaseResponseCode.SUCCESS.getMessage());
	}

}
