package egovframework.com.user.calendar.service;

import java.util.List;

import egovframework.com.user.calendar.vo.Calendar;


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
public interface CalendarService {

	public List<Calendar> selectCalendarList(Calendar params);
	
	public int insertCalendar(Calendar params);
	
	public int updateCalendar(Calendar params);
	
	public int deleteCalendar(Calendar params);

	public Calendar selectCalendar(Calendar params);
	
}

