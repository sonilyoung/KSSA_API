package egovframework.com.global;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 멀티데이터소스를 사용하기 위해 AbstractRoutingDataSource 상속받음
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicContextHolder.getDynamicType();
	}

}
