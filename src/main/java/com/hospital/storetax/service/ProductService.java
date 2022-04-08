package com.hospital.storetax.service;
import java.util.List;
import java.util.Optional;

import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.details.ReceiptDetails;

public interface ProductService {
	
	public ReceiptDetails addProduct(ProductDetails newProduct);
//	public ProductDetails updateProduct(ProductDetails updateProduct);
//	public void deleteProduct(int productId);

	List<ReceiptDetails> getAllProducts();
}
