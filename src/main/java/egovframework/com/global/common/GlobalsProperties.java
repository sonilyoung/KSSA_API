package egovframework.com.global.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.global.util.ResourceCloseHelper;
import egovframework.com.global.util.WebUtil;

/**
 * Class Name : Properties.java Description : properties값들을 파일로부터 읽어와 Globals클래스의 정적변수로 로드시켜주는 클래스로
 * 문자열 정보 기준으로 사용할 전역변수를 시스템 재시작으로 반영할 수 있도록 한다. Modification Information
 *
 * 수정일 수정자 수정내용 ------- -------- --------------------------- 2009.01.19 박지욱 최초 생성 2011.07.20 서준식
 * Globals파일의 상대경로를 읽은 메서드 추가 2014.10.13 이기하 Globals.properties 값이 null일 경우 오류처리
 * 
 * @author 공통 서비스 개발팀 박지욱
 * @since 2009. 01. 19
 * @version 1.0
 * @see
 *
 */

public class GlobalsProperties {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalsProperties.class);

    // 파일구분자
    final static String FILE_SEPARATOR = System.getProperty("file.separator");

    // 프로퍼티 파일의 물리적 위치
    public static final String GLOBALS_PROPERTIES_FILE = getPropertyFile();
    
    

    //public static final String RELATIVE_PATH_PREFIX = Properties.class.getResource("egovframework/com/domain/utils/globals.properties").getPath();
    // + FILE_SEPARATOR+ ".." + FILE_SEPARATOR + ".." + FILE_SEPARATOR;
    /*
    public static final String RELATIVE_PATH_PREFIX =
            GlobalsProperties.class.getResource("").getPath().substring(0,
                    GlobalsProperties.class.getResource("").getPath().lastIndexOf("office"));

    public static final String MONITOR_TARGET_LIST_FILE =
            RELATIVE_PATH_PREFIX + "properties" + FILE_SEPARATOR + "monitor-targets.properties";
	*/
    
    private static String getPropertyFile() {
    	ClassLoader cl;
    	cl = Thread.currentThread().getContextClassLoader();   	
    	
    	if (cl == null)
    		cl = ClassLoader.getSystemClassLoader();    	
    	URL url = cl.getResource("globals.properties");    	
    	
    	return url.getPath().toString();
    }        
    
    
    
    /**
     * 인자로 주어진 문자열을 Key값으로 하는 상대경로 프로퍼티 값을 절대경로로 반환한다(Globals.java 전용)
     * 
     * @param keyName String
     * @return String
     */
    public static String getPathProperty(String keyName) {
        String value = "";
        LOGGER.debug("getPathProperty : {} = {}", GLOBALS_PROPERTIES_FILE, keyName);

        FileInputStream fis = null;
        try {
            Properties props = new Properties();

            fis = new FileInputStream(WebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE));
            props.load(new BufferedInputStream(fis));

            value = props.getProperty(keyName).trim();
            //value = RELATIVE_PATH_PREFIX + "properties" + System.getProperty("file.separator")+ value;
        } catch (FileNotFoundException fne) {
            LOGGER.debug("Property file not found.", fne);
            throw new RuntimeException("Property file not found", fne);
        } catch (IOException ioe) {
            LOGGER.debug("Property file IO exception", ioe);
            throw new RuntimeException("Property file IO exception", ioe);
        } finally {
            ResourceCloseHelper.close(fis);
        }

        return value;
    }

    /**
     * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
     * 
     * @param keyName String
     * @return String
     */
    public static String getProperty(String keyName) {
        String value = "";

        LOGGER.debug("getProperty : {} = {}", GLOBALS_PROPERTIES_FILE, keyName);

        FileInputStream fis = null;
        try {
            Properties props = new Properties();

            fis = new FileInputStream(WebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE));

            props.load(new BufferedInputStream(fis));
            if (props.getProperty(keyName) == null) {
                return "";
            }
            value = props.getProperty(keyName).trim();
        } catch (FileNotFoundException fne) {
            LOGGER.debug("Property file not found.", fne);
            throw new RuntimeException("Property file not found", fne);
        } catch (IOException ioe) {
            LOGGER.debug("Property file IO exception", ioe);
            throw new RuntimeException("Property file IO exception", ioe);
        } finally {
            ResourceCloseHelper.close(fis);
        }

        return value;
    }

    
    /**
     * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 상대 경로값을 절대 경로값으로 반환한다
     * 
     * @param fileName String
     * @param key String
     * @return String
     */
    public static String getPathProperty(String fileName, String key) {
        FileInputStream fis = null;
        try {
            Properties props = new Properties();

            fis = new FileInputStream(WebUtil.filePathBlackList(fileName));
            props.load(new BufferedInputStream(fis));
            fis.close();

            String value = props.getProperty(key);
            //value = RELATIVE_PATH_PREFIX + "properties" + System.getProperty("file.separator")+ value;

            return value;
        } catch (FileNotFoundException fne) {
            LOGGER.debug("Property file not found.", fne);
            throw new RuntimeException("Property file not found", fne);
        } catch (IOException ioe) {
            LOGGER.debug("Property file IO exception", ioe);
            throw new RuntimeException("Property file IO exception", ioe);
        } finally {
            ResourceCloseHelper.close(fis);
        }
    }

    /**
     * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
     * 
     * @param fileName String
     * @param key String
     * @return String
     */
    public static String getProperty(String fileName, String key) {
        FileInputStream fis = null;
        try {
            Properties props = new Properties();

            fis = new FileInputStream(WebUtil.filePathBlackList(fileName));
            props.load(new BufferedInputStream(fis));
            fis.close();

            String value = props.getProperty(key);

            return value;
        } catch (FileNotFoundException fne) {
            LOGGER.debug("Property file not found.", fne);
            throw new RuntimeException("Property file not found", fne);
        } catch (IOException ioe) {
            LOGGER.debug("Property file IO exception", ioe);
            throw new RuntimeException("Property file IO exception", ioe);
        } finally {
            ResourceCloseHelper.close(fis);
        }
    }

    /**
     * 주어진 프로파일의 내용을 파싱하여 (key-value) 형태의 구조체 배열을 반환한다.
     * 
     * @param property String
     * @return ArrayList
     */
    public static ArrayList<Map<String, String>> loadPropertyFile(String property) {

        // key - value 형태로 된 배열 결과
        ArrayList<Map<String, String>> keyList = new ArrayList<Map<String, String>>();

        String src = property.replace('\\', File.separatorChar).replace('/', File.separatorChar);
        FileInputStream fis = null;
        try {

            File srcFile = new File(WebUtil.filePathBlackList(src));
            if (srcFile.exists()) {

                Properties props = new Properties();
                fis = new FileInputStream(src);
                props.load(new BufferedInputStream(fis));
                fis.close();

                Enumeration<?> plist = props.propertyNames();
                if (plist != null) {
                    while (plist.hasMoreElements()) {
                        Map<String, String> map = new HashMap<String, String>();
                        String key = (String) plist.nextElement();
                        map.put(key, props.getProperty(key));
                        keyList.add(map);
                    }
                }
            }
        } catch (IOException ex) {
            LOGGER.debug("IO Exception", ex);
            throw new RuntimeException(ex);
        } finally {
            ResourceCloseHelper.close(fis);
        }

        return keyList;
    }

    /*
    public static ArrayList<String> getTargetList() {
        String target = null;
        ArrayList<String> alTargets = new ArrayList<String>();
        LOGGER.debug("getTargetList : {}", MONITOR_TARGET_LIST_FILE);

        FileInputStream fis = null;
        try {
            Properties props = new Properties();

            fis = new FileInputStream(WebUtil.filePathBlackList(MONITOR_TARGET_LIST_FILE));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            while ((target = br.readLine()) != null) {
                target = target.trim();
                if (target.startsWith("#") || target.length() == 0)
                    continue;

                alTargets.add(target.trim());
            }

        } catch (FileNotFoundException fne) {
            LOGGER.debug("Property file not found.", fne);
            throw new RuntimeException("Property file not found", fne);
        } catch (IOException ioe) {
            LOGGER.debug("Property file IO exception", ioe);
            throw new RuntimeException("Property file IO exception", ioe);
        } finally {
            ResourceCloseHelper.close(fis);
        }

        return alTargets;
    }*/
}
