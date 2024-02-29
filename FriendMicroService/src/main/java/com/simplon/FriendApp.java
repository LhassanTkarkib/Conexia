package com.simplon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FriendApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(FriendApp.class, args);
    }
}
