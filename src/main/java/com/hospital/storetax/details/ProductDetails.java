package com.hospital.storetax.details;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductDetails{

	@Id
	@Column(name="productName",length=20)
	private String productName;
	
	private int productQuantity;
	private double productUnitPrice;

	public ProductDetails() {
		this.productName=null;
		this.productQuantity=0;
		this.productUnitPrice=0;
	}
	
	public ProductDetails(String productName, int productQuantity, double productUnitPrice) {
		super();
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productUnitPrice = productUnitPrice;
	}
	
	public String getProductName(){
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
	
	
}