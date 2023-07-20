package egovframework.com.global.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class ComUtils {
	public static Boolean imgExtentionCheck(MultipartFile m) {
        String fileName = m.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        if(!fileExtension.toUpperCase().equals("JPG") 
       		&& !fileExtension.toUpperCase().equals("GIF")
       		&& !fileExtension.toUpperCase().equals("JPEG")
       		&& !fileExtension.toUpperCase().equals("PNG")
        ) {
        	return false;
        }else {
        	return true;
        }	
	}

}