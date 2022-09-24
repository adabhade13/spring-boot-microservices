package com.tatamotor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AutomobileConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomobileConfigserverApplication.class, args);
	}

}
