package ru.kerporation.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger
public class SwaggerConfig {

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public SwaggerSpringMvcPlugin configureSwagger() {
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);

		ApiInfo apiInfo = new ApiInfoBuilder()
							        .title("Loans REST API")
							        .description("Тестовое задание")
							        .build();

		swaggerSpringMvcPlugin
					.apiInfo(apiInfo)
					.apiVersion("1.0")
					.includePatterns("/apiloans/loans/*.*",
									 "/apiloans/users/*.*",
									 "/apiusers/users/*.*",
									 "/apiusers/blacklist",
									 "/apiusers/generate");

		swaggerSpringMvcPlugin.useDefaultResponseMessages(false);

	    return swaggerSpringMvcPlugin;
	}
}
