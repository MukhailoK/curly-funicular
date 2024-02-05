package com.ait.grooming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-local.properties")
public class GroomingApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(GroomingApplication.class, args);

    }

}
