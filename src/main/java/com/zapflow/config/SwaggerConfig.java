package com.zapflow.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "ZapFlow API", version = "v1", description = "API do backend ZapFlow")
)
public class SwaggerConfig {}
