package egovframework.com.global.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileReader {
    	     
    public static File[] ListFile( String strDirPath ) { 
        File path = new File( strDirPath ); 
        File[] fList = path.listFiles(); 
        if(fList!=null) {
        	for( int i = 0; i < fList.length; i++ ) { 
        		if( fList[i].isDirectory() ) { 
        			ListFile( fList[i].getPath() );  // 재귀함수 호출 
        		} 
        	}
        }
		return fList; 
    }
}