package egovframework.com.adm.login.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.adm.login.dao.UserDAO;
import egovframework.com.adm.login.vo.User;
import egovframework.com.global.exception.CustomBaseException;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.util.AES256Util;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Resource(name = "UserDAO")
	private UserDAO repository;		
		
	@Override
	public User getUser(long userId) {
		return repository.getUser(userId);
	}

	@Override
	public void modifyUser(User parameter) {
		repository.modifyUser(parameter);
	}

	@Override
	public void modifyPwd(User parameter) {
		
		try {
			AES256Util aesUtil = new AES256Util();
	        	
        	if(StringUtils.equals(parameter.getChangePwd(), parameter.getConfirmPwd())) {
        		
        		parameter.setChangePwd(aesUtil.encrypt(parameter.getChangePwd()));
        		repository.modifyPwd(parameter);
        		
        	} else {
        		
        		throw new CustomBaseException(BaseResponseCode.NO_USERS, new String[] {});
        	}
	        	
		
		} catch(Exception e) {
			
			throw new CustomBaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {e.getMessage()});
			
		}
		
	}
	
	@Override
	public Long getUserCount(User parameter) {
		
		return repository.getUserCount(parameter);
	}

}
