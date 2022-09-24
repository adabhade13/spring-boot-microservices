package com.tatamotor.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tatamotor.dto.StockDto;
import com.tatamotor.service.StockService;
import com.tatamotor.service.impl.StockServiceImpl;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockServiceImpl stockService;

	@GetMapping("{stockName}/available")
	public List<StockDto> getAllAvailableStock(@PathVariable("stockName") String stockName){
		System.out.println("#############"+this.hashCode());
		return stockService.getAvailableStocks(stockName);	
	}
	
	@GetMapping("details")
	public String getTransportDetails() throws JsonProcessingException {
		Map<String, Object> dataMap = null;
		
		dataMap = new HashMap<>();
		dataMap.put("transportType", stockService.getTransportType());
		dataMap.put("slaDay", stockService.getSlaDay());
		return new ObjectMapper().writeValueAsString(dataMap);
	}
}
