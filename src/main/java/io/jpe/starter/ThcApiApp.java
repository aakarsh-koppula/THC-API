package io.jpe.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.jpe.starter.security.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class ThcApiApp {
	private static final Logger logger = LogManager.getLogger(ThcApiApp.class);
	
	public static void main(String[] args) {
		
		logger.info("Starting the App");

		SpringApplication.run(ThcApiApp.class, args);
	}

}
