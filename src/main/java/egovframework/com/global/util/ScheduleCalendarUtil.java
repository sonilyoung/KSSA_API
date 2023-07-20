package egovframework.com.global.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleCalendarUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleCalendarUtil.class);

	// 날짜문자열 (DD/MM/YYYY) 과 추가 일수를 인수로 해당 일자의 UTC time millisecond 반환
	public static long getTimeInMillisByDateDDMMYYYY(String dateStr, int addDays) {
		int year = Integer.parseInt(dateStr.substring(dateStr.lastIndexOf("/")+1));
		int month = Integer.parseInt(dateStr.substring(dateStr.indexOf("/")+1, dateStr.indexOf("/")+3));
		int day = Integer.parseInt(dateStr.substring(0, dateStr.indexOf("/")));
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.YEAR , year);
		calendar.set(Calendar.MONTH , month-1);
		calendar.set(Calendar.DAY_OF_MONTH , day);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.DATE, addDays);

		return calendar.getTimeInMillis();
	}

	// 날짜문자열 (YYYY/MM/DD) 과 추가 일수를 인수로 해당 일자의 UTC time millisecond 반환
	public static long getTimeInMillisByDateYYYYMMDD(String dateStr, int addDays) {
		int day = Integer.parseInt(dateStr.substring(dateStr.lastIndexOf("-")+1));
		int month = Integer.parseInt(dateStr.substring(dateStr.indexOf("-")+1, dateStr.indexOf("-")+3));
		int year = Integer.parseInt(dateStr.substring(0, dateStr.indexOf("-")));
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.YEAR , year);
		calendar.set(Calendar.MONTH , month-1);
		calendar.set(Calendar.DAY_OF_MONTH , day);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.DATE, addDays);

		return calendar.getTimeInMillis();
	}

    // Getting the time for fullCalendar (input : millisecond. output : yyyy-MM-dd T HH:mm:ss)
    public static String getTimeFullCal(String millisecond) {
        long lStartDt	= Long.parseLong( millisecond );
        SimpleDateFormat sdfFormat1	= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfFormat2	= new SimpleDateFormat("HH:mm:ss");

        String sTime	= sdfFormat1.format(lStartDt) + "T" + sdfFormat2.format(lStartDt);

        return sTime;
    }

	// Getting the time for miniCalendar (input : millisecond. output : yyyyMMdd)
	public static String getDateMiniCal(String millisecond) {
		long lStartDt	= Long.parseLong( millisecond );
		SimpleDateFormat sdfFormat1	= new SimpleDateFormat("yyyyMMdd");

		String sTime	= sdfFormat1.format(lStartDt) ;

		return sTime;
	}

	// Getting the time for miniCalendar (input : millisecond. output : yyyyMMdd)
	public static String getTimeMiniCal(String millisecond) {
		long lStartDt	= Long.parseLong( millisecond );
		SimpleDateFormat sdfFormat1	= new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String sTime	= sdfFormat1.format(lStartDt) ;
		sTime = sTime.replace(" ", "T");
		return sTime;
	}

	public static String getFirstDayOfThisMonth(String yearStr, String monthStr) {
		Calendar calendar = Calendar.getInstance();

		if(StringUtils.isNotEmpty(yearStr) && StringUtils.isNotEmpty(monthStr)) {
			int year = Integer.parseInt(yearStr);
			int month = Integer.parseInt(monthStr);
			calendar.set(Calendar.YEAR , year);
			calendar.set(Calendar.MONTH, month-1);
		}
		calendar.set(Calendar.DAY_OF_MONTH , 1);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

		return sdFormat.format(calendar.getTime());
	}

	public static String getLastDayOfThisMonth(String yearStr, String monthStr) {
		Calendar calendar = Calendar.getInstance();

		if(StringUtils.isNotEmpty(yearStr) && StringUtils.isNotEmpty(monthStr)) {
			int year = Integer.parseInt(yearStr);
			int month = Integer.parseInt(monthStr);
			calendar.set(Calendar.YEAR , year);
			calendar.set(Calendar.MONTH, month-1);
		}
		calendar.set(Calendar.DAY_OF_MONTH , calendar.getActualMaximum(Calendar.DATE));

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

		return sdFormat.format(calendar.getTime());
	}

	// 오늘 0시 0분 0초 반환 타임존 적용
    public static String getTodayStartTime(TimeZone timeZone, String formatStr) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.setTimeZone(timeZone);
		LOGGER.info("getTodayStartTime : " + calendar.getTimeInMillis());
		SimpleDateFormat sformat = new SimpleDateFormat(formatStr);
        
        return sformat.format(calendar.getTime());
    }

	// 오늘 0시 0분 0초 반환 타임존 UTC
	public static String getTodayStartTime(String formatStr) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		LOGGER.info("getTodayStartTime : " + calendar.getTimeInMillis());
		SimpleDateFormat sformat = new SimpleDateFormat(formatStr);

		return sformat.format(calendar.getTime());
	}
}
