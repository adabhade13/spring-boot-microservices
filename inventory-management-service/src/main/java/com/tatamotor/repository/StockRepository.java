package com.tatamotor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatamotor.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	public List<Stock> findByStockNameLike(String stockName);

}
