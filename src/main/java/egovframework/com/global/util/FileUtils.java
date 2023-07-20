package egovframework.com.global.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("FileUtil")
public class FileUtils {

    public static final int BUFF_SIZE = 2048;


    /**
     * 첨부파일을 서버에 저장한다.
     * 
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		InputStream stream = null;
		OutputStream bos = null;
		
		try {
		    stream = file.getInputStream();
		    File cFile = new File(stordFilePath);
	
		    if (!cFile.isDirectory()) {
			boolean _flag = cFile.mkdir();
			if (!_flag) {
			    throw new IOException("Directory creation Failed ");
			}
		    }
	
		    bos = new FileOutputStream(stordFilePath + File.separator + newName);
	
		    int bytesRead = 0;
		    byte[] buffer = new byte[BUFF_SIZE];
	
		    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
			bos.write(buffer, 0, bytesRead);
		    }
		} catch (FileNotFoundException fnfe) {
		    fnfe.printStackTrace();
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if (bos != null) {
				try {
				    bos.close();
				} catch (Exception ignore) {
				    log.debug("IGNORED: " + ignore.getMessage());
				}
		    }
		    if (stream != null) {
				try {
				    stream.close();
				} catch (Exception ignore) {
				    log.debug("IGNORED: " + ignore.getMessage());
				}
		    }
		}
    }	
	
    /**
     * 서버의 파일을 다운로드한다.
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String downFileName = "";
		String orgFileName = "";
	
		if ((String)request.getAttribute("downFile") == null) {
		    downFileName = "";
		} else {
		    downFileName = (String)request.getAttribute("downFile");		    
		}
	
		if ((String)request.getAttribute("orginFile") == null) {
		    orgFileName = "";
		    
		} else {
		    orgFileName = (String)request.getAttribute("orginFile");		    
		}
	
		File file = new File(downFileName);
	
		if (!file.exists()) {
		    throw new FileNotFoundException(downFileName);
		}
	
		if (!file.isFile()) {
		    throw new FileNotFoundException(downFileName);
		}
		
		int fileSize = ((int)request.getAttribute("fileSize"));
	
		byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
	
		
		String browser = getBrowser(request);
		/*
		if ("Opera".equals(browser)){
		    response.setContentType("application/octet-stream;charset=UTF-8");
		} else {
			response.setContentType("application/x-msdownload");
		}*/				
		
		response.setContentType("application/octet-stream;charset=UTF-8");
		
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		
		if (browser.equals("MSIE")) {
		    encodedFilename = URLEncoder.encode(orgFileName, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
		    encodedFilename = "\"" + new String(orgFileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
		    encodedFilename = "\"" + new String(orgFileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			encodedFilename = new String(orgFileName.getBytes("UTF-8"), "ISO-8859-1");
		} else {
		    //throw new RuntimeException("Not supported browser");
		    throw new IOException("Not supported browser");
		}
		
		response.setContentLength(fileSize);
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
        //response.setHeader("Access-Control-Expose-Headers", "filename");
        //response.setHeader("filename", orgFileName);  
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
 	    //파일복사
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();
		response.getOutputStream().flush();
		response.getOutputStream().close();
    }

    /**
     * 브라우저 구분 얻기.
     * 
     * @param request
     * @return
     */
    private static String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }
    

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     * 
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
	InputStream stream = null;
	OutputStream bos = null;
	
	try {
	    stream = file.getInputStream();
	    File cFile = new File(stordFilePath);

	    if (!cFile.isDirectory())
		cFile.mkdir();

	    bos = new FileOutputStream(stordFilePath + File.separator + newName);

	    int bytesRead = 0;
	    byte[] buffer = new byte[BUFF_SIZE];

	    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
		bos.write(buffer, 0, bytesRead);
	    }
	} catch (FileNotFoundException fnfe) {
	    fnfe.printStackTrace();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (bos != null) {
		try {
		    bos.close();
		} catch (Exception ignore) {
			ignore.printStackTrace();
		}
	    }
	    if (stream != null) {
		try {
		    stream.close();
		} catch (Exception ignore) {
			ignore.printStackTrace();
		}
	    }
	}
    }

    /**
     * 서버 파일에 대하여 다운로드를 처리한다.
     * 
     * @param response
     * @param streFileNm
     *            : 파일저장 경로가 포함된 형태
     * @param orignFileNm
     * @throws Exception
     */
    public void downFile(HttpServletResponse response, String streFileNm, String orignFileNm) throws Exception {
	String downFileName = streFileNm;
	String orgFileName = orignFileNm;

	File file = new File(downFileName);
	//log.debug(this.getClass().getName()+" downFile downFileName "+downFileName);
	//log.debug(this.getClass().getName()+" downFile orgFileName "+orgFileName);

	if (!file.exists()) {
	    throw new FileNotFoundException(downFileName);
	}

	if (!file.isFile()) {
	    throw new FileNotFoundException(downFileName);
	}

	//byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
	int fSize = (int)file.length();
	if (fSize > 0) {
	    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
	    String mimetype = "text/html"; //"application/x-msdownload"

	    response.setBufferSize(fSize);
	    response.setContentType(mimetype);
	    response.setHeader("Content-Disposition:", "attachment; filename=" + orgFileName);
	    response.setContentLength(fSize);
	    //response.setHeader("Content-Transfer-Encoding","binary");
	    //response.setHeader("Pragma","no-cache");
	    //response.setHeader("Expires","0");
	    FileCopyUtils.copy(in, response.getOutputStream());
	    in.close();
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
		
    }
}
