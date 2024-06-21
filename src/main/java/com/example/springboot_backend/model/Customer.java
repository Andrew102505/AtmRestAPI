package com.example.springboot_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;


@Data//this is a jpa entity now
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	/*@Transient//we don't want this value stored to the db, we just have it so that the client can enter the bank they want to join in json format, we want to store the bank id in the customer table and jpa/hibernate automatically does that for us when we have an object dependency in this case bank
	private String bankName;*///we don't want this because even though its transient in the json format all instance variables will be returned and it will always have a null value, and we'd rather not have uneeded/useless(null value) returned
	@ManyToOne
	private Bank bank;
	/*
	private Bank bank;
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts;*/
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public Bank getBank() {
		return this.bank;
	}
	/*public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	*/
	//I want a way for the user to be able to be able ot enter the name of the bank he wants to join in json format 
}
