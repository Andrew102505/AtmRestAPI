package com.example.springboot_backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot_backend.exception.ResourceNotFoundException;
import com.example.springboot_backend.model.Account;
import com.example.springboot_backend.model.Purchase;
import com.example.springboot_backend.repository.AccountRepository;
import com.example.springboot_backend.repository.PurchaseRepository;
import com.example.springboot_backend.service.AccountService;
import com.example.springboot_backend.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	private PurchaseRepository purchaseRepository;
	private AccountRepository accountRepository;
	private AccountService accountService;
	
	public PurchaseServiceImpl(PurchaseRepository purchaseRepository, AccountRepository accountRepository, AccountService accountService) {
		super();
		this.purchaseRepository = purchaseRepository;
		this.accountRepository = accountRepository;
		this.accountService = accountService;
	}
	
	@Override
	public Purchase savePurchase(Purchase purchase, int accountId) throws Exception {
		Optional<Account> account = accountRepository.findById(accountId);
		if(account.isPresent()) {
			purchase.setAccount(account.get());
		}else {
			throw new ResourceNotFoundException("Account", "Id", accountId);
		}
		//check that the purchase can be made
		if(account.get().getBalance()>=purchase.getPrice()) {
			purchaseRepository.save(purchase);
			accountService.updateAccountBalance(accountId, account.get().getBalance()-purchase.getPrice());
			
		}else {
			throw new Exception("Purchase exceeds account balance!");
		}
		return purchase;
		
	}

	@Override
	public List<Purchase> getAllPurchases() {
		return purchaseRepository.findAll();
	}

	@Override
	public Purchase getPurchaseById(int id) {
		Optional<Purchase> purchase = purchaseRepository.findById(id);
		if(purchase.isPresent()) {
			return purchase.get();
		}else {
			throw new ResourceNotFoundException("Purchase", "Id", id);
		}
	}

	@Override
	public Purchase updatePurchaseDescription(Purchase purchase, int id) {
		Purchase existingPurchase = purchaseRepository.findById(id).orElseThrow((
				) -> new ResourceNotFoundException("Purchase", "Id", id));
		//this is referring to the new description in the json body since purchase is referring to the new purchase in the json body
		if(purchase.getDescription()!=null&&!purchase.getDescription().equals("")) {
			existingPurchase.setDescription(purchase.getDescription());
		}
		purchaseRepository.save(existingPurchase);
		return existingPurchase;
	}

	@Override//the purchase passed does not get stored to db since we never save it using purchaseRepository
	public Purchase updatePurchasePrice(Purchase purchase, int id) throws Exception {
		//purchase will contain a purchase price which is the new purchase price
		Purchase existingPurchase = purchaseRepository.findById(id).orElseThrow((
				) -> new ResourceNotFoundException("Purchase", "Id", id));
		//this is referring to the new price in the json body since purchase is referring to the new purchase in the json body
		//we first need to make sure that there is enough money in the account that the purchase is on to update the price of the purchase
		
		if(existingPurchase.getAccount().getBalance()>=(purchase.getPrice()-existingPurchase.getPrice())) {
			//System.out.println("-----------------------" + purchase.getPrice());
			int difference = purchase.getPrice()-existingPurchase.getPrice();
			existingPurchase.setPrice(purchase.getPrice());
			
			//System.out.println("------------------ " + difference);
			//I don't know that this will update the db, I might need to use the accountRepository object and create a customer jpql query
			//existingPurchase.getAccount().setBalance(existingPurchase.getAccount().getBalance()-difference);
			//accountRepository.save(existingPurchase.getAccount());
			//accountService.updateAccountBalance();
			accountService.updateAccountBalance(existingPurchase.getAccount().getId(), existingPurchase.getAccount().getBalance()-difference);
		}else {
			throw new Exception("Invalid price update for account balance.");
		}
		purchaseRepository.save(existingPurchase);
		return existingPurchase;
	}

	@Override
	public Optional<Purchase> deletePurchase(int id) {
		Optional<Purchase> deletedPurchase = purchaseRepository.findById(id);
		if(deletedPurchase.isPresent()) {
			purchaseRepository.deleteById(id);
			//we are adding back the purchase price to the account balance, might also need to use accountRepo object to save changes to db
			//deletedPurchase.get().getAccount().setBalance(deletedPurchase.get().getAccount().getBalance() + deletedPurchase.get().getPrice());
			//accountRepository.save(deletedPurchase.get().getAccount());
			accountService.updateAccountBalance(deletedPurchase.get().getAccount().getId(), deletedPurchase.get().getAccount().getBalance()+deletedPurchase.get().getPrice());
			return deletedPurchase;
		}else {
			throw new ResourceNotFoundException("Purchase", "Id", id);
		}
		
	}
	
}
