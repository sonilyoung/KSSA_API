
package egovframework.com.user.calendar;

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
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.adm.login.service.LoginService;
import egovframework.com.adm.login.vo.Login;
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
import egovframework.com.user.calendar.service.CalendarService;
import egovframework.com.user.calendar.vo.Calendar;
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
@RequestMapping("/schedule")
public class CalendarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarController.class);

    private OfficeMessageSource officeMessageSource;

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private CalendarService calendarService; 
    
    
    /**
     * 달력조회메인
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectCalendarList.do")
    @ApiOperation(value = "달력", notes = "달력목록조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<List<Calendar>> selectCalendarList(HttpServletRequest request, @RequestBody Calendar params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}else {
			params.setUserId(login.getUserId());
			params.setInsertId(login.getUserId());
			params.setCompany(login.getCompany());
		}  
		
		try {
			//달력조회
	        return new BaseResponse<List<Calendar>>(calendarService.selectCalendarList(params));
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }     
    
    
    /**
     * 달력상세
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/selectCalendar.do")
    @ApiOperation(value = "달력상세", notes = "달력상세조회.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Calendar> selectCalendar(HttpServletRequest request, @RequestBody Calendar params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}else {
			params.setUserId(login.getUserId());
			params.setInsertId(login.getUserId());
			params.setCompany(login.getCompany());
		}  		
    	
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}			
		
		try {
			Calendar result = calendarService.selectCalendar(params);
	        return new BaseResponse<Calendar>(result);
        } catch (Exception e) {
        	LOGGER.error("error:", e);
            throw new BaseException(BaseResponseCode.UNKONWN_ERROR, e.getMessage());
        }
    }    
        
    


    /**
     * 달력등록
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/insertCalendar.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "달력등록", notes = "달력등록")
    public BaseResponse<Calendar> insertCalendar(
    		HttpServletRequest request, @RequestBody Calendar params)throws Exception {
		
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}else {
			params.setUserId(login.getUserId());
			params.setInsertId(login.getUserId());
			params.setCompany(login.getCompany());
		}    	
		
		if(StringUtils.isEmpty(params.getCategory())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "Category" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getEduStartDate())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "EduStartDate" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getEduEndDate())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "EduEndDate" + BaseApiMessage.REQUIRED.getCode());
		}		
		
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		
		//달력등록
		int result = calendarService.insertCalendar(params);
        
		if(result > 0) {
			return new BaseResponse<Calendar>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage());
		}else {
			return new BaseResponse<Calendar>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage());
		}        
    }  

    
    
    
    /**
     * 달력수정
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="/updateCalendar.do" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    @ApiOperation(value = "달력수정", notes = "달력수정")
    public BaseResponse<Calendar> updateCalendar(
    		HttpServletRequest request,@RequestBody  Calendar params)
            throws Exception {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}else {
			params.setUserId(login.getUserId());
			params.setInsertId(login.getUserId());
			params.setCompany(login.getCompany());
		}    	
    	
		if(StringUtils.isEmpty(params.getSeqId())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "SeqId" + BaseApiMessage.REQUIRED.getCode());
		}        
        
		if(StringUtils.isEmpty(params.getTitle())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "Title" + BaseApiMessage.REQUIRED.getCode());
		}
		
		if(StringUtils.isEmpty(params.getContents())){				
			return new BaseResponse<Calendar>(BaseResponseCode.PARAMS_ERROR, "Contents" + BaseApiMessage.REQUIRED.getCode());
		}
		
		//달력등록
		int result = calendarService.updateCalendar(params);
		
		if(result > 0) {
			return new BaseResponse<Calendar>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage());
		}else {
			return new BaseResponse<Calendar>(BaseResponseCode.SAVE_ERROR, BaseResponseCode.SAVE_ERROR.getMessage());
		}        
    }  
    
    
    /**
     * 달력삭제
     * 
     * @param param
     * @return Company
     */
    @PostMapping("/deleteCalendar.do")
    @ApiOperation(value = "달력", notes = "달력삭제.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_ALL)
    public BaseResponse<Integer> deleteCalendar(HttpServletRequest request, @RequestBody Calendar params) {
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}else {
			params.setUserId(login.getUserId());
			params.setInsertId(login.getUserId());
			params.setCompany(login.getCompany());
		}  
		
		if(StringUtils.isEmpty(params.getSeqIdList())){				
			return new BaseResponse<Integer>(BaseResponseCode.PARAMS_ERROR, "SeqIdList" + BaseApiMessage.REQUIRED.getCode());
		}				
		
		try {
			int result = calendarService.deleteCalendar(params);		
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
