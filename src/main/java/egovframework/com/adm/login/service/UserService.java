package egovframework.com.adm.login.service;

import egovframework.com.adm.login.vo.User;

public interface UserService {

	User getUser(long userId);

	void modifyUser(User parameter);

	void modifyPwd(User parameter);

	Long getUserCount(User parameter);

}
