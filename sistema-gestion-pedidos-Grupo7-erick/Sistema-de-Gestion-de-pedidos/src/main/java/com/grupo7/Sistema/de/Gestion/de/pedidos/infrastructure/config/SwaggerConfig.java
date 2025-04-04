package com.grupo7.Sistema.de.Gestion.de.pedidos.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Reservation Management API")
                        .version("1.0")
                        .description("API documentation for Reservation Management by Coworking Space"));
    }

    @Bean
    public GroupedOpenApi roomApi() {
        return GroupedOpenApi.builder()
                .group("reservation")
                .pathsToMatch("/api/v1/reserves/**")
                .build();
    }
}
