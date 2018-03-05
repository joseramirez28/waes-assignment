package org.jr.waes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class WasServiceSwaggerConfig extends WebMvcConfigurationSupport {

	@Value("${server.servlet-path}")
	private String servletPath;

	@Bean
	public Docket generalAPIs() {
		return new Docket(DocumentationType.SWAGGER_2).pathMapping(servletPath)
				.apiInfo(new ApiInfoBuilder().title("Waes Assigment 'Scalable Web'")
						.description("Assignment code for Assigment, Spring Boot Microservice").build());
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
