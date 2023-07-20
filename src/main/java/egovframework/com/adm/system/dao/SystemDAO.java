package egovframework.com.adm.system.dao;

import java.util.List;

import egovframework.com.adm.system.vo.Board;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("SystemDAO")
public class SystemDAO extends EgovAbstractMapper{
	
	private static final String Namespace = "egovframework.com.adm.system.dao.SystemDAO";
	
	public List<?> selectNoticeList(Board params) {
		return (List<?>)selectList(Namespace + ".selectNoticeList", params);
	}
	
	public int insertNotice(Board params) {
		return insert(Namespace + ".insertNotice", params);
	}
	
	public int updateNotice(Board params) {
		return update(Namespace + ".updateNotice", params);
	}	
	
	public int deleteNotice(Board params) {
		return delete(Namespace + ".deleteNotice", params);
	}	

	public Board selectNotice(Board params) {
		return selectOne(Namespace + ".selectNotice", params);
	}	
}
