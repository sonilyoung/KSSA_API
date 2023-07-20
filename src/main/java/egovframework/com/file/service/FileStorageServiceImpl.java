package egovframework.com.file.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.junit.experimental.theories.Theory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.file.vo.AttachFile;
import egovframework.com.global.common.GlobalsProperties;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;

/**
 * FileStorageService 구현체로 서블릿 설치 경로에 파일 저장 (※파일 Storage 결정 시까지 임시로 사용)
 * 
 * @fileName : ServletFileStorageService.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageServiceImpl.class);
	
    @Autowired
    private ServletContext servletContext;

    private String realPath;
    
    /*단품 저장경로*/
    public static final String UNIT_ROOT_DIR = GlobalsProperties.getProperty("xray.unitImg.path");    

    /*xray 저장경로*/
    public static final String XRAY_ROOT_DIR = GlobalsProperties.getProperty("xray.img.path");
    
    /*이론 저장경로*/
    public static final String THEORY_ROOT_DIR = GlobalsProperties.getProperty("theory.img.path");
    
    /*파일업로드 저장경로*/
    public static final String FILE_UPLOAD_PATH = GlobalsProperties.getProperty("file.upload.path");
    
    /*파일업로드 저장경로*/
    public static final String FILE_DB_UPLOAD_PATH = GlobalsProperties.getProperty("file.db.upload.path");    
    
    @PostConstruct
    public void initialize() {
        this.realPath = servletContext.getRealPath("/");
    }

    @Override
    public AttachFile createFile(MultipartFile file) throws Exception {
        AttachFile attachFile = null;
        File newFile = null;
        String originalFileName = file.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        String filePath = FILE_UPLOAD_PATH;
        File fileDir = new File(filePath);
        // root directory 없으면 생성
    	if (!fileDir.exists()) {
    		fileDir.mkdirs(); //폴더 생성합니다.
    	}        
        
        // 실제 파일명_현재시간 으로 rename
        StringBuilder sb = new StringBuilder();
        //sb.append(originalFileName.substring(0, originalFileName.indexOf(fileExtension) - 1));
        //sb.append("_").append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))
        sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))
                .append(".").append(fileExtension);
        String realFileName = sb.toString();
        try {
            newFile = new File(filePath, realFileName);
            file.transferTo(newFile);
            attachFile = new AttachFile();
            attachFile.setFileExt(fileExtension);
            attachFile.setFilePath(FILE_DB_UPLOAD_PATH);
            attachFile.setOriginalFileName(originalFileName);
            attachFile.setSaveFileName(realFileName);
            attachFile.setFileSize((int) file.getSize());
        } catch (IllegalStateException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        return attachFile;
    }

    @Override
    public boolean deleteFile(AttachFile AttachFile) {
        File file = getFile(AttachFile);
        boolean bDeleted = false;
        if (file.exists()) {
            bDeleted = file.delete();
        }
        return bDeleted;
    }

    @Override
    public File getFile(AttachFile AttachFile) {
        File file = new File(AttachFile.getFilePath(), AttachFile.getSaveFileName());
        return file;
    }
    
    /**
     * 이미지정보 가져오기
     */
    @Override
    public void getImage(String imgPath, HttpServletResponse response) throws Exception{
    	
    	File file = new File(imgPath);
    	if(!file.isFile()){
    		throw new BaseException(BaseResponseCode.DATA_IS_NULL);
    	}
    	
    	FileInputStream fis = null;
    	new FileInputStream(file);
    	
    	BufferedInputStream in = null;
    	ByteArrayOutputStream bStream = null;
    	try {
    		fis = new FileInputStream(file);
    		in = new BufferedInputStream(fis);
    		bStream = new ByteArrayOutputStream();
    		int imgByte;
    		while ((imgByte = in.read()) != -1) {
    			bStream.write(imgByte);
    		}

    		String type = "";
    		String ext = FilenameUtils.getExtension(file.getName());
    		if (ext != null && !"".equals(ext)) {
    			if ("jpg".equals(ext.toLowerCase())) {
    				type = "image/jpeg";
    			} else {
    				type = "image/" + ext.toLowerCase();
    			}

    		} else {
    			LOGGER.debug("Image fileType is null.");
    		}

    		response.setHeader("Content-Type", type);
    		response.setContentLength(bStream.size());

    		bStream.writeTo(response.getOutputStream());

    		response.getOutputStream().flush();
    		response.getOutputStream().close();

    	} catch (Exception e) {
    		LOGGER.debug("{}", e);
    	} finally {
    		if (bStream != null) {
    			try {
    				bStream.close();
    			} catch (Exception est) {
    				LOGGER.debug("IGNORED: {}", est.getMessage());
    			}
    		}
    		if (in != null) {
    			try {
    				in.close();
    			} catch (Exception ei) {
    				LOGGER.debug("IGNORED: {}", ei.getMessage());
    			}
    		}
    		if (fis != null) {
    			try {
    				fis.close();
    			} catch (Exception efis) {
    				LOGGER.debug("IGNORED: {}", efis.getMessage());
    			}
    		}
    	}
    }



}
