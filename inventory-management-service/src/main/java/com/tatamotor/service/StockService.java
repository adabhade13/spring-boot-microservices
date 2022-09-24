package com.tatamotor.service;

import java.util.List;

import com.tatamotor.dto.StockDto;

public interface StockService {
	
	List<StockDto> getAvailableStocks(String stockName);

}
