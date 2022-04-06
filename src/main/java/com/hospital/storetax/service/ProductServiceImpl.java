package com.hospital.storetax.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.storetax.dao.ProductRepo;
import com.hospital.storetax.details.ProductDetails;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	  public boolean isProductPresent(ProductDetails newProduct){
	    	return productRepo.existsByProductName(newProduct.getProductName());
//	    	if(product==true) {
////	  		  System.out.println("hello");
////	    		ProductDetails existingProduct=productRepo.getByProductName(name);
////	    		int existingProductQuantity=existingProduct.getProductQuantity();
////	    		int newProductQuantity=newProduct.getProductQuantity();
////	    		int total=existingProductQuantity+newProductQuantity;
////	    	existingProduct.setProductQuantity( total);
//	    	return true;
//	    	}
//	    	System.out.println(product);
//	    	return false;
	    }
	  

	@Override
	public List<ProductDetails> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Optional<ProductDetails> getProductById(int productId) {
		// TODO Auto-generated method stub
		return productRepo.findById(productId);
	}

	@Override
	public ProductDetails addProduct(ProductDetails newProduct) {
		// TODO Auto-generated method stub
		if(isProductPresent(newProduct)==true) {	
		ProductDetails existingProduct=productRepo.getByProductName(newProduct.getProductName());
		int existingProductQuantity=existingProduct.getProductQuantity();
		int newProductQuantity=newProduct.getProductQuantity();
		int total=existingProductQuantity+newProductQuantity;
		System.out.println(total);
	    existingProduct.setProductQuantity(6);  
		}
		else {
		return productRepo.save(newProduct);
		}
		return null;
	}

	@Override
	public ProductDetails updateProduct(ProductDetails updateProduct) {
		// TODO Auto-generated method stub
		return productRepo.save(updateProduct);
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		ProductDetails productDeleted=productRepo.getById(productId);
		 productRepo.delete(productDeleted);
	}
}