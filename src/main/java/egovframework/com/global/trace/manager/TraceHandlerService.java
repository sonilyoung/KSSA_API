package egovframework.com.global.trace.manager;


import egovframework.com.global.trace.handler.TraceHandler;

import org.springframework.util.PathMatcher;

public interface TraceHandlerService {

	/**
	 * 패키지, 클래스 이름으로 패턴등록(Ant형식의 매칭).
	 * 
	 * @param patterns 패턴리스트
	 */

	public void setPatterns(String[] patterns);

	/**
	 * ExceptionHandler 리스트 등록.
	 * 
	 * @param handlers handler리스트
	 */
	public void setHandlers(TraceHandler[] handlers);

	/**
	 * 비교할 클래스 정보.
	 * 
	 * @param canonicalName 비교할 클래스명
	 */
	public void setPackageName(String canonicalName);

	/**
	 * setReqExpMatcher 메소드.
	 * 
	 * @param pm 사용자에 의해 사용하고자하는 PathMatcher 
	 */
	public void setReqExpMatcher(PathMatcher pm);

	/**
	 * PathMatcher 가 있는지 여부 반환.
	 * 
	 * @return boolean true|false
	 */
	public boolean hasReqExpMatcher();

	/**
	 * trace 메소드.
	 * 
	 * @param clazz 클래스정보
	 * @param message 보여주고자하는 메세지
	 * @return boolean true|false
	 */
	public boolean trace(Class<?> clazz, String message);

}