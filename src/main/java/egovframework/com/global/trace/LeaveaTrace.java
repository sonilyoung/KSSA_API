package egovframework.com.global.trace;

import java.util.Locale;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import egovframework.com.global.trace.manager.TraceHandlerService;


public class LeaveaTrace {
    @Resource(name = "messageSource")
    private MessageSource messageSource;

    private TraceHandlerService[] traceHandlerServices;

    private PathMatcher pm = new AntPathMatcher();

    /**
     * setTraceHandlerServices.
     * 
     * @param traceHandlerServices 실행하고자 하는 Handler 를 가진 Manager서비스
     */
    public void setTraceHandlerServices(TraceHandlerService[] traceHandlerServices) {
        this.traceHandlerServices = traceHandlerServices;
    }

    /**
     * 등록된 TraceHandlerService 갯수를 리턴한다.
     * 
     * @return TraceHandlerService 갯수
     */
    public int countOfTheTraceHandlerService() {
        return (traceHandlerServices != null) ? traceHandlerServices.length : 0;
    }

    /**
     * trace 메소드.
     * 
     * @param msgKey 메세지를 가져오기 위한 메세지키값
     * @param clazz leaveaTrace 실행 위치에 클래스 정보
     */
    public void trace(String msgKey, Class<?> clazz) {
        this.trace(msgKey, new String[] {}, clazz);
    }

    /**
     * trace 메소드.
     * 
     * @param msgKey 메세지를 가져오기 위한 메세지키값
     * @param msgArgs 메세지 값의 변수를 치환하기 위한 값리스트
     * @param clazz leaveaTrace 실행 위치에 클래스 정보
     */
    public void trace(String msgKey, String[] msgArgs, Class<?> clazz) {
        this.trace(msgKey, msgArgs, null, clazz);
    }

    /**
     * trace 메소드.
     * 
     * @param msgKey 메세지를 가져오기 위한 메세지키값
     * @param msgArgs 메세지 값의 변수를 치환하기 위한 값리스트
     * @param clazz leaveaTrace 실행 위치에 클래스 정보
     */
    public void trace(String msgKey, String[] msgArgs, Locale locale, Class<?> clazz) {
        this.trace(clazz, messageSource, msgKey, msgArgs, locale, null);
    }

    /**
     * trace 메소드.
     * 
     * @param clazz leaveaTrace 실행 위치에 클래스 정보
     * @param messageKey 메세지를 가져오기 위한 메세지키값
     * @param messageParameters 메세지 값의 변수를 치환하기 위한 값리스트
     * @param locale 국가/언어지정
     */
    public void trace(Class<?> clazz, MessageSource messageSource, String messageKey,
            Object[] messageParameters, Locale locale) {
        this.trace(clazz, messageSource, messageKey, messageParameters, locale, null);
    }

    /**
     * trace 메소드.
     * 
     * @param clazz leaveaTrace 실행 위치에 클래스 정보
     * @param messageKey 메세지를 가져오기 위한 메세지키값
     * @param messageParameters 메세지 값의 변수를 치환하기 위한 값리스트
     * @param locale 국가/언어지정
     * @param log 로그객체지정
     */
    public void trace(Class<?> clazz, MessageSource messageSource, String messageKey,
            Object[] messageParameters, Locale locale, Logger log) {

        String message = messageSource.getMessage(messageKey, messageParameters, null, locale);

        if (log != null) {
            log.info(" LeaveaTrace.trace() this.message =>" + message);
        }

        if (traceHandlerServices == null) {
            return;
        }

        for (TraceHandlerService traceHandlerService : traceHandlerServices) {
            if (traceHandlerService.hasReqExpMatcher()) {
                traceHandlerService.setReqExpMatcher(pm);
            }
            traceHandlerService.setPackageName(clazz.getCanonicalName());
            traceHandlerService.trace(clazz, message);
        }
    }
}
