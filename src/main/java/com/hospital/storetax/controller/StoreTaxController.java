package com.hospital.storetax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.storetax.constants.Constants;
import com.hospital.storetax.details.Product;
import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.details.ProductSummary;
import com.hospital.storetax.service.ProductService;

@RestController
public class StoreTaxController {
	
	@Autowired
	private ProductService productService;
	
	
	//To get all the product Details
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
//	
	//To add new product
	@PostMapping("/addproduct")
	public String addProduct(@RequestBody ProductDetails newProduct){
		Constants constant=new Constants();
		productService.addProduct(newProduct);
		
		return constant.getProductAddedSuccessResponse();
	}
	
	
	@PutMapping("/updateproduct/")
	public String updateProduct(@RequestBody ProductDetails updateProduct) {
		Constants constant=new Constants();
		productService.updateProduct(updateProduct);
		return constant.getProductUpdatedSucessResponse();
	}
	
	@GetMapping("/productsummary/")
	public ProductSummary getProductSummary(){
		return productService.getProductSummary();
	}
	
	@DeleteMapping("/deleteproduct/{deleteProductName}")
	public String deleteProduct(@PathVariable("deleteProductName") String deleteProductName) {
		Constants constant=new Constants();
		productService.deleteProduct(deleteProductName);
		return constant.getProductDeletedSuccessResponse();
	}
}