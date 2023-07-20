package egovframework.com.global.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.OncePerRequestFilter;

import egovframework.com.adm.login.service.LoginService;
import egovframework.com.adm.login.vo.Login;
import egovframework.com.global.exception.CustomBaseException;
import egovframework.com.global.http.BaseResponseCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 사용자 서비스
     */
    @Autowired
    protected LoginService loginService;

	@Autowired
	protected Environment env;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, CustomBaseException {
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new CustomBaseException(BaseResponseCode.AUTH_ERROR);
		}	
        filterChain.doFilter(request, response);
    }

}