package egovframework.com.adm.login.dao;

import egovframework.com.adm.login.vo.User;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("UserDAO")
public class UserDAO extends EgovAbstractMapper{
	
	private static final String Namespace = "egovframework.com.domain.user.dao.UserDAO";

	public User getUser(long userId) {
		return (User) selectOne(Namespace + ".getUser", userId);
	}

	public void modifyUser(User parameter) {
		update(Namespace + ".getUser", parameter);
	}

	public int modifyPwd(User parameter) {
		return (int)update(Namespace + ".getUser", parameter);
	}

	public Long getUserCount(User parameter) {
		return (Long)selectOne(Namespace + ".getUserCount", parameter);
	}
}
