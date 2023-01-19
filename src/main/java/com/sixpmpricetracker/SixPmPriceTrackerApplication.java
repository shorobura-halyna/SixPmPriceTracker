package com.sixpmpricetracker;

import com.sixpmpricetracker.properties.TelegramBotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties(TelegramBotProperties.class)
@SpringBootApplication
public class SixPmPriceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SixPmPriceTrackerApplication.class, args);
	}
}
