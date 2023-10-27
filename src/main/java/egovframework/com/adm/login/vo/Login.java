package egovframework.com.adm.login.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userNo;                     //사용자NO
	private String userId;                     //사용자ID        
    private String userNm;                     //사용자명        
    private String userPw;                     //사용자패스워드
    private String authCd;                     //권한코드 
    private String hpNo;                     
    private String firstLogin;                 //최초로그인        
    private String lastLogin;                  //최종로그인        
    private String useYn;                      //사용여부        
    private String regId;                      //등록자        
    private String regDt;                      //등록일시        
    private String company;                      //회사        
    private String telNo;                      //전화번호        
    private String dept;                      //부서        
}
