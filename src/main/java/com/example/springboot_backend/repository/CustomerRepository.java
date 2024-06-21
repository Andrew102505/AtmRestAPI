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
