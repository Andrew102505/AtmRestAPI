package com.example.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot_backend.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{
	
	@Query("FROM Bank WHERE name = ?1")
	Bank getBankByName(String name);
}
