package com.hospital.storetax.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.details.ReceiptDetails;
import com.hospital.storetax.service.ProductService;
import com.hospital.storetax.service.ReceiptService;

@RestController
public class StoreTaxController {
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private ProductService productService;
	
	//TO get the receipt of the product
	@GetMapping("/store/id/{id}")
	public ReceiptDetails getReceipt(@PathVariable("id") int productId){
		return receiptService.getReceipt(productId);
	}
	
	//To get all the product Details
	@GetMapping("/products")
	public List<ProductDetails> getAllProducts(){
		return productService.getAllProducts();
	}
	
	//To get details of the particular Product
	@GetMapping("/product/id/{id}")
	public Optional<ProductDetails> getProductById(@PathVariable("id") int productId){
		return productService.getProductById(productId);
	}
	
	//To add new product
	@PostMapping("/addproduct")
	public String addProduct(@RequestBody ProductDetails newProduct){
		productService.addProduct(newProduct);
		return "Product Added!!";
	}
	
	//to update details of the product
	@PutMapping("/updateproduct")
	public String updateProduct(@RequestBody ProductDetails updateProduct) {
		productService.updateProduct(updateProduct);
		return "Product Updated!!";
	}
	
	//To remove the product
	@DeleteMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") int productId) {
		productService.deleteProduct(productId);
		return "Product Removed!!";
	}
}