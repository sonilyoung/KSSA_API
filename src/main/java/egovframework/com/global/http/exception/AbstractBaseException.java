package egovframework.com.global.http.exception;

import egovframework.com.global.http.BaseResponseCode;

public class AbstractBaseException extends RuntimeException {
	private static final long serialVerionUID = 8342235231880246631L;
	protected BaseResponseCode responseCode;
	protected String message;
	protected Object[] args;

	public AbstractBaseException() {

	}

	public AbstractBaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public BaseResponseCode getResponseCode() {
		return responseCode;
	}

	public Object[] getArgs() {
		return args;
	}
	

	public String getMessage() {
		return message;
	}

}
