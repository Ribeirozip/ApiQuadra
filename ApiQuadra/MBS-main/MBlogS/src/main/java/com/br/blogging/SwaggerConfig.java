package com.br.blogging;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API MBS- Mensagens de Bem e Sabedoria")
                .version("v1")
                .description("api de Blog.")
                .termsOfService("URL")
                .license(new License().name("Apache 2.0").url("URL")));
    }

}
