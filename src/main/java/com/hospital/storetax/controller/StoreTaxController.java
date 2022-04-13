package com.hospital.storetax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.storetax.constants.Constants;
import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.details.ProductSummary;
import com.hospital.storetax.details.ReceiptDetails;
import com.hospital.storetax.service.ProductService;
import com.hospital.storetax.service.ReceiptService;

@RestController
public class StoreTaxController {
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private ProductService productService;
	
	//To get all the product Details
	@GetMapping("/products")
	public List<ReceiptDetails> getAllProducts(){
		return productService.getAllProducts();
	}
//	
	//To add new product
	@PostMapping("/addproduct")
	public String addProduct(@RequestBody ProductDetails newProduct){
		Constants constant=new Constants();
		productService.addProduct(newProduct);
		
		return constant.getProductSuccessResponse();
	}
	
	//to update details of the product
//	@PutMapping("/updateproduct")
//	public String updateProduct(@RequestBody ProductDetails updateProduct) {
//		productService.updateProduct(updateProduct);
//		return "Product Updated!!";
//	}

	
	@GetMapping("/productsummary/")
	public ProductSummary getProductSummary(){
		return receiptService.getProductSummary();
	}
	
}