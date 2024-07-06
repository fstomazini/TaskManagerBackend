package com.example.workmannager;

import com.example.workmannager.config.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class WorkMannagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkMannagerApplication.class, args);
    }

}
