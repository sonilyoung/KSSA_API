package egovframework.com.global.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateHelper.class);
	
    public static String getCurrDateTime() {
        Date from = new Date();
        String to = "";
        try {
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            to = transFormat.format(from);
        } catch (Exception e) {
            //e.printStackTrace();
        	LOGGER.error(e.getMessage());
        }
        
        return to;
    }
    
    public static Date convertDate(String date, String inputFormat) {
    	if ((date == null) || (date.isEmpty() || date.trim().isEmpty()) 
    			|| (inputFormat == null) || (inputFormat.isEmpty())) {
    		return null;
    	}
    	
    	SimpleDateFormat format = new SimpleDateFormat(inputFormat);
    	
    	Date dateTime = new Date();
		try {
			dateTime = format.parse(date.trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
    	
    	return dateTime;
    }
}
