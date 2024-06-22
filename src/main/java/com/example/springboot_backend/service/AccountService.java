package com.example.springboot_backend.service;

import java.util.List;
import java.util.Optional;

import com.example.springboot_backend.model.Account;
import com.example.springboot_backend.model.Purchase;

public interface AccountService {
	
	Account saveAccount(Account account, int id);
	List<Account> getAllAccounts();
	Account getAccountById(int id);
	Account updateAccountName(int accountId, String newName);
	Account updateAccountBalance(int accountId, int newBalance);
	Optional<Account> deleteAccount(int id);
	List<Purchase> getAllPurchases(int id);
}
