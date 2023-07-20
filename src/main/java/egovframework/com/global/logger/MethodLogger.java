package egovframework.com.global.logger;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodLogger implements Filter {

    //필터의 핵심. request와 response를 이용하여 요청과 응답을 처리한다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //전처리 과정 - HttpServletRequest와 HttpServletResponse를 캐시 가능하도록 래핑해준다.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);


        //전, 후 처리의 기준이되는 메소드
        //filter의 동작에 httpServletRequest, httpServletResponse를 이용한다.
        chain.doFilter(httpServletRequest, httpServletResponse);


        //후 처리 과정

        //request 요청으로 어떤 uri가 들어왔는지 확인
        String uri = httpServletRequest.getRequestURI();

        //request 내용 확인
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        // response 내용 상태 정보, 내용 확인
        int httpStatus = httpServletResponse.getStatusCode();
        String resContent = new String(httpServletResponse.getContentAsByteArray());

        //주의 : response를 클라이언트에서 볼 수 있도록 하려면 response를 복사해야 한다. response를 콘솔에 보여주면 내용이 사라진다.
        httpServletResponse.copyBodyToResponse();

        log.debug("====================================================================================");
        log.debug("STATUS : {}", httpStatus);
        log.debug("URL : {}", uri);
        //log.debug("REQUEST : {}", reqContent);
        
        //octet-stream , excel
        if(httpServletResponse.getContentType()!=null) {
        	if(!httpServletResponse.getContentType().contains("excelFile") && !httpServletResponse.getContentType().contains("excel") && !httpServletResponse.getContentType().contains("octet-stream")) {
        		log.debug("RESPONSE : {}", resContent);
        	}else {
        		log.debug("RESPONSE : file respnse");	
        	}
        }
        log.debug("====================================================================================");        
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}