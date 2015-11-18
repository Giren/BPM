package com.sample;

public class CarModel {
	public final static int smallClass = 40;
	public final static int compactClass = 50;
	public final static int middleClass = 70;
	public final static int upperClass = 90;
	
	private int price;
	
	public CarModel(int price){
		this.setPrice(price);
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
