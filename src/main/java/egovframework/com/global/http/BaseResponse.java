package egovframework.com.global.http;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 공통으로 사용할 응답 클래스
 * 
 * @author jkj0411
 *
 * @param <T>
 */
@Data
public class BaseResponse<T> {
    // private BaseResponseCode code;
    @JsonProperty("RET_CODE")
    private String ret_code;

    @JsonProperty("RET_DESC")
    private String ret_desc;

    @JsonProperty("RET_DATA")
    private T ret_data;

    public BaseResponse(T data) {
        this.ret_code = BaseResponseCode.SUCCESS.getCode();
        this.ret_data = data;
    }

    public BaseResponse(BaseResponseCode baseResponseCode) {
        this.ret_code = baseResponseCode.getCode();
        this.ret_desc = baseResponseCode.getMessage();
    }

    public BaseResponse(BaseResponseCode baseResponseCode, String message) {
        this.ret_code = baseResponseCode.getCode();
        this.ret_desc = message;
    }
    
    public BaseResponse(BaseResponseCode baseResponseCode, String message, T ret_data) {
        this.ret_code = baseResponseCode.getCode();
        this.ret_desc = message;
        this.ret_data = ret_data;
    }    

    public T getRet_data() {
        return ret_data;
    }

    public void setRet_data(T ret_data) {
        this.ret_data = ret_data;
    }

}
