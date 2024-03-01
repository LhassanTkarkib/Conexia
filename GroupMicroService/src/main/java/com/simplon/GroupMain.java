package com.simplon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"com.simplon"})
@EnableDiscoveryClient
public class GroupMain {
    public static void main(String[] args) {
        SpringApplication.run(GroupMain.class, args);
    }
}