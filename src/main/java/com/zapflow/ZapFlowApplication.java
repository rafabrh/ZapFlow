package com.zapflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(scanBasePackages = "com.zapflow")
@EnableKafka
@EnableRedisRepositories(basePackages = "com.zapflow.repository")
@OpenAPIDefinition(
        info = @Info(
                title = "ZapFlow API",
                version = "v1",
                description = "API do backend ZapFlow"
        )
)
public class ZapFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZapFlowApplication.class, args);
    }
}
