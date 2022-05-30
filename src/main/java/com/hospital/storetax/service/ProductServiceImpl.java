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
    	if (productName == null || productName.trim().isEmpty()) {
    		throw new IllegalArgumentException("the product name can not be null or empty");
    		}
    	ProductDetails product= productRepo.getByProductName(productName);
    	return product!=null;
    }
	
	//Function to get total tax on the product, store tax is applied as standard 10% for all items except Book food and medical products
    //additional 5% imported tax is applied to all imported products
	public double getTotalTax(ProductDetails newProduct) {
		double totalTax=0;
		String productName=newProduct.getProductName();
		double productPrice=newProduct.getProductUnitPrice();
		int productQuantity=newProduct.getProductQuantity();
		//cheking if product is imported or not
		if(checkIsProductImported(productName)) {
			productName=productName.substring(9);
			//checking if imported product is food product or medical product or book
			if(checkIsProductMedical(productName) || checkIsProductFood(productName) || checkIsProductBook(productName)) {
				totalTax=productPrice*0.05*productQuantity;
			}
			else {
				totalTax=productPrice*0.15*productQuantity;
			}
		}
		else {
			//checking if imported product is not food product or medical product or book.
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
		if(products.isEmpty()) {
			throw new NullPointerException("Repository is Empty");
		}
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
		//checking if the product is already present or not
		//if product is already present then adding the new product quantity to the existing product quantity.
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

	// function to get the products summary of all the products.
	@Override
	public ProductSummary getProductSummary() {
		ProductSummary productFinalReceipt=new ProductSummary();
		List<ProductDetails> products = productRepo.findAll();
		if(products.isEmpty()) {
			throw new NullPointerException("Repository is Empty");
		}
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
	
	//function to delete the product from the records.
	@Override
	public void deleteProduct(String deleteProductName) {
		// TODO Auto-generated method stub
		ProductDetails receipt=productRepo.getByProductName(deleteProductName);
		productRepo.delete(receipt);
	}

	//function to update the product details of the existing product.
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