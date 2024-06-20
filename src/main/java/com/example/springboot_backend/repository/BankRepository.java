package com.example.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_backend.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

}
