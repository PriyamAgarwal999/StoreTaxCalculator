package com.hospital.storetax.service;
import java.util.List;
import java.util.Optional;

import com.hospital.storetax.details.ProductDetails;

public interface ProductService {
	
	public List<ProductDetails> getAllProducts();
	public Optional<ProductDetails> getProductById(int productId);
	public ProductDetails addProduct(ProductDetails newProduct);
	public ProductDetails updateProduct(ProductDetails updateProduct);
	public void deleteProduct(int productId);
}
