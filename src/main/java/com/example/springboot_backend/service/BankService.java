package com.example.springboot_backend.service;

import java.util.List;
import java.util.Optional;

import com.example.springboot_backend.model.Bank;

public interface BankService {

	//we would restrict this api functionality to only admins because we don't want customers deleting banks from the atm service.
	Bank saveBank(Bank bank);
	//customers should be able to use this list since they need to see all of the available banks
	List<Bank> getAllBanks();
	Bank getBankById(int id);
	Bank updateBank(Bank bank, int id);
	Optional<Bank> deleteBank(int id);
	
}
