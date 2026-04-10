package com.example.sputnik_onegina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
	void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
		context.getBean(SpaceOperationController.class);
	}
}
