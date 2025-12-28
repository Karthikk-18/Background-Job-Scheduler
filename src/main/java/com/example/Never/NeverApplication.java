package com.example.Never;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeverApplication.class, args);
	}

}
