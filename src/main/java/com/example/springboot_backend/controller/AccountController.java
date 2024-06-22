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

import com.example.springboot_backend.model.Account;
import com.example.springboot_backend.model.Purchase;
import com.example.springboot_backend.service.AccountService;

@RestController
public class AccountController {

	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PostMapping("/accounts/{customerId}")//the the id of the client will be passed to the url(post request so http parameters won't be visible)
	public Account saveAccount(@RequestBody Account account, @PathVariable("customerId") int id) {
		return accountService.saveAccount(account, id);
	}
	@GetMapping("/accounts")
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}
	@GetMapping("/accounts/{accountId}")//when the client requests to get a particlar account, the id of the account will be sent in the url
	public Account getAccountById(@PathVariable("accountId") int accountId) {
		return accountService.getAccountById(accountId);
	}
	@PutMapping("/accounts/name/{accountId}/{newName}")//enter account that we want existing account to be like in json format
	public Account updateAccountName( @PathVariable("accountId") int accountId , @PathVariable("newName") String newName) {
		return accountService.updateAccountName(accountId, newName);
	}
	@PutMapping("/accounts/balance/{accountId}/{newBalance}")//enter account that we want existing account to be like in json format
	public Account updateAccountBalance( @PathVariable("accountId") int accountId , @PathVariable("newBalance") int newBalance) {
		return accountService.updateAccountBalance(accountId, newBalance);
	}
	@DeleteMapping("/accounts/{accountId}")//id of account in db that we want to delete
	public Optional<Account> deleteAccount(@PathVariable("accountId") int accountId){
		return accountService.deleteAccount(accountId);
	}
	@GetMapping("/accounts/purchases/{accountId}")
	public List<Purchase> getAllPurchases(@PathVariable("accountId") int id){
		return accountService.getAllPurchases(id);
	}
	
}
