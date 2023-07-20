package egovframework.com.global.http;

/**
 * 응답 코드 정의 클래스
 * 
 * @author jkj0411
 *
 */
public enum BaseApiMessage {
	REQUIRED(
			" Required", " 은(는) 필수값"
	), INFORMATION_ERROR(
			"9999", "입력 정보 없음");
	

	private String code;
	private String message;

	private BaseApiMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
