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
		 return productRepo.save(newProduct);
		 
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