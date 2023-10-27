package egovframework.com.user.calendar.vo;

import java.util.List;

import lombok.Data;

@Data
public class Calendar{

	private String seqId;
	private String userId;
	private String category;
	private String title;
	private String contents;
	private String eduStartDate;
	private String eduEndDate;
	private String insertId;
	private String insertDate;
	private String updateId;
	private String updateDate;
	private String company;
	
	List<Long> seqIdList;	
	
}
