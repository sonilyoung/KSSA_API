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
	
	
	public List<?> selectNewsList(Board params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectNewsList", params);
	}

	public int insertNews(Board params) {
		// TODO Auto-generated method stub
		return insert(Namespace + ".insertNews", params);
	}

	public int updateNews(Board params) {
		// TODO Auto-generated method stub
		return update(Namespace + ".updateNews", params);
	}
	
	public int updateNewsHitCnt(Board params) {
		return update(Namespace + ".updateNewsHitCnt", params);
	}		
	
	public int deleteNews(Board params) {
		// TODO Auto-generated method stub
		return delete(Namespace + ".deleteNews", params);
	}

	public Board selectNews(Board params) {
		// TODO Auto-generated method stub
		return selectOne(Namespace + ".selectNews", params);
	}	
	
	public List<?> selectLawsList(Board params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectLawsList", params);
	}

	public int insertLaws(Board params) {
		// TODO Auto-generated method stub
		return insert(Namespace + ".insertLaws", params);
	}

	public int updateLaws(Board params) {
		// TODO Auto-generated method stub
		return update(Namespace + ".updateLaws", params);
	}
	
	public int updateLawsHitCnt(Board params) {
		return update(Namespace + ".updateLawsHitCnt", params);
	}		
	
	public int deleteLaws(Board params) {
		// TODO Auto-generated method stub
		return delete(Namespace + ".deleteLaws", params);
	}

	public Board selectLaws(Board params) {
		// TODO Auto-generated method stub
		return selectOne(Namespace + ".selectLaws", params);
	}	
	
	public List<?> selectDatumList(Board params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectDatumList", params);
	}

	public int insertDatum(Board params) {
		// TODO Auto-generated method stub
		return insert(Namespace + ".insertDatum", params);
	}

	public int updateDatum(Board params) {
		// TODO Auto-generated method stub
		return update(Namespace + ".updateDatum", params);
	}
	
	public int updateDatumHitCnt(Board params) {
		return update(Namespace + ".updateDatumHitCnt", params);
	}		
	
	public int deleteDatum(Board params) {
		// TODO Auto-generated method stub
		return delete(Namespace + ".deleteDatum", params);
	}

	public Board selectDatum(Board params) {
		// TODO Auto-generated method stub
		return selectOne(Namespace + ".selectDatum", params);
	}		
}
