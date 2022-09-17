package it.epicode.be.energy.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "Epic Energy System REST API", version = "v1", description = "Epic Energy System REST Information", contact = @Contact(email = "dcerulli.dc@gmail.com", name = "Daniele Cerulli", url = "https://www.instagram.com/daniele.cer/?hl=it")))
@Configuration
@SecurityScheme(
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
		)
public class SwaggerConfig {

}
