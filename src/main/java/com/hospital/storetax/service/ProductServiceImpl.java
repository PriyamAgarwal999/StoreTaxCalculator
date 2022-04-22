package com.hospital.storetax.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.storetax.dao.ProductRepo;
import com.hospital.storetax.details.Product;
import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.details.ProductSummary;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	//List of Medical Products
	List<String> medicalProducts= new ArrayList<>(Arrays.asList("Medicine","Syringe","Paracetamol","Sanitizer"));
	
	//List of Food Products
	List<String> foodProducts= new ArrayList<>(Arrays.asList("Chocolate","Biscuits","Ice Cream"));
	
	//Function to check if product is food item or not
    public boolean checkIsProductFood(String productName){ 
        for(String foodProduct:foodProducts) {
        	if(productName.contains(foodProduct)) {
        		return true;
        	}
        }
        return false;
    }

    //Function to check if product is medical item or not
    public boolean checkIsProductMedical(String productName){
        for(String medicalProduct:medicalProducts) {
        	if(productName.contains(medicalProduct)) {
        		return true;
        	}
        }
        return false;
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
    	ProductDetails product= productRepo.getByProductName(productName);
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
	
	  public boolean isProductPresent(ProductDetails newProduct){
	    	return productRepo.existsByProductName(newProduct.getProductName());
	    }
	  

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<ProductDetails> products = productRepo.findAll();
		List<Product> productList=new ArrayList<Product>();
		for(ProductDetails product:products) {
			Product addProduct=new Product();
			addProduct.setProductName(product.getProductName());
			addProduct.setProductQuantity(product.getProductQuantity());
			addProduct.setProductUnitPrice(product.getProductUnitPrice());
			addProduct.setTotalTax(getTotalTax(product));
			addProduct.setTotalPrice(getTotalPrice(product,getTotalTax(product)));
			productList.add(addProduct);
		}
		return productList;
	}
	

	@Override
	public ProductDetails addProduct(ProductDetails newProduct) {
//		// TODO Auto-generated method stub
		if(isProductPresent(newProduct.getProductName())==true) {	
		ProductDetails existingProduct=productRepo.getByProductName(newProduct.getProductName());
		int existingProductQuantity=existingProduct.getProductQuantity();
		int newProductQuantity=newProduct.getProductQuantity();
		int total=existingProductQuantity+newProductQuantity;
	    existingProduct.setProductQuantity(total); 
	    return productRepo.save(existingProduct);
		}
		else {
		return productRepo.save(newProduct);
		}
	}

	@Override
	public ProductSummary getProductSummary() {
		ProductSummary productFinalReceipt=new ProductSummary();
		List<ProductDetails> products = productRepo.findAll();
		List<Product> productList=new ArrayList<Product>();
		double totalPrice = 0;
		double totalTax=0;
		for(ProductDetails product : products){
			Product newProduct=new Product();
			newProduct.setProductName(product.getProductName());
			newProduct.setProductQuantity(product.getProductQuantity());
			newProduct.setProductUnitPrice(product.getProductUnitPrice());
			double tax=getTotalTax(product);
			double price=getTotalPrice(product,tax);
			newProduct.setTotalPrice(price);
			newProduct.setTotalTax(tax);
			productList.add(newProduct);
			totalPrice+=price;
			totalTax+=tax;
		}
		totalPrice=(totalPrice*100)/100.00;
		productFinalReceipt.setTotalPrice(totalPrice);
		productFinalReceipt.setTotalTax(totalTax);
		productFinalReceipt.setProducts(productList);
		productFinalReceipt.setGrossPrice(totalPrice-totalTax);
		return productFinalReceipt;
	}
	
	@Override
	public void deleteProduct(String deleteProductName) {
		// TODO Auto-generated method stub
		ProductDetails receipt=productRepo.getByProductName(deleteProductName);
		productRepo.delete(receipt);
	}

	@Override
	public ProductDetails updateProduct(ProductDetails updateProduct) {
		if(isProductPresent(updateProduct.getProductName())==true) {
			ProductDetails existingProduct=productRepo.getByProductName(updateProduct.getProductName());
			existingProduct.setProductQuantity(updateProduct.getProductQuantity());
			existingProduct.setProductUnitPrice(updateProduct.getProductUnitPrice());
			return productRepo.save(existingProduct);
		}
		else {
			return productRepo.save(updateProduct);
		}
	}	
}