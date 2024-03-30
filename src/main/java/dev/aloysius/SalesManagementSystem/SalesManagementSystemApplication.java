package dev.aloysius.SalesManagementSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalesManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagementSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {

		};
	}

}
