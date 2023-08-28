package egovframework.com.user.contents.dao;

import java.util.List;

import egovframework.com.adm.system.vo.Board;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MainDAO")
public class MainDAO extends EgovAbstractMapper{
	
	private static final String Namespace = "egovframework.com.user.contents.dao.MainDAO";
	
	
	public List<?> selectMainNoticeList(Board params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectMainNoticeList", params);
	}	
	
	public List<?> selectMainInfoList(Board params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectMainInfoList", params);
	}	
	
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
	public List<?> selectFAQList(Board params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectFAQList", params);
	}

	public int insertFAQ(Board params) {
		// TODO Auto-generated method stub
		return insert(Namespace + ".insertFAQ", params);
	}

	public int updateFAQ(Board params) {
		// TODO Auto-generated method stub
		return update(Namespace + ".updateFAQ", params);
	}
	
	public int updateFAQHitCnt(Board params) {
		return update(Namespace + ".updateFAQHitCnt", params);
	}		
	
	public int deleteFAQ(Board params) {
		// TODO Auto-generated method stub
		return delete(Namespace + ".deleteFAQ", params);
	}

	public Board selectFAQ(Board params) {
		// TODO Auto-generated method stub
		return selectOne(Namespace + ".selectFAQ", params);
	}
	
	public List<?> selectInfoList(Board params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectInfoList", params);
	}

	public int insertInfo(Board params) {
		// TODO Auto-generated method stub
		return insert(Namespace + ".insertInfo", params);
	}

	public int updateInfo(Board params) {
		// TODO Auto-generated method stub
		return update(Namespace + ".updateInfo", params);
	}
	
	public int updateInfoHitCnt(Board params) {
		return update(Namespace + ".updateInfoHitCnt", params);
	}		
	
	public int deleteInfo(Board params) {
		// TODO Auto-generated method stub
		return delete(Namespace + ".deleteInfo", params);
	}

	public Board selectInfo(Board params) {
		// TODO Auto-generated method stub
		return selectOne(Namespace + ".selectInfo", params);
	}	
}
