package com.hospital.storetax;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.hospital.storetax.details.ReceiptDetails;
import com.hospital.storetax.details.ProductDetails;
import com.hospital.storetax.dao.ProductRepo;
import com.hospital.storetax.service.ProductServiceImpl;

@SpringBootTest(classes= {ServiceMockitoTests.class})
public class ServiceMockitoTests {

	@Mock
	ProductRepo productRepo;
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	public List<ReceiptDetails> receipt;
	
	@Test
	@Order(1)
	public void testGetAllProducts() {
		List<ReceiptDetails> receipt=new ArrayList<ReceiptDetails>();
		receipt.add(new ReceiptDetails("Book",1,45,45,2));
		receipt.add(new ReceiptDetails("CD", 1, 60, 60, 2));
		when(productRepo.findAll()).thenReturn(receipt);
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
		ReceiptDetails receipt=new ReceiptDetails("Book",1,45,45,2);
		String productName="Book";
		when(productRepo.getByProductName(productName)).thenReturn(receipt);
		assertTrue(productServiceImpl.isProductPresent(productName));
	}
	
	@Test
	@Order(5)
	public void test_setReceiptDetails() {
		ProductDetails product= new ProductDetails("Imported Perfume",2,50);
		ReceiptDetails receipt=new ReceiptDetails("Imported Perfume",2,50,115,15);
		assertAll(
		() -> assertEquals(receipt.getProductName(),productServiceImpl.setReceiptDetails(product).getProductName()),
		() -> assertEquals(receipt.getProductQuantity(),productServiceImpl.setReceiptDetails(product).getProductQuantity()),
		() -> assertEquals(receipt.getProductTotalPrice(),productServiceImpl.setReceiptDetails(product).getProductTotalPrice()),
		() -> assertEquals(receipt.getProductTotalTax(),productServiceImpl.setReceiptDetails(product).getProductTotalTax()),
		() -> assertEquals(receipt.getProductUnitPrice(),productServiceImpl.setReceiptDetails(product).getProductUnitPrice())
		);
	}
	
}