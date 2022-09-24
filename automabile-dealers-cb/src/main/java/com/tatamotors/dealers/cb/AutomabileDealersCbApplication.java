package com.tatamotors.dealers.cb;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tatamotors.dealers.cb.dto.StockDto;
import com.tatamotors.dealers.cb.services.StockService;

@SpringBootApplication
public class AutomabileDealersCbApplication {


	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(AutomabileDealersCbApplication.class, args);
		StockService discoveryManager = context.getBean(StockService.class);

		//for (int i = 0; i <10; i++) {
			
		
		List<StockDto> stocks= discoveryManager.getStocks("spare");
      System.out.println("Stock ::"+stocks);
      stocks.stream().forEach(System.out::println);
      
	//	}
	}


}
