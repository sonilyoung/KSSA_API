package egovframework.com.file.service;

import java.util.List;

import egovframework.com.file.vo.AttachFile;


/**
 * 파일 Storage 관련 Service
 * 
 * @fileName : FileStorageService.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
public interface FileService {
	
    List<AttachFile> selectFileAll(AttachFile atchFileId);

    AttachFile selectFile(AttachFile param);

    int insertFile(List<AttachFile> param);

    int deleteFile(AttachFile param);

    int deleteFileAll(AttachFile atchFileId);

    
}
