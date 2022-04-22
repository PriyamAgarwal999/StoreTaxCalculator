package com.hospital.storetax;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.dao.ProductRepo;
import com.hospital.storetax.service.ProductServiceImpl;

@SpringBootTest(classes= {ServiceMockitoTests.class})
public class ServiceMockitoTests {

	@Mock
	ProductRepo productRepo;
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	
	@Test
	@Order(1)
	public void testGetAllProducts() {
		List<ProductDetails> productList=new ArrayList<ProductDetails>();
		productList.add(new ProductDetails("Book",1,45));
		productList.add(new ProductDetails("CD", 1, 60));
		when(productRepo.findAll()).thenReturn(productList);
		assertEquals(2,productServiceImpl.getAllProducts().size());
	}
	
	
	@Test
	@Order(2)
	public void test_getTotalTax() {
		ProductDetails product1=new ProductDetails("Imported Chocolate",1,10);
		ProductDetails product2=new ProductDetails("Imported Book",2,50);
		ProductDetails product3=new ProductDetails("Syringe",1,20);
		ProductDetails product4=new ProductDetails("Music CD",3,40);
		assertAll(
		() -> assertEquals(0.50,productServiceImpl.getTotalTax(product1)),
		() -> assertEquals(5,productServiceImpl.getTotalTax(product2)),
		() -> assertEquals(0,productServiceImpl.getTotalTax(product3)),
		() -> assertEquals(12,productServiceImpl.getTotalTax(product4))
		);
	}
	
	@Test
	@Order(3)
	public void test_getTotalPrice() {
		ProductDetails product=new ProductDetails("Perfume",2,50);
		assertEquals(110,productServiceImpl.getTotalPrice(product,productServiceImpl.getTotalTax(product)));
	}
	
	@Test
	@Order(4)
	public void test_isProductPresent() {
		ProductDetails product=new ProductDetails("Book",1,45);
		String productName="Book";
		when(productRepo.getByProductName(productName)).thenReturn(product);
		assertTrue(productServiceImpl.isProductPresent(productName));
	}
	
//	@Test
//	@Order(5)
//	public void setProductDetails() {
//		ProductDetails productDetails= new ProductDetails("Imported Perfume",2,50);
//		Product product=new Product("Imported Perfume",2,50,15,115);
//		assertAll(
//		() -> assertEquals(product.getProductName(),productServiceImpl.setProductDetails(productDetails).getProductName()),
//		() -> assertEquals(product.getProductQuantity(),productServiceImpl.setProductDetails(productDetails).getProductQuantity()),
//		() -> assertEquals(product.getProductUnitPrice(),productServiceImpl.setProductDetails(productDetails).getProductUnitPrice())
//		);
//	}
//	

}