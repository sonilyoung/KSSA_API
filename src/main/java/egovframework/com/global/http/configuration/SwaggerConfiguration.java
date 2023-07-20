package egovframework.com.global.http.configuration;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 설정
 * 
 * @author jkj0411
 *
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration {
	@Bean
	public Docket docket() {
		ApiInfoBuilder apiInfo = new ApiInfoBuilder();
		apiInfo.title("RISK-FREE API server documentation");
		apiInfo.description("API server documentation.");

	     Docket docket = new Docket(DocumentationType.SWAGGER_2);
	        docket.apiInfo(apiInfo.build()).securityContexts(Arrays.asList(securityContext()))
	                .securitySchemes(Arrays.asList(apiKey()));
	        ApiSelectorBuilder apis = docket.select()
	                .apis(RequestHandlerSelectors.basePackage("egovframework.com"));
	        apis.paths(PathSelectors.ant("/**"));

	        return apis.build();
	    }

	    private SecurityContext securityContext() {
	        return SecurityContext.builder().securityReferences(defaultAuth()).build();
	    }

	    private List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope =
	                new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
	    }

	    private ApiKey apiKey() {
	        return new ApiKey("Authorization", "Authorization", "header");
	    }
}
