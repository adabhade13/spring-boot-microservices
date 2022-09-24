package com.tatamotors.dealers;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import com.tatamotors.dealers.dto.StockDto;
import com.tatamotors.dealers.service.StockManagerService;

@SpringBootApplication
@EnableFeignClients
public class AutomabileFeignApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AutomabileFeignApplication.class, args);
       StockManagerService bean = context.getBean(StockManagerService.class);
    for (int i = 0; i < 10; i++) {
    	   List<StockDto> stocks = bean.getAllAvailableStock("spare");
           stocks.stream().forEach(System.out::println);
	}
    
	}

}
