package com.example.twitterapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.twitterapp.model")
public class BloggingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BloggingApplication.class, args);
    }
}
