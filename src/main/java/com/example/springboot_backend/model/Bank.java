package com.example.springboot_backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	//The issue for why we can't have this list is that because this list contains objects(customers) and 
	//those objects contain objects(bank), so the data is continually embedded forever. Usually we can have
	//a list of objects as long as those objects in list contain don't contain object instance variables
	/*@OneToMany(mappedBy = "bank")
	private List<Customer> customers;*/
	
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public List<Customer> getCustomers() {
		return this.customers;
	}
	*/
}
