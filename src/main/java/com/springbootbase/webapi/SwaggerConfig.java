package com.springbootbase.webapi;


import org.springdoc.core.customizers.OperationCustomizer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.Parameter;


@Configuration

public class SwaggerConfig {


	@Bean
	public OpenAPI springBootOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Spring Boot Base Wep API")
				.description("Spring Boot base REST Web API")
				.version("1.0.0")
				.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
				.externalDocs(new ExternalDocumentation()
				.description("Spring Boot Base documentation")
				.url("https://www.wikipedia.org"));
	}

	@Bean
	public OperationCustomizer customize() {
	return (operation, handlerMethod) -> operation.addParametersItem(
			new Parameter()
					.in("header")
					.required(true)
					.description("APIKEY")
					.name("APIKEY"));
	}

}
