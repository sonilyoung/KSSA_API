package egovframework.com.global.http;

/**
 * 응답 코드 정의 클래스
 * 
 * @author jkj0411
 *
 */
public enum BaseResponseCode {
	SUCCESS(
			"0000", "성공"
	), SAVE_SUCCESS(
			"0100", "저장완료"
	), UPLOAD_SUCCESS(
			"0100", "업로드완료"			
	), UPDATE_SUCCESS(
			"0200", "수정완료"
	), DELETE_SUCCESS(
			"0300", "삭제되었습니다."	
	), DELETE_ERROR(
			"0400", "삭제 중 내부 오류가 발생"
	), SAVE_ERROR(
			"0500", "저장시 내부 오류가 발생"
	), UPDATE_ERROR(
			"0600", "수정시 내부 오류가 발생"
	), DATA_IS_DUPLICATE(
			"0700", "중복된 데이터가 있습니다."
	), DATA_IS_NULL(
			"0800", "데이터가 없습니다."
	), INPUT_CHECK_ERROR(
			"0900", "입력값 무결성 오류"
	), BASELINE_DATA(
			"0901", "차수정보가 없습니다."			
	), EDU_DATA(
			"0902", "교육정보가 없습니다."		
	), MODULE_DATA(
			"0903", "모듈정보가 없습니다."
	), THEORY_DATA(
			"0904", "등록이론문제가 없습니다."				
	), LEARNINGPROBLEM_DATA(
			"0904", "등록학습문제가 없습니다."			
	), WRONGANSWERPROBLEM_DATA(
			"0905", "등록된 오답문제가 없습니다."	
	), EVALUATIONPROBLEM_DATA(
			"0906", "등록된 오답문제가 없습니다."				
	), PARAMS_ERROR(
	        "1000", "파라미터 오류"	        
	), EXTENSION_ERROR(
	        "2000", "파일확장자 오류"
	), SAME_ERROR(
	        "3000", "동일한 데이터가 존재합니다."
	), EXCEL_TYPE(
	        "9997", "엑셀형식이 잘못되었습니다."
	), EXGIST_USERS(		
			"9996", "이미존재하는 아이디입니다."	     
	), NO_ID(		
			"9998", "사용가능한 아이디입니다."				
	), NO_USERS(		
			"9995", "사용자가 존재하지 않습니다."	        
	), AUTH_FAIL(		
			"0401", "인증실패"
	), AUTH_ERROR(
			"0403", "인증된 사용자가 아닙니다."
	), STOP_ID(
	        "7000", "계정이 중지되었습니다."
	), ALREADY_STARE(
	        "ALREADY_STARE", "이미 시험에 응시했습니다."
	), DATA_IS_NULL_LAERNPROBLEMS(
	        "LEARN_NODATA", "학습문제가 없습니다."
	), DATA_IS_NULL_EVALPROBLEMS(
	        "EVALUATION_NODATA", "평가문제가 없습니다."
	), PASS(
	        "pass", "합격"
	), FAIL(
	        "fail", "불합격"	        
	), UNKONWN_ERROR(
	        "9999", "내부 오류가 발생");			
	

	private String code;
	private String message;

	private BaseResponseCode(String code, String message) {
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
