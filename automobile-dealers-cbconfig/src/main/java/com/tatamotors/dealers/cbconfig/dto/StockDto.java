package com.tatamotors.dealers.cbconfig.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockDto {
	private int stockNo;
	private String stockName;
	private String description;
	private int quantity;
	private double unitPrice;
}
