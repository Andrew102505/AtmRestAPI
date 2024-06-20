package com.example.springboot_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data//this is a jpa entity now
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
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
	
}
