package com.person.shop_data_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShopDataScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopDataScheduleApplication.class, args);
	}

}
