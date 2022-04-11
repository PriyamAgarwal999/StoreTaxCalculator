package com.hospital.storetax.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.storetax.dao.ProductRepo;
import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.details.ReceiptDetails;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
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
    
    public boolean isProductPresent(String productName){
    	ReceiptDetails product= productRepo.getByProductName(productName);
    	System.out.println(product);
    	return product!=null;
    }
	
	//Function to get total tax on the product
	public double getTotalTax(ProductDetails newProduct) {
		double totalTax=0;
		String productName=newProduct.getProductName();
		double productPrice=newProduct.getProductUnitPrice();
		int productQuantity=newProduct.getProductQuantity();
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
	public double getTotalPrice(ProductDetails newProduct,double totalTax) {
		double productPrice=newProduct.getProductUnitPrice();
		int productQuantity=newProduct.getProductQuantity();
		double totalPrice=(productPrice*productQuantity)+totalTax;
		totalPrice=Math.round(totalPrice*100)/100.00;
		return totalPrice;
	}
	
	  public boolean isProductPresent(ReceiptDetails newProduct){
	    	return productRepo.existsByProductName(newProduct.getProductName());
	    }
	  

	@Override
	public List<ReceiptDetails> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}
	
	public ReceiptDetails setReceiptDetails(ProductDetails newProduct) {
		ReceiptDetails receiptDetails=new ReceiptDetails();
		String productName=newProduct.getProductName();
		receiptDetails.setProductName(productName);
		receiptDetails.setProductQuantity(newProduct.getProductQuantity());
		receiptDetails.setProductUnitPrice(newProduct.getProductUnitPrice());
		double totalTax=getTotalTax(newProduct);
		System.out.println(totalTax);
		receiptDetails.setProductTotalTax(totalTax);
		double totalPrice=getTotalPrice(newProduct,totalTax);
		receiptDetails.setProductTotalPrice(totalPrice);
		return receiptDetails;
	}

	@Override
	public ReceiptDetails addProduct(ProductDetails newProduct) {
		ReceiptDetails receiptDetails=setReceiptDetails(newProduct);
		
//		// TODO Auto-generated method stub
		if(isProductPresent(receiptDetails)==true) {	
		ReceiptDetails existingProduct=productRepo.getByProductName(newProduct.getProductName());
		int existingProductQuantity=existingProduct.getProductQuantity();
		int newProductQuantity=newProduct.getProductQuantity();
		int total=existingProductQuantity+newProductQuantity;
//		System.out.println(total);
	    existingProduct.setProductQuantity(total); 
	    ProductDetails productDetails=new ProductDetails();
	    productDetails.setProductName(existingProduct.getProductName());
	    productDetails.setProductQuantity(total);
	    productDetails.setProductUnitPrice(existingProduct.getProductUnitPrice());
	    ReceiptDetails receipt=setReceiptDetails(productDetails);
//	    int existingProductTotalTax=getTotalTax(existingProduct);
//	    existingProduct.setProductTotalTax(existingProductTotalTax);
//	    int existingProductTotalPrice=getTotalPrice(existingProduct)
//	    existingProduct.setProductTotalPrice(total);
	    productRepo.save(receipt);
		}
		else {
		return productRepo.save(receiptDetails);
		}
		return null;
	}	
}