package egovframework.com.user.calendar.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.user.calendar.dao.CalendarDAO;
import egovframework.com.user.calendar.vo.Calendar;
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
@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {

    @Resource(name = "CalendarDAO")
	private CalendarDAO calendarDAO;
	@Override
	@SuppressWarnings("unchecked")
	public List<Calendar> selectCalendarList(Calendar params) {
		// TODO Auto-generated method stub
		return (List<Calendar>)calendarDAO.selectCalendarList(params);
	}

	@Override
	public int insertCalendar(Calendar params) {
		// TODO Auto-generated method stub
		return calendarDAO.insertCalendar(params);
	}

	@Override
	public int updateCalendar(Calendar params) {
		// TODO Auto-generated method stub
		
		params.setStudentCnt(params.getStudentCnt() - params.getApplyStudentCnt()); 
		return calendarDAO.updateCalendar(params);
	}
	
	@Override
	public int deleteCalendar(Calendar params) {
		// TODO Auto-generated method stub
		return calendarDAO.deleteCalendar(params);
	}

	@Override
	public Calendar selectCalendar(Calendar params) {
		// TODO Auto-generated method stub
		return calendarDAO.selectCalendar(params);
	}


}
