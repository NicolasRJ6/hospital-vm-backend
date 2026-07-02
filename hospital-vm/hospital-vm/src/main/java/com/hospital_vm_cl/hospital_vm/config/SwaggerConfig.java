package com.hospital_vm_cl.hospital_vm.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 2026 Hospital Vida y Meditación")
                        .version("1.0")
                        .description("Documentación de la API para el sistema de gestión de pacientes y microservicios."))
                .addSecurityItem(new SecurityRequirement().addList("JavaBearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("JavaBearerAuth", new SecurityScheme()
                                .name("JavaBearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}