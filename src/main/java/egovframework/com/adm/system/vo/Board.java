package egovframework.com.adm.system.vo;

import java.util.List;

import egovframework.com.file.vo.AttachFile;
import lombok.Data;

@Data
public class Board {
	
	private Long seqId;
	private String title;
	private String contents;
	private int hit;
	private String insertId;
	private String insertDate;
	private String updateId;
	private String userName;
	private String updateDate;
	private String command;
	private String searchTxt;
	
	List<AttachFile> attachFile;
	
	
}
