package egovframework.com.global.common.dao;

import java.util.LinkedHashMap;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.security.access.ConfigAttribute;

public interface SecuredObjectDAO {

    public void setDataSource(DataSource dataSource);

    public String getSqlRolesAndUrl();

    public void setSqlRolesAndUrl(String sqlRolesAndUrl);

    public String getSqlRolesAndMethod();

    public void setSqlRolesAndMethod(String sqlRolesAndMethod);

    public String getSqlRolesAndPointcut();

    public void setSqlRolesAndPointcut(String sqlRolesAndPointcut);

    public String getSqlRegexMatchedRequestMapping();

    public void setSqlRegexMatchedRequestMapping(String sqlRegexMatchedRequestMapping);

    public String getSqlHierarchicalRoles();

    public void setSqlHierarchicalRoles(String sqlHierarchicalRoles);

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndResources(String resourceType,
            String requestMatcherType) throws Exception;

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndUrl() throws Exception;

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndMethod() throws Exception;

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndPointcut() throws Exception;

    public List<ConfigAttribute> getRegexMatchedRequestMapping(String url) throws Exception;

    public String getHierarchicalRoles() throws Exception;
}
