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
    private String dept;                        //부서        
    private String telNo;                      //전화번호        
    private String hpNo;                       //핸드폰번호        
    private String email;                       //이메일        
    private String firstLogin;                 //최초로그인        
    private String lastLogin;                  //최종로그인        
    //private String loginError;                 //로그인에러        
    private String prePw;                      //이전비밀번호        
    private String pwChangeDay;               //비밀번호변경일        
    private String pwChangeCycle;             //비밀번호변경주기        
    private String pwChangePeriod;            //비밀번호변경기간        
    private String useYn;                      //사용여부        
    private String regId;                      //등록자        
    private String regDt;                      //등록일시        
    private String updId;                      //수정자        
    private String updDt;                      //수정일시        
    //private String autoPlus;                   //자동증가        
    private String delYn;                      //삭제여부        
    private String delDt;                      //삭제일시   
}
