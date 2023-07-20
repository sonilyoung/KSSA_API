package egovframework.com.global.http.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import egovframework.com.global.http.BaseCodeLabelEnum;
import egovframework.com.global.http.BaseCodeLabelEnumJsonSerializer;

public class JsonMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public JsonMapper() {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
		registerModule(simpleModule);
	}
}
