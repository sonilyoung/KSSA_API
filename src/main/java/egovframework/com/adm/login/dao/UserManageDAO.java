package egovframework.com.adm.login.dao;

import egovframework.com.adm.login.vo.Login;
import egovframework.com.adm.login.vo.LoginRequest;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * 사용자관리에 관한 데이터 접근 클래스를 정의한다.
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
@Mapper("UserManageDAO")
public class UserManageDAO extends EgovAbstractMapper {

    private static final String Namespace = "egovframework.com.adm.login.dao.UserManageDAO";

    public Login getByLoginId(String loginId) {
        return (Login)selectOne(Namespace + ".getByLoginId", loginId);
    }

	public void updateLoginTime(String loginId) {
		update(Namespace + ".updateLoginTime", loginId);
	}
	
	public void updateLoginCnt(String loginId) {
		update(Namespace + ".updateLoginCnt", loginId);
	}	

	public int getUserStatus(long workplaceId) {
		return selectOne(Namespace + ".getUserStatus", workplaceId);
	}
	
	public LoginRequest getPwdInfo(LoginRequest params) {
		return selectOne(Namespace + ".getPwdInfo", params);
	}

}
