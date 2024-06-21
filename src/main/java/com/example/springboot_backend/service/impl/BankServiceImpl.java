package com.example.springboot_backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot_backend.exception.ResourceNotFoundException;
import com.example.springboot_backend.model.Bank;
import com.example.springboot_backend.model.Customer;
import com.example.springboot_backend.repository.BankRepository;
import com.example.springboot_backend.repository.CustomerRepository;
import com.example.springboot_backend.service.BankService;

@Service
public class BankServiceImpl implements BankService{

	private BankRepository bankRepository;
	private CustomerRepository customerRepository;
	
	public BankServiceImpl(BankRepository bankRepository, CustomerRepository customerRepository) {
		super();
		this.bankRepository = bankRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public Bank saveBank(Bank bank) {
		return bankRepository.save(bank);
	}

	@Override
	public List<Bank> getAllBanks() {
		return bankRepository.findAll();
	}

	@Override
	public Bank getBankById(int id) {
		Optional<Bank> bank = bankRepository.findById(id);
		
		if(bank.isPresent()) {
			return bank.get();
		}else {
			throw new ResourceNotFoundException("Bank", "Id", id);
		}
	}

	@Override
	public Bank updateBank(Bank bank, int id) {
		Bank existingBank = bankRepository.findById(id).orElseThrow((
				) -> new ResourceNotFoundException("Bank", "Id", id));
		existingBank.setName(bank.getName());
		bankRepository.save(existingBank);
		return existingBank;
	}
	
	@Override
	public Optional<Bank> deleteBank(int id) {
		Optional<Bank> deletedBank = bankRepository.findById(id);
		if(deletedBank.isPresent()) {
			bankRepository.deleteById(id);
			return deletedBank;
		}else {
			throw new ResourceNotFoundException("Bank", "Id", id);
		}
	}

	@Override
	public List<Customer> getAllCustomers(int bankId) {
		//so you need to look into the customer table and return all banks 
		
		//here we are using a customer method(getAllCustomersWithId) that we defined in the CustomerRepo
		return customerRepository.getAllCustomersWithId(bankId);//method returns a Customer list
		//customer repository object deals with objects in the customer table
		/*List<Customer> allCustomers = customerRepository.findAll();
		List<Customer> requestedCustomers = new ArrayList<>();
		for(int i = 0; i<allCustomers.size();i++) {
			if(allCustomers.get(i).getBank().getId()==bankId) {
				requestedCustomers.add(allCustomers.get(i));
			}
		}
		for(Customer c:requestedCustomers) {
			System.out.println(c.getName());
		}
		
		return requestedCustomers;
		*/
	}
	
}
