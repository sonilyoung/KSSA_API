package egovframework.com.adm.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.adm.system.dao.SystemDAO;
import egovframework.com.adm.system.vo.Board;
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
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    @Resource(name = "SystemDAO")
	private SystemDAO systemDAO;

	@Override
	@SuppressWarnings("unchecked")
	public List<Board> selectNoticeList(Board params) {
		// TODO Auto-generated method stub
		return (List<Board>)systemDAO.selectNoticeList(params);
	}

	@Override
	public int insertNotice(Board params) {
		// TODO Auto-generated method stub
		return systemDAO.insertNotice(params);
	}

	@Override
	public int updateNotice(Board params) {
		// TODO Auto-generated method stub
		return systemDAO.updateNotice(params);
	}

	@Override
	public int updateNoticeHitCnt(Board params) {
		// TODO Auto-generated method stub
		return systemDAO.updateNoticeHitCnt(params);
	}
	
	@Override
	public int deleteNotice(Board params) {
		// TODO Auto-generated method stub
		return systemDAO.deleteNotice(params);
	}

	@Override
	public Board selectNotice(Board params) {
		// TODO Auto-generated method stub
		return systemDAO.selectNotice(params);
	}
    
	
}
