package egovframework.com.global.session;

import lombok.Data;

/**
 *
 * @fileName : SessionUserInfo.java
 * @author : YeongJun Lee
 * @date : 2022.06.09
 */
@Data
public class SessionUserInfo {
    private long sessionUserId;
    private String loginId;
}
