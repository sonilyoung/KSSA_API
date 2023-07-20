package egovframework.com.global;

import java.util.Locale;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * 메시지 리소스 사용을 위한 MessageSource 인터페이스 및 ReloadableResourceBundleMessageSource 클래스의 구현체
 * 
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *
 *      </pre>
 */

public class OfficeMessageSource {

    private static MessageSourceAccessor msAcc = null;

    public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
        OfficeMessageSource.msAcc = msAcc;
    }

    /**
     * 정의된 메세지 조회
     * 
     * @param code - 메세지 코드
     * @return String
     */
    public static String getMessage(String code) {
        return msAcc.getMessage(code, Locale.getDefault());
    }

    /**
     * 정의된 메세지 조회
     * 
     * @param code - 메세지 코드
     * @param args - 메세지 안에 들어갈 변수
     * @return String
     */
    public static String getMessage(String code, Object[] objs) {
        return msAcc.getMessage(code, objs, Locale.getDefault());
    }
    

}
