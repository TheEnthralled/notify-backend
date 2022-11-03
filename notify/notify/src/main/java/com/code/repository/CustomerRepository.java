package com.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	Customer findByUsername(String username);
}
