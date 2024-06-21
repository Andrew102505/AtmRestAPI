package com.example.springboot_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot_backend.model.Customer;

//Jpa class internally provides @Repository annotation, so we don't need to add it on our repos
public interface CustomerRepository extends JpaRepository<Customer, Integer>{//<entity type, primary key type>

	
//here we can define our own custom jpa methods and use them on the customerRepo object in our service class
//there are already built in methods like findBy(), findAll()
	
	//we use a jpql query with @Query to define what the method will do(can specify return type and arguments)
	@Query(value = "SELECT * FROM customer WHERE bank_id = :id", nativeQuery = true)//might not be able to access the column name
	List<Customer> getAllCustomersWithId(int id);//id will be passed into the ?1, 1 stands for param #
}
//important to note that we can't do List of objects if the objects in the list also contain object instance
//variable of type that the class the list is in
//for example Bank class has list of customer objects and customer has a Bank object can't do this
//we could have Bank class has list of customer objects and customer has hard drive instance variable 
//object 
//for now when we add the account class, we will it will have a customer object and that customer has a list
//of account objects --> customer can't have an accounts list otherwise we'll get infinite nested problem
//for purchase class the purchase will be linked to an account and the accounts will have a list of purchases
//under it --> account can't have a list of purchases otherwise we'll get infinite nesting problem