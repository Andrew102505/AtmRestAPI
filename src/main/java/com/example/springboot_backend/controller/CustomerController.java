package com.example.springboot_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_backend.model.Account;
import com.example.springboot_backend.model.Customer;
import com.example.springboot_backend.service.CustomerService;

@RestController
//@RequestMapping("/api/customers")//base url for this controller to be called
public class CustomerController {
	//the methods like save are a part of jpa(hibernate is built in)
	
	/*we do this because like we said earlier we call service methods from controller and repo methods
	 *from service, so we need an object of service class to access service methods from controller
	 *(service object is a dependency to this class)*/
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	//create customer rest api
	
	@PostMapping("/customers/{bankId}")
	//post request from client contains a customer in json format and we want to bind it to java object using @Request mapping
	public Customer saveCustomer(@RequestBody Customer customer, @PathVariable("bankId") int bankId){
		//response entity class allows rest api to provide complete response details like status, header, etc.
		//System.out.println(customer.getName());
		return customerService.saveCustomer(customer, bankId);
	}
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
		
	}
	
	@GetMapping("/customers/{id}")//@PathVariable parameter name should match the url parameter name
	public Customer getCustomerById(@PathVariable("id")int customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	@PutMapping("/customers/{customerId}")//they will put the customer in json format that they want the existing customer to be like 
	public Customer updateCustomerName(@RequestBody Customer customer, @PathVariable("customerId") int customerId) {
		return customerService.updateCustomerName(customer, customerId);
	}
	@PutMapping("/customers/{customerId}/{bankId}")
	public Customer updateCustomerBank(@PathVariable("customerId") int customerId, @PathVariable("bankId") int bankId) {
		return customerService.updateCustomerBank(customerId, bankId);
	}
	@DeleteMapping("/customers/{id}")
	public Optional<Customer> deleteCustomer(@PathVariable("id") int customerId) {
		return customerService.deleteCustomer(customerId);
	}
	@GetMapping("/customers/accounts/{customerId}")
	public List<Account> getAllAccounts(@PathVariable("customerId") int id){
		return customerService.getAllAccounts(id);
	}
}
