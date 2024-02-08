package com.ait.grooming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@PropertySource("classpath:application-local.properties")
public class GroomingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GroomingApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setActiveProfiles("develop");
    }
}
