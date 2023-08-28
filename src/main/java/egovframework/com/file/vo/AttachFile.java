package egovframework.com.file.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 파일 첨부 상세
 * 
 * @fileName : AttachFile.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachFile {
	private Long attachFileId;
	private Long fileTarget;
    private int fileSn;
    private String originalFileName;
    private String saveFileName; 
    private String filePath;
    private String fileExt;
    private int fileSize;
    private String insertDate;
    private String insertId;
    
    private String targetName;
    private String command;
}
