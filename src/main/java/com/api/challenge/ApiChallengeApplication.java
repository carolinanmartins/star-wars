package com.api.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ConfigurationPropertiesScan("com.api.challenge.config")
@SpringBootApplication
//@ComponentScan(basePackages = {"com.api.challenge.service", "com.api.challenge.service.mapper"})
public class ApiChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiChallengeApplication.class, args);
    }

}
