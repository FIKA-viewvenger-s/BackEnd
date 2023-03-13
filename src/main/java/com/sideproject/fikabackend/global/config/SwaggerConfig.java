package com.sideproject.fikabackend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "[FIKA_DEV] FIKA REST API",
                description = "[sideProject] Fika BackEnd Rest API Test",
                version = "v1.0"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi fikaOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("FIKA API v1")
                .pathsToMatch(paths)
                .build();
    }
}
