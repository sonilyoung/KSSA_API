package egovframework.com.global;

/**
 * ThreadLocal에 사용할 데이터소스 할당
 */
public class DynamicContextHolder {
	
	private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();
	
	public static void setDynamicType(String type) {
		contextHolder.set(type);
	}
	
	public static Object getDynamicType() {
		return contextHolder.get();
	}
	
	public static void clearDynamicType() {
		contextHolder.remove();
	}
	
}
