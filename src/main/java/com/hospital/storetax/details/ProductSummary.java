package com.hospital.storetax.details;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

public class ProductSummary {
	List<ReceiptDetails> products= new ArrayList<ReceiptDetails>();
	private double totalPrice;
	private double totalTax;
	private double grossPrice;
	public List<ReceiptDetails> getProducts() {
		return products;
	}
	public void setProducts(List<ReceiptDetails> products) {
		this.products = products;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public double getGrossPrice() {
		return grossPrice;
	}
	public void setGrossPrice(double grossPrice) {
		this.grossPrice = grossPrice;
	}
	
}