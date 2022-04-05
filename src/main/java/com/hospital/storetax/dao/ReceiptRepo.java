package com.hospital.storetax.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.storetax.details.ReceiptDetails;
public interface ReceiptRepo extends JpaRepository<ReceiptDetails,Integer> {

}
