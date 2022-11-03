package com.code.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer 
{
	@Id
	@Column(name="customerKey")
	private String customerKey;	//username+customerType, primary key for DB
	
	@Column(name="customerType")
	private int customerType; 	//1 is borrower, 0 is coborrower
	
	private String username;	//account username
	private String password;	//account password
	private double balance;		//account balance
	
	@Column(name="accountID")
	private int accountID;		//account ID number
	
	//private list of notifications
	
	public Customer(String customerKey, int customerType, String username, String password, double balance,
			int accountID) {
		super();
		this.customerKey = customerKey;
		this.customerType = customerType;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.accountID = accountID;
	}
	
	public String getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}
	public int getCustomerType() {
		return customerType;
	}
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
}
