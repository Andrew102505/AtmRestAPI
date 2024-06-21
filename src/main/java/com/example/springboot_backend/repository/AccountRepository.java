package com.example.springboot_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot_backend.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	@Query(value = "SELECT * FROM account WHERE customer_id = :id", nativeQuery = true)
	List<Account> getAllAccountsWithId(int id);
	
}
