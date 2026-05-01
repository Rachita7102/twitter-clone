package com.example.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterApplication {

	public static void main(String[] args) {
        System.out.println("Starting Twitter Application...");
        System.out.println("Loading configuration with jenkins...");
		SpringApplication.run(TwitterApplication.class, args);
	}

}
