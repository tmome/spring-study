package sample.springstudy.domain.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@OpenAPIDefinition
@Profile("!prod")
public class SpringdocConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}

  public Info info() {
    return new Info()
        .title("SpringBoot board sample application")
        .description("스프링 부트 게시판 샘플 어플리케이션 입니다.")
        .version("v1")
        .contact(new Contact()
            .email("effortsof@gmail.com")
            .name("CHEON HYUN SEUNG")
            .url("https://github.com/tmome"));
  }
}
