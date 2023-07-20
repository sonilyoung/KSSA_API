package egovframework.com.global.util;

public class FCalendarDateFormat {
	
	public static String convertDateForm(StringBuffer sb, boolean allDay) {
		String strDate = sb.insert(4, "-").insert(7, "-")
						.insert(10, "T").insert(13, ":").insert(16, ":00").toString();
		/*if(allDay){
			strDate = sb.substring(0,10);
		}*/
		
		return strDate;
	}
	
	public static String convertDateFormReverse(String str, boolean allDay) {
		String strDate = "";
				
		if(allDay){
			strDate = str.replaceAll("-", "") + "0000";
		}else {
			strDate = str.substring(0,16).replace("T", "").replaceAll("-", "").replaceAll(":", "");
		}
		
		return strDate;
	}
}
