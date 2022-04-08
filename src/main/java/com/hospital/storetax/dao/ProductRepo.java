package com.hospital.storetax.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.storetax.details.ReceiptDetails;

public interface ProductRepo extends JpaRepository<ReceiptDetails,Integer>{
	public ReceiptDetails getByProductName(String productName);
	public boolean existsByProductName(String productName);
}