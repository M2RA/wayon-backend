package br.com.wayon.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("Wayon - Teste Matheus Malta de Aguiar")
                        .version("1.0.0")
                        .description("Desenvolvido por Matheus Malta de Aguiar (M2RA Informática)")
                        .contact(new Contact()
                                .name("Matheus Malta de Aguiar")
                                .email("maltamatheus@gmail.com"))
                );
    }
}
