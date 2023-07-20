package egovframework.com.global.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.global.DynamicContextHolder;
import egovframework.com.global.OfficeMessageSource;

/**
 * 요청받아 사용할 데이터소스를 셋팅하는 클래스
 */
public class DynamicDataSourceFilter implements Filter {

	private FilterConfig config;

	private List<String> excludedUrls;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession httpSession = ((HttpServletRequest)request).getSession(true);

		if(!excludeUrl(httpRequest)) {
			//URL is not '/mbl/*.do' pattern
			//String COMPANY_ID = httpRequest.getParameter("companyId");
			String dataKey    = httpRequest.getParameter("dataKey");

			if(StringUtils.isNotEmpty(dataKey)) {
				//로그인 이후 데이터소스 셋팅을 위해 해당 정보 session에 저장
				httpSession.setAttribute("COMPANY_ID", dataKey);

				//데이터소스 셋팅
				DynamicContextHolder.setDynamicType(dataKey);
			} else {
				//session에 저장된 데이터소스 구함
				String dynamicType = (String)httpSession.getAttribute("COMPANY_ID");

				if(StringUtils.isNotBlank(dynamicType)) {
					//데이터소스 셋팅
					DynamicContextHolder.setDynamicType((String)httpSession.getAttribute("COMPANY_ID"));
				} else {
					httpSession.setAttribute("COMPANY_ID", "COMM");
					//데이터소스 셋팅
					DynamicContextHolder.setDynamicType("COMM");

					//Redirect Login Page
					ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
					OfficeMessageSource officeMessageSource = (OfficeMessageSource) act.getBean("OfficeMessageSource");

					// 로그인 URL
					String redirectURL = config.getInitParameter("redirectURL");
					redirectURL = redirectURL.replaceAll("\r", "").replaceAll("\n", "");

					String redirectPage = config.getInitParameter("redirectPage");
					redirectPage = redirectPage.replaceAll("\r", "").replaceAll("\n", "");

					if(!httpSession.isNew()) {
						httpRequest.setAttribute("message",officeMessageSource.getMessage("error.msg02"));
					}

					httpRequest.setAttribute("redirectURL",redirectURL);
					RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(redirectPage);
					dispatcher.forward(httpRequest, httpResponse);
					return;
				}
			}
		}

		// DataSource 처리
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		
		//exclude url
		String excludePattern = config.getInitParameter("excludedUrls");
		excludedUrls = Arrays.asList(excludePattern.split(","));
	}

	private boolean excludeUrl(HttpServletRequest request) {
		boolean result = false;
		String uri = request.getRequestURI().trim();

		for(String excludeUrl: excludedUrls) {
			excludeUrl = excludeUrl.trim();
			if(StringUtils.isBlank(excludeUrl)) {
				continue;
			}
			if(uri.startsWith(excludeUrl)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
