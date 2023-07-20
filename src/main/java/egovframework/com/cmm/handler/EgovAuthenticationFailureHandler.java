package egovframework.com.cmm.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class EgovAuthenticationFailureHandler implements AuthenticationFailureHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAuthenticationFailureHandler.class);
	
	private final String DEFAULT_FAILURE_URL = "/login.do"; 
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMsg = null;
		
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			errorMsg = "ID, PASSWORD ERROR!!";
		} else if(exception instanceof DisabledException) {
			errorMsg = "ACCOUNT DISABLED ERROR!!";
		} else if(exception instanceof CredentialsExpiredException) {
			errorMsg = "PASSWORD HAS EXPIRED ERROR!!";
		} else {
			errorMsg = "UNKNOWN ERROR!!";
		}
		LOGGER.info(errorMsg);
		request.setAttribute("reason", errorMsg);
		request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
		
	}
	
}
