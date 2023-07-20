package egovframework.com.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.adm.login.service.LoginService;
import egovframework.com.adm.login.vo.Login;
import egovframework.com.global.annotation.SkipAuth;
import egovframework.com.global.authorization.SkipAuthLevel;
import egovframework.com.global.exception.CustomBaseException;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.session.SessionUserInfoHolder;

/**
 * 사용자 인증과 인가를 처리하기 위한 인터셉터
 * 
 * @fileName : AuthInterceptor.java
 * @author : YeongJun Lee
 * @date : 2022.06.08
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        // RequestMapping 에 해당하지 않는 요청은 통과
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 메소드에 정의한 SkipAuth Annotation 체크
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        SkipAuth skipAuth = handlerMethod.getMethodAnnotation(SkipAuth.class);
        SkipAuthLevel skipAuthLevel =
                skipAuth != null ? skipAuth.skipAuthLevel() : SkipAuthLevel.SKIP_NONE;

        // 인증 및 권한 체크 모두 필요 없는 요청은 통과
        if (SkipAuthLevel.SKIP_ALL.equals(skipAuthLevel)) {
            return true;
        }

        // 인증(로그인) 체크
        Login login = loginService.getLoginInfo(request);
        if (login == null || !StringUtils.hasText(login.getUserId())) {
            throw new CustomBaseException(BaseResponseCode.AUTH_FAIL);
        }

        // 권한 체크
        if (!SkipAuthLevel.SKIP_AUTHORIZATION.equals(skipAuthLevel)) {
            // 권한 체크 TODO
            boolean authorized = true;
            if (!authorized) {
                throw new CustomBaseException(BaseResponseCode.AUTH_ERROR);
            }
        }

        // 사용자 정보 ThreadLocal에 저장
        SessionUserInfoHolder.set(login);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object obj, Exception e) throws Exception {
        // 사용자 정보 ThreadLocal 삭제
        SessionUserInfoHolder.remove();
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
