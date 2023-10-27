package egovframework.com.user.calendar.dao;

import java.util.List;

import egovframework.com.user.calendar.vo.Calendar;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CalendarDAO")
public class CalendarDAO extends EgovAbstractMapper{
	
	private static final String Namespace = "egovframework.com.user.calendar.dao.CalendarDAO";
	
	
	public List<?> selectCalendarList(Calendar params) {
		// TODO Auto-generated method stub
		return (List<?>)selectList(Namespace + ".selectCalendarList", params);
	}	

	public int insertCalendar(Calendar params) {
		// TODO Auto-generated method stub
		return insert(Namespace + ".insertCalendar", params);
	}

	public int updateCalendar(Calendar params) {
		// TODO Auto-generated method stub
		return update(Namespace + ".updateCalendar", params);
	}
	
	public int deleteCalendar(Calendar params) {
		// TODO Auto-generated method stub
		return delete(Namespace + ".deleteCalendar", params);
	}

	public Calendar selectCalendar(Calendar params) {
		// TODO Auto-generated method stub
		return selectOne(Namespace + ".selectCalendar", params);
	}
	
}
