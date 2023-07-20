package egovframework.com.global.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import egovframework.com.global.http.BaseResponseCode;

public class CustomBaseException extends AbstractBaseException {


	private static final long serialVersionUID = -4560458974750914172L;

	Logger logger = LoggerFactory.getLogger(getClass());

	public CustomBaseException() {

	}

	public CustomBaseException(BaseResponseCode responseCode) {

		this.responseCode = responseCode;
		logger.debug("BaseResponseCode = {} , {}", this.responseCode.getCode(),
				this.responseCode.getMessage());
	}

	public CustomBaseException(BaseResponseCode responseCode, String[] args) {
		this.responseCode = responseCode;
		this.args = args;
		logger.debug("BaseResponseCode = {} , {}", this.responseCode.getCode(),
				this.responseCode.getMessage());
	}
	
	public CustomBaseException(BaseResponseCode responseCode, String args) {
		this.responseCode = responseCode;
		this.message = args;
	}	
}
