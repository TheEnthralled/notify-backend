package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.Customer;
import com.code.service.CustomerService;

@RestController
@RequestMapping("/user")
public class Controller {
	
	@Autowired CustomerService customerService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public boolean checkCredentials(@RequestParam(value = "username", defaultValue ="root") String username, @RequestParam(value = "password", defaultValue = "calvin") String password)
	{
		//check if database has credentials
		
		return false;
	}
	
	@GetMapping("/info")
	public Customer getCustomer(@RequestParam(value = "customerKey", defaultValue = "root") String customerKey)
	{
		//check account balance for user
		return customerService.getByCustomerKey(customerKey);
	}
	
	@RequestMapping(value = "/unreadCount", method = RequestMethod.GET)
	public int getUnreadCount(@RequestParam(value = "userID", defaultValue = "-4") int userID)
	{
		//determine number of unread
		return userID;
	}
	
	@PostMapping("/save")
	public boolean saveCustomer(@RequestBody(required=true) Customer customer)
	{
		System.out.println("here!");
		return customerService.save(customer);
	}
	
}
