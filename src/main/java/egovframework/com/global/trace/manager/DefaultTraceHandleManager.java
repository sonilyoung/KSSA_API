package egovframework.com.global.trace.manager;


import egovframework.com.global.trace.handler.TraceHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTraceHandleManager extends AbstractTraceHandleManager implements TraceHandlerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTraceHandleManager.class);

	/**
	 * trace 메소드.
	 *
	 * @param clazz 클래스정보
	 * @param message 보여주고자하는 메세지
	 * @return boolean true|false
	 */
	@Override
	public boolean trace(Class<?> clazz, String message) {
		LOGGER.debug(" DefaultExceptionHandleManager.run() ");

		// 매칭조건이 false 인 경우
		if (!enableMatcher()) {
			return false;
		}

		for (String pattern : patterns) {
			LOGGER.debug("pattern = {}, thisPackageName = {}", pattern, getPackageName());
			LOGGER.debug("pm.match(pattern, getPackageName()) = {}", pm.match(pattern, getPackageName()));
			if (pm.match(pattern, getPackageName())) {
				for (TraceHandler eh : handlers) {
					eh.todo(clazz, message);
					LOGGER.debug("trace end?");
				}
				break;
			}
		}

		return true;
	}

}
