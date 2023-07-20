package egovframework.com.global.trace.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTraceHandler implements TraceHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTraceHandler.class);

	public void todo(Class<?> clazz, String message) {

		LOGGER.info("DefaultTraceHandler run...............");
	}
}
