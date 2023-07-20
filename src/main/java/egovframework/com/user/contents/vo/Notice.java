package egovframework.com.user.contents.vo;

import lombok.Data;

@Data
public class Notice{

	private Long noticeId;
	private String title;
	private String contents;
	private String insertId;
	private String insertDate;
	private String updateId;
	private String updateDate;
	private String languageCode;
	
	
}
