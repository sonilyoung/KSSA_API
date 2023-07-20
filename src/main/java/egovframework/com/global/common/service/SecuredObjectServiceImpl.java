package egovframework.com.global.common.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import egovframework.com.global.common.dao.SecuredObjectDAO;

@Service("SecuredObjectService")
public class SecuredObjectServiceImpl implements SecuredObjectService {

    @Autowired
    private SecuredObjectDAO securedObjectDAO;

    public SecuredObjectDAO getSecuredObjectDAO() {
        return securedObjectDAO;
    }

    public void setSecuredObjectDAO(SecuredObjectDAO secureObjectDao) {
        this.securedObjectDAO = secureObjectDao;
    }

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
        // TODO Auto-generated method stub
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> ret =
                new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
        LinkedHashMap<Object, List<ConfigAttribute>> data = securedObjectDAO.getRolesAndUrl();
        Set<Object> keys = data.keySet();
        for (Object key : keys) {
            ret.put((AntPathRequestMatcher) key, data.get(key));
        }
        return ret;
    }

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getRolesAndMethod() throws Exception {
        // TODO Auto-generated method stub
        LinkedHashMap<String, List<ConfigAttribute>> ret =
                new LinkedHashMap<String, List<ConfigAttribute>>();
        LinkedHashMap<Object, List<ConfigAttribute>> data = securedObjectDAO.getRolesAndMethod();
        Set<Object> keys = data.keySet();
        for (Object key : keys) {
            ret.put((String) key, data.get(key));
        }
        return ret;
    }

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getRolesAndPointcut() throws Exception {
        // TODO Auto-generated method stub
        LinkedHashMap<String, List<ConfigAttribute>> ret =
                new LinkedHashMap<String, List<ConfigAttribute>>();
        LinkedHashMap<Object, List<ConfigAttribute>> data = securedObjectDAO.getRolesAndPointcut();
        Set<Object> keys = data.keySet();
        for (Object key : keys) {
            ret.put((String) key, data.get(key));
        }
        return ret;
    }

    @Override
    public List<ConfigAttribute> getMatchedRequestMapping(String url) throws Exception {
        // TODO Auto-generated method stub
        return securedObjectDAO.getRegexMatchedRequestMapping(url);
    }

    @Override
    public String getHierarchicalRoles() throws Exception {
        // TODO Auto-generated method stub
        return securedObjectDAO.getHierarchicalRoles();
    }

}
