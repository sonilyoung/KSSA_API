package egovframework.com.file.service;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.junit.experimental.theories.Theory;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.file.vo.AttachFile;


/**
 * 파일 Storage 관련 Service
 * 
 * @fileName : FileStorageService.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
public interface FileStorageService {
	
    File getFile(AttachFile AttachFile);

    AttachFile createFile(MultipartFile file) throws Exception;

    boolean deleteFile(AttachFile AttachFile);
    
    void getImage(String filePath, HttpServletResponse response) throws Exception;

}
