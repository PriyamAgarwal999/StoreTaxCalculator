package com.hospital.storetax.activemq;


import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.hospital.storetax.productrequest.ProductRequest;
import com.hospital.storetax.service.ProductService;



@Component
public class Listener {
	
	@Autowired
	private ProductService productService;
	
	@JmsListener(destination = "inbound")
	public void receiveMessage(final String message) throws JMSException {
		Gson gson=new Gson();
		ProductRequest product=gson.fromJson(message,ProductRequest.class);
		System.out.println(product.getRequestType());
		if(product.getRequestType().equals("Add")) {
			productService.addProduct(product.getProduct());
		}
		if(product.getRequestType().equals("Delete")) {
			productService.deleteProduct(product.getProduct().getProductName());
		}
		if(product.getRequestType().equals("Update")) {
			productService.updateProduct(product.getProduct());
		}
	}
	
}
