package egovframework.com.file.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.file.dao.FileDAO;
import egovframework.com.file.vo.AttachFile;
import egovframework.com.global.common.GlobalsProperties;

/**
 * FileStorageService 구현체로 서블릿 설치 경로에 파일 저장 (※파일 Storage 결정 시까지 임시로 사용)
 * 
 * @fileName : ServletFileStorageService.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Service
public class FileServiceImpl implements FileService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
	
    @Resource(name = "FileDAO")
	private FileDAO fileDAO;	
    
    @Autowired
    private FileStorageService fileStorageService;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AttachFile> selectFileAll(AttachFile param) {
		// TODO Auto-generated method stub
		return (List<AttachFile>) fileDAO.selectFileAll(param);
	}
	

	@Override
	public AttachFile selectFile(AttachFile param) {
		// TODO Auto-generated method stub
		return fileDAO.selectFile(param);
	}

	@Override
	@Transactional
	public int insertFile(List<AttachFile> param) {
		// TODO Auto-generated method stub
		int result = 0;
		for(AttachFile af : param) {
			result = fileDAO.insertFile(af);
		}
		return result;
	}

    @Override
    @Transactional
	public int deleteFile(AttachFile param) {
		// TODO Auto-generated method stub
    	AttachFile attachDetail = selectFile(param);

    	// 파일 삭제
        fileStorageService.deleteFile(attachDetail);
        
        // DB 정보 삭제
        int iRslt = fileDAO.deleteFile(param);
        return iRslt;
	}

	@Override
	public int deleteFileAll(AttachFile param) {
		// TODO Auto-generated method stub
		return fileDAO.deleteFileAll(param);
	}



}
