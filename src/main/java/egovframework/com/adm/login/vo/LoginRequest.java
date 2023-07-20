package egovframework.com.adm.login.vo;

import lombok.Data;

@Data
public class LoginRequest {
    private String loginId;
    private String loginPw;
    private String language;

}
