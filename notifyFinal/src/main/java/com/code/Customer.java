package com.code;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer 
{
	@Id
	private String customer_key;	//username+customerType, primary key for DB
	
	private int user_type; 	//1 is borrower, 0 is coborrower
	
	private String username;	//account username
	
	private String passcode;	//account password
	
	private double balance;		//account balance
	
	private int account_id;		//account ID number
	
	//private ArrayList<Notification> notifications;
	
	//private list of notifications
	
	public Customer(String customerKey, int customerType, String username, String password, double balance,
			int accountID) {
		super();
		this.customer_key = customerKey;
		this.user_type = customerType;
		this.username = username;
		this.passcode = password;
		this.balance = balance;
		this.account_id = accountID;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
		this.customer_key = null;
		this.user_type = -4;
		this.username = null;
		this.passcode = null;
		this.balance = -4;
		this.account_id = -4;
		
	}

	public String getCustomerKey() {
		return customer_key;
	}
	public void setCustomerKey(String customerKey) {
		this.customer_key = customerKey;
	}
	public int getCustomerType() {
		return user_type;
	}
	public void setCustomerType(int customerType) {
		this.user_type = customerType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return passcode;
	}
	public void setPassword(String password) {
		this.passcode = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccountID() {
		return account_id;
	}
	public void setAccountID(int accountID) {
		this.account_id = accountID;
	}
}
