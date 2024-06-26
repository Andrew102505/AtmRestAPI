package com.example.springboot_backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot_backend.exception.ResourceNotFoundException;
import com.example.springboot_backend.model.Account;
import com.example.springboot_backend.model.Customer;
import com.example.springboot_backend.repository.AccountRepository;
import com.example.springboot_backend.repository.BankRepository;
import com.example.springboot_backend.repository.CustomerRepository;
import com.example.springboot_backend.service.CustomerService;

@Service//add annotation whenever creating a service class, also makes it a bean
public class CustomerServiceImpl implements CustomerService{
	
	//we need to inject the CustomerRepository Object because it allows us to use jpa methods and interact with db
	//like we said we call service methods in controller and implement repo functionality in service class
	private CustomerRepository customerRepository;
	private BankRepository bankRepository;
	private AccountRepository accountRepository;
	//use constructor based dependency injection whenever you have mandatory parameters
	//use setter based dependency injection whenever you have optional parameters(because then you can choose to call the set method or not)
	
	//constructor
	//don't have to add @Autowired above constructor bacause if a class is a spring bean and it has only one constructor then spring will automatically configure instance variable dependencies
	public CustomerServiceImpl(CustomerRepository customerRepository, BankRepository bankRepository, AccountRepository accountRepository) {
		super();
		this.customerRepository = customerRepository;
		this.bankRepository = bankRepository;
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Customer saveCustomer(Customer customer, int bankId) {
		//we still have not set the bank that the customer belongs to
		customer.setBank(bankRepository.findById(bankId).get());
		//now our customer has a bank object initialized and hibernate/jpa will store the banks id to the customer table since we retrived a bank from the table
		//this save method is a part of jpa, it also knows to save an object of type customer to customers table since in the customers class we specified the table name as "customer" for customer entity, if we didn't specify table name in class it would map the object to table named "customer" since we still marked the class with @Entity 
		return customerRepository.save(customer);
	}
//this is a class that implements the CustomerService interface(impl = implementation)

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();//returns all entries in customers table
	}

@Override
public Customer getCustomerById(int id) {
	//type optional since we might not get an object back and we don't want to return null
	Optional<Customer> customer = customerRepository.findById(id);
	//is present returns true if there is a value present in the optional instance
	if(customer.isPresent()) {
		return customer.get();//.get() returns the value of the optional(customer is an optional in this case) otherwise it throws a no such element exception instead of null
	//object will be returned in json format, when using react we will need something to parse this json 
	}else {
		//this is the exception class we created that will be returned if the user requests for an resource(object in this case) that is not available
		throw new ResourceNotFoundException("Customer", "Id", id);
	}
}

@Override
public Customer updateCustomerName(Customer customer, int customerId) {
	Customer existingCustomer = customerRepository.findById(customerId).orElseThrow((
			) -> new ResourceNotFoundException("Customer", "Id", customerId));
	existingCustomer.setName(customer.getName());
	customerRepository.save(existingCustomer);
	return existingCustomer;
}
@Override
public Customer updateCustomerBank(int customerId, int bankId) {
	Customer existingCustomer = customerRepository.findById(customerId).orElseThrow((
			) -> new ResourceNotFoundException("Customer", "Id", customerId));
	existingCustomer.setBank(bankRepository.findById(bankId).get());
	customerRepository.save(existingCustomer);
	return existingCustomer;
}
@Override
public Optional<Customer> deleteCustomer(int id) {
	//check that customer with id passed exists
	//we could also make the return type void and just run the right side of the first line and deleteById line without returning the deleted customer
	Optional<Customer> deletedCustomer = customerRepository.findById(id);//.orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
	if(deletedCustomer.isPresent()) {
		customerRepository.deleteById(id);//the reference is not deleted from deletedCustomer while the program is still running, if you try to access that object again form db using findById, while or after the program the runs you won't able to since the entry has been deleted from the db
		return deletedCustomer;
	}else {
		throw new ResourceNotFoundException("Customer", "Id", id);
	}
	
}

@Override
public List<Account> getAllAccounts(int customerId) {
	return accountRepository.getAllAccountsWithId(customerId);
}
	

	
	
}
