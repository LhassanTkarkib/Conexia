package com.simplon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Hello world!
 */

@SpringBootApplication
@EnableWebMvc
@EnableDiscoveryClient
public class Reaction {
    public static void main(String[] args) {
        SpringApplication.run(Reaction.class, args);
    }

}
