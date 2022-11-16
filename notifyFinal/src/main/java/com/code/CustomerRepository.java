package com.code;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ResponseBody;

public interface CustomerRepository extends CrudRepository<Customer, Integer>
{
	@Query(value = "SELECT username FROM customers", nativeQuery=true)
	public @ResponseBody ArrayList<String> getUsernames();
	
	@Query(value = "SELECT passcode FROM customers", nativeQuery=true)
	public @ResponseBody ArrayList<String> getPasscodes();
	
	@Query(value = "SELECT balance FROM customers WHERE username = :reqUser", nativeQuery=true)
	public @ResponseBody double getBalanceGivenUser(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT account_id FROM customers WHERE username = :reqUser", nativeQuery=true)
	public @ResponseBody int getAccountIdGivenUser(@Param("reqUser") String reqUser);
}
