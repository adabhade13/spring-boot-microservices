package com.tatamotor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.tatamotor.dto.StockDto;
import com.tatamotor.entity.Stock;
import com.tatamotor.repository.StockRepository;
import com.tatamotor.service.StockService;

@Service
@ConfigurationProperties(prefix = "stock")
public class StockServiceImpl /* implements StockService */{
	@Autowired
	private StockRepository stockRepository;
	
	private String transportType;
	private int slaDay;
	

	//@Override
	public List<StockDto> getAvailableStocks(String stockName) {
		// TODO Auto-generated method stub
		List<Stock> stockNameLike = stockRepository.findByStockNameLike(stockName+"%");
	  System.out.println("Response ::"+stockNameLike);
		List<StockDto> dtoList = new ArrayList<StockDto>();
		for(Stock stock : stockNameLike) {
			 StockDto dto = new StockDto();
			 BeanUtils.copyProperties(stock, dto);
			 dtoList.add(dto);
		}
		  System.out.println("Response ::"+dtoList);
			return dtoList;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public int getSlaDay() {
		return slaDay;
	}

	public void setSlaDay(int slaDay) {
		this.slaDay = slaDay;
	}
	
	

	
}
