package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class inventoryapi {
    public static void main(String[] args)
    {
        SpringApplication.run(inventoryapi.class,
                args);
    }
}
