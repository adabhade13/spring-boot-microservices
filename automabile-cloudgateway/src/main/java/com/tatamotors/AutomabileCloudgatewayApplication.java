package com.tatamotors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AutomabileCloudgatewayApplication {
	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("stockList", r-> r.path("/inventory/**").uri("lb://INVENTORY-MGMT-SERVICE/")).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(AutomabileCloudgatewayApplication.class, args);
	}

}
