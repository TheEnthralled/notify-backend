package com.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.code.Customer;

@Controller
@RequestMapping("/user")
public class MainController 
{
	@Autowired
	
	private CustomerRepository customerRepository;
	
	@PostMapping("/save")
	public @ResponseBody String saveCustomer(@RequestParam(defaultValue = "novalueprovided") String cKey, @RequestParam int cType, @RequestParam String username, @RequestParam String password, @RequestParam double balance, @RequestParam int accountID)
	{
		Customer temp = new Customer();
		temp.setCustomerKey(cKey);
		temp.setCustomerType(cType);
		temp.setUsername(username);
		temp.setPassword(password);
		temp.setBalance(balance);
		temp.setAccountID(accountID);
		customerRepository.save(temp);
		return "Saved";
	}
	
}
