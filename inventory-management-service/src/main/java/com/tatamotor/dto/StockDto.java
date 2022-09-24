package com.tatamotor.dto;

public class StockDto {
	private int stockNo;
	private String stockName;
	private String description;
	private int quantity;
	private double unitPrice;
	public int getStockNo() {
		return stockNo;
	}
	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Override
	public String toString() {
		return "Stock [stockNo=" + stockNo + ", stockName=" + stockName + ", description=" + description + ", quantity="
				+ quantity + ", unitPrice=" + unitPrice + "]";
	}


}
