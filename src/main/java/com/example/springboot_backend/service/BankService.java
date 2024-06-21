package com.example.springboot_backend.service;

import java.util.List;
import java.util.Optional;

import com.example.springboot_backend.model.Bank;
import com.example.springboot_backend.model.Customer;

public interface BankService {

	//we would restrict this api functionality to only admins because we don't want customers deleting banks from the atm service.
	Bank saveBank(Bank bank);
	//customers should be able to use this list since they need to see all of the available banks
	List<Bank> getAllBanks();
	Bank getBankById(int id);
	Bank updateBank(Bank bank, int id);
	Optional<Bank> deleteBank(int id);
	//this is part of the BankService because the list of customers is info pertaining to a bank
	List<Customer> getAllCustomers(int id);//will return a specific bank's list of customers
	//to return a banks list of customers I will need to pull data from the customer table thus I will need
	//to add a CustomerRepo object to my BankService implementation class, we will still put this method 
	//in the BankService class beacuse the list of customers is info pertaining to a bank
	
}
