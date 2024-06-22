package com.example.springboot_backend.service;

import java.util.List;
import java.util.Optional;

import com.example.springboot_backend.model.Account;
import com.example.springboot_backend.model.Purchase;

public interface PurchaseService {
	
	Purchase savePurchase(Purchase purchase, int accountId) throws Exception;//we don't want to have to pass the account info in json body(complex since nested customer object) so we just pass the account id in the url
	List<Purchase> getAllPurchases();
	Purchase getPurchaseById(int id);
	//better idea for updating objects is to individually update certain aspects of the entity because you might not want to change every aspect
	Purchase updatePurchaseDescription(Purchase purchase, int id);
	Purchase updatePurchasePrice(Purchase purchase, int id) throws Exception;//need to validate that there's enough money on the account first and luckily we have the account_id attribute in the purchase table
	Optional<Purchase> deletePurchase(int id); //remember to update the account balance, will need to do that first within the same method 
	
}
