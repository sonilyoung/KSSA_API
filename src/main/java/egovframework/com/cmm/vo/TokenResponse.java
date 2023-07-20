package egovframework.com.cmm.vo;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private String tokenType;

    public TokenResponse(String token, String type) {
        this.accessToken = token;
        this.tokenType = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
