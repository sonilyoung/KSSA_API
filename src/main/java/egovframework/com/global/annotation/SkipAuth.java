package egovframework.com.global.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import egovframework.com.global.authorization.SkipAuthLevel;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * 인증(로그인) 및 권한 체크를 Skip 하기 위한 Annotation
 * 
 * @fileName : SkipAuth.java
 * @author : YeongJun Lee
 * @date : 2022.06.08
 */
public @interface SkipAuth {
    /**
     * Skip level.<br>
     * Default {@link SkipAuthLevel#SKIP_ALL}.
     */
    SkipAuthLevel skipAuthLevel() default SkipAuthLevel.SKIP_ALL;
}
