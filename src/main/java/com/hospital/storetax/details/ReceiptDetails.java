package com.hospital.storetax.details;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReceiptDetails{

	@Id
	@Column(name="productName",length=20)
	private String productName;
	
	private int productQuantity;
	private double productUnitPrice;
	private double productTotalPrice;
	private double productTotalTax;
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
	public double getProductTotalPrice() {
		return productTotalPrice;
	}
	public void setProductTotalPrice(double productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}
	public double getProductTotalTax() {
		return productTotalTax;
	}
	public void setProductTotalTax(double productTotalTax) {
		this.productTotalTax = productTotalTax;
	}
}