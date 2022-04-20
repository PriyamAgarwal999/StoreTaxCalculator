package com.hospital.storetax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
public class StoreTaxCalculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreTaxCalculationApplication.class, args);
	}
	
}
