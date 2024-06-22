package com.example.springboot_backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot_backend.exception.ResourceNotFoundException;
import com.example.springboot_backend.model.Account;
import com.example.springboot_backend.model.Customer;
import com.example.springboot_backend.model.Purchase;
import com.example.springboot_backend.repository.AccountRepository;
import com.example.springboot_backend.repository.CustomerRepository;
import com.example.springboot_backend.repository.PurchaseRepository;
import com.example.springboot_backend.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;
	private PurchaseRepository purchaseRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, PurchaseRepository purchaseRepository) {
		super();
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public Account saveAccount(Account account, int id) {//id of the customer
		//needed to set the customer attribute of the account object
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent()) {
			account.setCustomer(customer.get());
		}else {
			throw new ResourceNotFoundException("Customer", "Id", id);
		}
		
		return accountRepository.save(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account getAccountById(int id) {
		Optional<Account> account = accountRepository.findById(id);
		if(account.isPresent()) {
			return account.get();
		}else {
			throw new ResourceNotFoundException("Account", "Id", id);
		}
	}
//update methods for the account --> we are not going to allow the user to restrict account access, if customer changed its own values it wouldn't matter because they didn't change the customer id, and that is how the customer is linked to the account
	@Override
	public Account updateAccountName(int accountId, String newName) {
		Account existingAccount = accountRepository.findById(accountId).orElseThrow((
				) -> new ResourceNotFoundException("Account", "Id", accountId));
		//ensuring that an actual value was passed, otherwise the values will remain the same as before(not throwing an error exception)
		if(newName!=null&&!newName.equals("")) {//because we want the account to have a name
			existingAccount.setName(newName);
		}
		accountRepository.save(existingAccount);
		return existingAccount;
		
	}
	@Override
	public Account updateAccountBalance(int accountId, int newBalance) {
		Account existingAccount = accountRepository.findById(accountId).orElseThrow((
				) -> new ResourceNotFoundException("Account", "Id", accountId));
		
		existingAccount.setBalance(newBalance);
		
		accountRepository.save(existingAccount);
		return existingAccount;
		
	}
	
	
	@Override
	public Optional<Account> deleteAccount(int id) {
		Optional<Account> deletedAccount = accountRepository.findById(id);
		if(deletedAccount.isPresent()) {
			accountRepository.deleteById(id);
			return deletedAccount;
		}else {
			throw new ResourceNotFoundException("Account", "Id", id);
		}
		
	}

	@Override
	public List<Purchase> getAllPurchases(int id) {
		return purchaseRepository.getAllPurchasesWithId(id);
	}
	
	
}
