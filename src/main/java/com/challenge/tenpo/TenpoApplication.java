package com.challenge.tenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class TenpoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TenpoApplication.class, args);
	}

}
