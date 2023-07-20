package egovframework.com.adm.system.service;

import java.util.List;

import egovframework.com.adm.system.vo.Board;

/**
 * 사용자관리에 관한 인터페이스클래스를 정의한다.
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
 *
 *      </pre>
 */
public interface SystemService {
	
	public List<Board> selectNoticeList(Board params);
	
	public int insertNotice(Board params);
	
	public int updateNotice(Board params);
	
	public int deleteNotice(Board params);

	public Board selectNotice(Board params);
	
}
