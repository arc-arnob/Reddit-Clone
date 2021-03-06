package com.booking.booking_per_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookingPerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingPerUserApplication.class, args);
	}

}
