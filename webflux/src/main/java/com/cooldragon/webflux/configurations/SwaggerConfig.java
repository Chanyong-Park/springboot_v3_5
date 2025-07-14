package com.cooldragon.webflux.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    final String jwtSchemeName = "jwtAuth";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(new Info()
                .title("Sample for WebFlux JWT")
                .version("v1.0.0")
                .description("samples")
                )
            .addSecurityItem(new SecurityRequirement().addList(jwtSchemeName))
            .components(new Components()
                .addSecuritySchemes(jwtSchemeName,
                    new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            )
            ;
    }
}
