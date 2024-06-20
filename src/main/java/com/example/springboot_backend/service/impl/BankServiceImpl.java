package com.example.springboot_backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot_backend.exception.ResourceNotFoundException;
import com.example.springboot_backend.model.Bank;
import com.example.springboot_backend.repository.BankRepository;
import com.example.springboot_backend.service.BankService;

@Service
public class BankServiceImpl implements BankService{

	private BankRepository bankRepository;
	
	public BankServiceImpl(BankRepository bankRepository) {
		super();
		this.bankRepository = bankRepository;
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
	
	
}
