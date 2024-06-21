package com.example.springboot_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_backend.model.Bank;
import com.example.springboot_backend.model.Customer;
import com.example.springboot_backend.service.BankService;

@RestController
public class BankController {
	
	private BankService bankService;
	
	public BankController(BankService bankService) {
		super();
		this.bankService = bankService;
	}
	//we need to make sure that when we get a customer it has their bank id, that's the last thing for this integration
	@PostMapping("/banks")
	public Bank saveBank(@RequestBody Bank bank) {
		return bankService.saveBank(bank);
	}
	@GetMapping("/banks")
	public List<Bank> getAllBanks(){
		return bankService.getAllBanks();
	}
	@GetMapping("/banks/{id}")
	public Bank getBankById(@PathVariable("id") int bankId) {
		return bankService.getBankById(bankId);
	}
	@PutMapping("/banks/{id}")
	public Bank updateBank(@RequestBody Bank bank, @PathVariable("id") int bankId) {
		return bankService.updateBank(bank, bankId);
	}
	@DeleteMapping("/banks/{id}")
	public Optional<Bank> deleteBank(@PathVariable("id") int bankId){
		return bankService.deleteBank(bankId);
	}
	@GetMapping("/banks/customers/{bankId}")//you might have to pass an id, i dont know if you can access the bank id if bank is passed as json format
	public List<Customer> getAllCustomers(@PathVariable("bankId") int id){
		
		return bankService.getAllCustomers(id);
	}
}
