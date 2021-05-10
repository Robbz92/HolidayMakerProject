package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HolidayMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayMakerApplication.class, args);

		System.out.println("Nu är det fredag!");
	}
}