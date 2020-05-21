package com.shadow.technology_integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TechnologyIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnologyIntegrationApplication.class, args);
    }

}
