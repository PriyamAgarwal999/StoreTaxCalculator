package com.hospital.storetax.details;

public class Product {
	
private String productName;
	
	private int productQuantity;
	private double productUnitPrice;
	private double totalTax;
	private double totalPrice;
	public Product() {
		
	}
	public Product(String productName, int productQuantity, double productUnitPrice, double totalTax,
			double totalPrice) {
		super();
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productUnitPrice = productUnitPrice;
		this.totalTax = totalTax;
		this.totalPrice = totalPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
