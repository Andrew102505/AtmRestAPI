package com.example.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_backend.model.Customer;

//Jpa class internally provides @Repository annotation, so we don't need to add it on our repos
public interface CustomerRepository extends JpaRepository<Customer, Integer>{//<entity type, primary key type>

}
