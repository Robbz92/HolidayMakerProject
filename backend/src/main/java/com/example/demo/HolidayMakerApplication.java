package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HolidayMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayMakerApplication.class, args);

		System.out.println("Nu är det fredag!");
	}

}
