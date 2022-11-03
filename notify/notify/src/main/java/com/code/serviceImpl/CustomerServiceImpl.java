package com.code.serviceImpl;

import com.code.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.code.model.Customer;
import com.code.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired CustomerRepository customerRepository;
	
	@Override
	public Customer getByCustomerKey(String requestedKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getByUsername(String requestedUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Customer currentCustomer) {
		customerRepository.save(currentCustomer);
		return true;
	}

}
