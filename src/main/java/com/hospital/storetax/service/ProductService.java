package com.hospital.storetax.service;
import java.util.List;
import com.hospital.storetax.details.Product;
import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.details.ProductSummary;

public interface ProductService {
	
	public ProductDetails addProduct(ProductDetails newProduct);
//	public ProductDetails updateProduct(ProductDetails updateProduct);
//	public void deleteProduct(int productId);

	List<Product> getAllProducts();
	public ProductSummary getProductSummary(); 
	public void deleteProduct(String deleteProductName);
	public ProductDetails updateProduct(ProductDetails updateProduct);
}
