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

import com.example.springboot_backend.model.Purchase;
import com.example.springboot_backend.service.PurchaseService;

@RestController
public class PurchaseController {
	
	private PurchaseService purchaseService;
	
	public PurchaseController(PurchaseService purchaseService) {
		super();
		this.purchaseService = purchaseService;
	}
	
	@PostMapping("/purchases/{accountId}")
	public Purchase savePurchase(@RequestBody Purchase purchase, @PathVariable("accountId") int id) throws Exception {
		return purchaseService.savePurchase(purchase, id);
	}
	@GetMapping("/purchases")
	public List<Purchase> getAllPurchases(){
		return purchaseService.getAllPurchases();
	}
	@GetMapping("/purchases/{purchaseId}")
	public Purchase getPurchaseById(@PathVariable("purchaseId") int id) {
		return purchaseService.getPurchaseById(id);
	}
	@PutMapping("/purchases/description/{purchaseId}")
	public Purchase updatePurchaseDescription(@RequestBody Purchase purchase, @PathVariable("purchaseId") int id) {
		return purchaseService.updatePurchaseDescription(purchase, id);
	}
	@PutMapping("/purchases/price/{purchaseId}")
	public Purchase updatePurchasePrice(@RequestBody Purchase purchase, @PathVariable("purchaseId")int id) throws Exception {
		return purchaseService.updatePurchasePrice(purchase, id);
	}
	@DeleteMapping("/purchases/{id}")
	public Optional<Purchase> deletePurchase(@PathVariable("id") int purchaseId){
		return purchaseService.deletePurchase(purchaseId);
	}
}
