package egovframework.com.user.calendar.vo;

import java.util.List;

import lombok.Data;

@Data
public class Calendar{

	private Long seqId;
	private String userId;
	private String category;
	private String division;
	private String title;
	private String contents;
	private String eduStartDate;
	private String eduEndDate;
	private String insertId;
	private String insertDate;
	private String updateId;
	private String updateDate;
	private String company;
	private int studentCnt;
	private int applyStudentCnt;
	
	List<Long> seqIdList;	
	
}
