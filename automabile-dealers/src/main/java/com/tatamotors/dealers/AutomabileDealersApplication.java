package com.tatamotors.dealers;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tatamotors.dealers.dto.StockDto;
import com.tatamotors.dealers.manager.helper.StockApiDiscoveryManager;
import com.tatamotors.dealers.service.StockManagerService;

@SpringBootApplication
@EnableDiscoveryClient
public class AutomabileDealersApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(AutomabileDealersApplication.class, args);
		StockManagerService discoveryManager = context.getBean(StockManagerService.class);

		for (int i = 0; i <10; i++) {
			
		
		List<StockDto> stocks= discoveryManager.getStocksAvailable("spare");
      
      stocks.stream().forEach(System.out::println);
      
		}
	}
}
