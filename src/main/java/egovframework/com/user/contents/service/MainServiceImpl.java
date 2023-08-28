package egovframework.com.user.contents.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.adm.system.vo.Board;
import egovframework.com.user.contents.dao.MainDAO;
import lombok.extern.log4j.Log4j2;


/**
 * 사용자관리에 관한 비지니스 클래스를 정의한다.
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
 *   2014.12.08	 이기하			암호화방식 변경(OfficeFileScrty.encryptPassword)
 *
 *      </pre>
 */
@Log4j2
@Service("MainService")
public class MainServiceImpl implements MainService {

    @Resource(name = "MainDAO")
	private MainDAO mainDAO;

	@Override
	@SuppressWarnings("unchecked")
	public List<Board> selectMainNoticeList(Board params) {
		// TODO Auto-generated method stub
		return (List<Board>)mainDAO.selectMainNoticeList(params);
	}    
    
	@Override
	@SuppressWarnings("unchecked")
	public List<Board> selectMainInfoList(Board params) {
		// TODO Auto-generated method stub
		return (List<Board>)mainDAO.selectMainInfoList(params);
	}
    
	@Override
	@SuppressWarnings("unchecked")
	public List<Board> selectNoticeList(Board params) {
		// TODO Auto-generated method stub
		return (List<Board>)mainDAO.selectNoticeList(params);
	}

	@Override
	public int insertNotice(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.insertNotice(params);
	}

	@Override
	public int updateNotice(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.updateNotice(params);
	}

	@Override
	public int updateNoticeHitCnt(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.updateNoticeHitCnt(params);
	}	
	
	@Override
	public int deleteNotice(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.deleteNotice(params);
	}

	@Override
	public Board selectNotice(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.selectNotice(params);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Board> selectFAQList(Board params) {
		// TODO Auto-generated method stub
		return (List<Board>)mainDAO.selectFAQList(params);
	}

	@Override
	public int insertFAQ(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.insertFAQ(params);
	}

	@Override
	public int updateFAQ(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.updateFAQ(params);
	}

	@Override
	public int updateFAQHitCnt(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.updateFAQHitCnt(params);
	}	
	
	@Override
	public int deleteFAQ(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.deleteFAQ(params);
	}

	@Override
	public Board selectFAQ(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.selectFAQ(params);
	}



	@Override
	@SuppressWarnings("unchecked")
	public List<Board> selectInfoList(Board params) {
		// TODO Auto-generated method stub
		return (List<Board>)mainDAO.selectInfoList(params);
	}

	@Override
	public int insertInfo(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.insertInfo(params);
	}

	@Override
	public int updateInfo(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.updateInfo(params);
	}

	@Override
	public int updateInfoHitCnt(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.updateInfoHitCnt(params);
	}	
	
	@Override
	public int deleteInfo(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.deleteInfo(params);
	}

	@Override
	public Board selectInfo(Board params) {
		// TODO Auto-generated method stub
		return mainDAO.selectInfo(params);
	}

	

}
