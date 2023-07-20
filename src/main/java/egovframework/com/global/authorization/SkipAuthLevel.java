package egovframework.com.global.authorization;

import egovframework.com.global.annotation.SkipAuth;

/**
 * {@link SkipAuth} Annotation 과 함께 사용할 Skip Level 정의
 * 
 * @fileName : SkipAuthLevel.java
 * @author : YeongJun Lee
 * @date : 2022.06.08
 */
public enum SkipAuthLevel {
    /**
     * 인증(로그인) : 체크 안함, 권한 : 체크 안함
     */
    SKIP_ALL("SKIP_ALL"),

    /**
     * 인증(로그인) : 체크, 권한 : 체크 안함
     */
    SKIP_AUTHORIZATION("SKIP_AUTHORIZATION"),

    /**
     * 인증(로그인) : 체크, 권한 : 체크
     */
    SKIP_NONE("SKIP_NONE");

    private final String value;

    SkipAuthLevel(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
