package com.inditex.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.inditex.application", "com.inditex.infrastructure"})
@EnableJpaRepositories("com.inditex.infrastructure.outbound.persistence.repositories")
@EntityScan("com.inditex.infrastructure.outbound.persistence.entities")
public class ProductPriceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductPriceServiceApplication.class, args);
    }

}
