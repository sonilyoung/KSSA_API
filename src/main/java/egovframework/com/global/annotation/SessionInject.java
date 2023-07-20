package egovframework.com.global.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(PARAMETER)
/**
 * 파라메터에 사용자 정보를 주입하기 위한 Annotation<br>
 * {@link egovframework.com.global.aop.SessionInjectAspect} 에서 처리됨<br>
 * 동작 조건 : SessionUserInfo를 상속한 파라메터에 SessionInject Annotation 정의
 * 
 * @fileName : SessionInject.java
 * @see egovframework.com.global.session.SessionUserInfo
 * @author : YeongJun Lee
 * @date : 2022.06.08
 */
public @interface SessionInject {

}
