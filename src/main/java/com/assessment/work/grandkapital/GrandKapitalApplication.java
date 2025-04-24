package com.assessment.work.grandkapital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GrandKapitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrandKapitalApplication.class, args);
    }
}