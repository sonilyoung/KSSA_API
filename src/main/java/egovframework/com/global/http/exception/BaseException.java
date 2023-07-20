package egovframework.com.global.http.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import egovframework.com.global.http.BaseResponseCode;

public class BaseException extends AbstractBaseException {


	private static final long serialVersionUID = -4560458974750914172L;

	Logger logger = LoggerFactory.getLogger(getClass());

	public BaseException() {

	}

	public BaseException(BaseResponseCode responseCode) {

		this.responseCode = responseCode;
		logger.debug("BaseResponseCode = {} , {}", this.responseCode.getCode(),
				this.responseCode.getMessage());
	}

	public BaseException(BaseResponseCode responseCode, String[] args) {
		this.responseCode = responseCode;
		this.args = args;
		logger.debug("BaseResponseCode = {} , {}", this.responseCode.getCode(),
				this.responseCode.getMessage());
	}
	
	public BaseException(BaseResponseCode responseCode, String args) {
		this.responseCode = responseCode;
		this.message = args;
	}	
}
