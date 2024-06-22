package com.example.springboot_backend.service;

import java.util.List;
import java.util.Optional;

import com.example.springboot_backend.model.Account;
import com.example.springboot_backend.model.Customer;

public interface CustomerService {
	
	//used to add customer to db
	Customer saveCustomer(Customer customer, int bankId);
	List<Customer> getAllCustomers();
	Customer getCustomerById(int id);
	/*the first argument(customer) from the client will be in json format and will contain the new values 
	 * they want to set an existing customer object to, however, we dont want them to pass the id object
	 * within the json format(complicated), so they will specify the id of the existing customer in the url
	 */
	
	Customer updateCustomerName(Customer customer, int customerId);
	Customer updateCustomerBank(int customerId, int bankId);
	//will return the deleted customer
	Optional<Customer> deleteCustomer(int id);
	List<Account> getAllAccounts(int id);//customerId
}
