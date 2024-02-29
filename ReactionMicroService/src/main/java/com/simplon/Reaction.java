package com.simplon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Hello world!
 */

@SpringBootApplication
@EnableWebMvc
public class Reaction {
    public static void main(String[] args) {
        SpringApplication.run(Reaction.class, args);
    }

}
