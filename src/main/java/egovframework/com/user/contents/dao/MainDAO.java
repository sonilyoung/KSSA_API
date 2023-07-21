package egovframework.com.user.contents.dao;

import java.util.List;

import egovframework.com.adm.system.vo.Board;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MainDAO")
public class MainDAO extends EgovAbstractMapper{
	
	private static final String Namespace = "egovframework.com.user.contents.dao.MainDAO";
	
	
	public List<?> selectNoticeList(Board params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectNoticeList", params);
	}

	public int insertNotice(Board params) {
		// TODO Auto-generated method stub
		return insert(Namespace + ".insertNotice", params);
	}

	public int updateNotice(Board params) {
		// TODO Auto-generated method stub
		return update(Namespace + ".updateNotice", params);
	}
	
	public int updateNoticeHitCnt(Board params) {
		return update(Namespace + ".updateNoticeHitCnt", params);
	}		
	
	public int deleteNotice(Board params) {
		// TODO Auto-generated method stub
		return delete(Namespace + ".deleteNotice", params);
	}

	public Board selectNotice(Board params) {
		// TODO Auto-generated method stub
		return selectOne(Namespace + ".selectNotice", params);
	}

}
