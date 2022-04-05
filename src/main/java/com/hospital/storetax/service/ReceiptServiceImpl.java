package com.hospital.storetax.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.storetax.dao.ProductRepo;
import com.hospital.storetax.dao.ReceiptRepo;
import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.details.ReceiptDetails;

@Service
public class ReceiptServiceImpl implements ReceiptService {
	
	@Autowired	
	ProductRepo productRepo;
	
	@Autowired
	ReceiptRepo receiptRepo;
	
	//List of Medical Products
	List<String> medicalProducts= new ArrayList<>(Arrays.asList("Medicine","Syringe","Paracetamol","Sanitizer"));
	
	//List of Food Products
	List<String> foodProducts= new ArrayList<>(Arrays.asList("Chocolate","Biscuits","Ice Cream"));
	
	//Function to check if product is food item or not
    public boolean checkIsProductFood(String productName){ //To prevent multiple dots i.e receipt.foodProducts.contains(productName)
        return foodProducts.contains(productName);
    }

    //Function to check if product is medical item or not
    public boolean checkIsProductMedical(String productName){
        return medicalProducts.contains(productName);
    }
    
    //Function to check if product is book or not
    public boolean checkIsProductBook(String productName) {
    	return productName.contains("Book");
    }
    
    //Function to check if product is imported or not
    public boolean checkIsProductImported(String productName) {
    	return productName.contains("Imported");
    }
	
	
	
	//Function to get total tax on the product
	public double getTotalTax(int productId) {
		double totalTax=0;
		ProductDetails productDetails=productRepo.getById(productId);
		String productName=productDetails.getProductName();
		double productPrice=productDetails.getProductPrice();
		int productQuantity=productDetails.getProductQuantity();
		if(checkIsProductImported(productName)) {
			productName=productName.substring(9);
			if(checkIsProductMedical(productName) || checkIsProductFood(productName) || checkIsProductBook(productName)) {
				totalTax=productPrice*0.05*productQuantity;
			}
			else {
				
				totalTax=productPrice*0.15*productQuantity;
			}
		}
		else {
			if(!checkIsProductMedical(productName) && !checkIsProductFood(productName) && !checkIsProductBook(productName)) {
				totalTax=productPrice*0.10*productQuantity;
			}
		}
		totalTax=Math.round(totalTax *100)/100.00;
		
		
		return totalTax;
	}
	
	//Function to get totalPrice of the product
	public double getTotalPrice(int productId,double totalTax) {
		ProductDetails productDetails=productRepo.getById(productId);
		double productPrice=productDetails.getProductPrice();
		int productQuantity=productDetails.getProductQuantity();
		double totalPrice=(productPrice*productQuantity)+totalTax;
		totalPrice=Math.round(totalPrice*100)/100.00;
		return totalPrice;
	}
	
	
	@Override
	public ReceiptDetails getReceipt(int productId) {
		ProductDetails productDetails=productRepo.getById(productId);
		String productName=productDetails.getProductName();
		double totalTax=getTotalTax(productId);
		double totalPrice=getTotalPrice(productId,totalTax);
		ReceiptDetails receiptDetails = new ReceiptDetails();
		receiptDetails.setReceiptId(productId);
		receiptDetails.setProductName(productName);
		receiptDetails.setTotalTax(totalTax);
		receiptDetails.setTotalPrice(totalPrice);
		return receiptRepo.save(receiptDetails);
	}
}

		
