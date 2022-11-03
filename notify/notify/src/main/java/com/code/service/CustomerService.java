package com.code.service;

import com.code.model.Customer;

public interface CustomerService {

	public Customer getByCustomerKey(String requestedKey);
	public Customer getByUsername(String requestedUsername);
	public boolean save(Customer currentCustomer);
}
