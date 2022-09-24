package com.tatamotors.dealers.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tatamotors.dealers.dto.StockDto;


@FeignClient(name="INVENTORY-MGMT-SERVICE")
public interface StockManagerService {
	
	@GetMapping(value ="/stock/{stockName}/available" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<StockDto> getAllAvailableStock(@PathVariable("stockName") String stockName);
}
