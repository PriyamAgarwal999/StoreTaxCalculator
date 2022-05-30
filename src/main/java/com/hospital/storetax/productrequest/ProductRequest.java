package com.hospital.storetax.productrequest;

import com.hospital.storetax.details.ProductDetails;

public class ProductRequest {
	private String requestType;
	ProductDetails product;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public ProductDetails getProduct() {
		return product;
	}

	public void setProduct(ProductDetails product) {
		this.product = product;
	} 
	
	
}
