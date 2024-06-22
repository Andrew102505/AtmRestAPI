package com.example.springboot_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot_backend.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
	
	@Query(value = "SELECT * FROM purchase WHERE account_id = :id", nativeQuery = true)
	List<Purchase> getAllPurchasesWithId(int id);
	

}
