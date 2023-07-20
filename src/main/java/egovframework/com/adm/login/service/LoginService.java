package egovframework.com.adm.login.service;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.adm.login.vo.Login;
import egovframework.com.adm.login.vo.LoginRequest;

/**
 * 사용자관리에 관한 인터페이스클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *
 *      </pre>
 */
public interface LoginService {

    public String createToken(HttpServletRequest request, LoginRequest loginRequest);

    public Login getLoginInfo(HttpServletRequest request);    
    
	public void updateLoginTime(String loginId);
	
	public void updateLoginCnt(String loginId);
	
	public LoginRequest getPwdInfo(LoginRequest loginRequest);
	
}
