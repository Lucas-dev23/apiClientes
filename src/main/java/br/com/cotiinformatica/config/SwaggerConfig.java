package br.com.cotiinformatica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	   OpenAPI customOpenApi() {
	       OpenAPI openAPI = new OpenAPI()
	    		   .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
	    		   .components(new Components()
	    		       .addSecuritySchemes("BearerAuth",
	    		           new SecurityScheme()
	    		               .name("BearerAuth")
	    		               .type(SecurityScheme.Type.HTTP)
	    		               .scheme("bearer")
	    		               .bearerFormat("JWT")))
	               .info(new Info()
	                       .title("apiClientes - API RESTful para controle de clientes.")
	                       .description("Treinamento Java WebDeveloper – COTI Informática.")
	                       .version("v1")
	                       .contact(new Contact()
	                               .name("Suporte COTI")
	                               .email("suporte@cotiinformatica.com.br")
	                               .url("https://www.cotiinformatica.com.br"))
	                       .termsOfService("https://www.cotiinformatica.com.br")
	                       .license(new License()
	                               .name("MIT License")
	                               .url("https://opensource.org/licenses/MIT")))
	               .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	       return openAPI;
	   }
}
