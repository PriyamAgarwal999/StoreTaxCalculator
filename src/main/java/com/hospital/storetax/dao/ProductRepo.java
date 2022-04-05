package com.hospital.storetax.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.storetax.details.ProductDetails;

public interface ProductRepo extends JpaRepository<ProductDetails,Integer>{

}
