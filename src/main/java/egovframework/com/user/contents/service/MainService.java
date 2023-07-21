package egovframework.com.user.contents.service;

import java.util.List;

import egovframework.com.adm.system.vo.Board;
import egovframework.com.user.contents.vo.Notice;

/**
 * 사용자관리에 관한 인터페이스클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @see
 * @version 1.0
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *
 *      </pre>
 */
public interface MainService {
	
	public List<Board> selectNoticeList(Board params);
	
	public int insertNotice(Board params);
	
	public int updateNotice(Board params);
	
	public int updateNoticeHitCnt(Board params);
	
	public int deleteNotice(Board params);

	public Board selectNotice(Board params);
	
	public List<Board> selectFAQList(Board params);
	
	public int insertFAQ(Board params);
	
	public int updateFAQ(Board params);
	
	public int updateFAQHitCnt(Board params);
	
	public int deleteFAQ(Board params);

	public Board selectFAQ(Board params);

	public List<Board> selectInfoList(Board params);
	
	public int insertInfo(Board params);
	
	public int updateInfo(Board params);
	
	public int updateInfoHitCnt(Board params);
	
	public int deleteInfo(Board params);

	public Board selectInfo(Board params);	
}
