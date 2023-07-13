package com.api.challenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ConfigurationPropertiesScan("com.api.challenge.config")
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableTransactionManagement(mode= AdviceMode.ASPECTJ)
@OpenAPIDefinition(info = @Info(title = "Starwars API", version = "1.0", description = "Starwars API"))
public class ApiChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiChallengeApplication.class, args);
    }

}