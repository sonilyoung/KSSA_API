package egovframework.com.global;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import egovframework.com.global.exception.BizException;
import egovframework.com.global.trace.LeaveaTrace;


public abstract class AbstractServiceImpl {

    protected Logger Logger = LoggerFactory.getLogger(AbstractServiceImpl.class);

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    private LeaveaTrace traceObj;

    /**
     * BizException 발생을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     * @return Exception BizException 객체
     */
    protected Exception processException(final String msgKey) {
        return processException(msgKey, new String[] {});
    }

    /**
     * BizException 발생을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
     * @return Exception BizException 객체
     */
    protected Exception processException(final String msgKey, Exception e) {
        return processException(msgKey, new String[] {}, e);
    }

    /**
     * BizException 발생을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
     * @return Exception BizException 객체
     */
    protected Exception processException(final String msgKey, final String[] msgArgs) {
        return processException(msgKey, msgArgs, null);
    }

    /**
     * BizException 발생을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
     * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
     * @return Exception BizException 객체
     */
    protected Exception processException(final String msgKey, final String[] msgArgs,
            final Exception e) {
        return processException(msgKey, msgArgs, e, LocaleContextHolder.getLocale());
    }

    /**
     * BizException 발생을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
     * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
     * @param locale 명시적 국가/언어지정
     * @return Exception BizException 객체
     */
    protected Exception processException(final String msgKey, final String[] msgArgs,
            final Exception e, Locale locale) {
        return processException(msgKey, msgArgs, e, locale, null);
    }

    /**
     * BizException 발생을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
     * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
     * @param locale 명시적 국가/언어지정
     * @param exceptionCreator 외부에서 별도의 Exception 생성기 지정
     * @return Exception BizException 객체
     */
    protected Exception processException(final String msgKey, final String[] msgArgs,
            final Exception e, final Locale locale, ExceptionCreator exceptionCreator) {
        ExceptionCreator eC = null;
        if (exceptionCreator == null) {
            eC = new ExceptionCreator() {
                public Exception createBizException(MessageSource messageSource) {
                    return new BizException(messageSource, msgKey, msgArgs, locale, e);
                }
            };
        } else {
            eC = exceptionCreator;
        }

        return eC.createBizException(messageSource);
    }

    protected interface ExceptionCreator {
        Exception createBizException(MessageSource messageSource);
    }

    /**
     * Exception 발생없이 후처리로직 실행을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     */
    protected void leaveaTrace(String msgKey) {
        leaveaTrace(msgKey, new String[] {});
    }

    /**
     * Exception 발생없이 후처리로직 실행을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
     */
    protected void leaveaTrace(String msgKey, String[] msgArgs) {
        leaveaTrace(msgKey, msgArgs, null);
    }

    /**
     * Exception 발생없이 후처리로직 실행을 위한 메소드.
     * 
     * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
     * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
     * @param locale 명시적 국가/언어지정
     */
    protected void leaveaTrace(String msgKey, String[] msgArgs, Locale locale) {
        traceObj.trace(this.getClass(), messageSource, msgKey, msgArgs, locale, Logger);
    }
}
