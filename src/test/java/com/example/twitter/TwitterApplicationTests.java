package com.example.twitter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TwitterApplicationTests {

	@Test
	void contextLoads() {
        System.out.println("Context loaded successfully!");
        System.out.println(
            "Test profile is active: " + System.getProperty("spring.profiles.active")
        );
	}

}
