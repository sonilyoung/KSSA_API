package egovframework.com.global.http.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import egovframework.com.global.exception.CustomBaseException;
import egovframework.com.global.http.BaseResponse;

/**
 * 공통 Exception 컨트롤러 어드바이스
 * 
 * @author jkj0411
 *
 */
@ControllerAdvice
public class BaseControllerAdvice {
    @Autowired
    private MessageSource messageResource;

    /**
     * 응답 오류발생시 resource 메세지를 읽어 리턴
     * 
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = {CustomBaseException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private BaseResponse<?> handlerBaseException(CustomBaseException e, WebRequest request) {
        if (e.getArgs() != null) {
            return new BaseResponse<String>(e.getResponseCode(),
                    messageResource.getMessage(e.getResponseCode().name(), e.getArgs(), null));
        } else {
            return new BaseResponse<String>(e.getResponseCode(), e.getResponseCode().getMessage());
        }
    }
}
