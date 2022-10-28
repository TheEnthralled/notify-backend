package com.PoC;

public class Customer {
	
	private String accountNumber;
	private String username;
	private String password;
	private double accountBalance;
	private int unreadCount;
	
	public Customer(String userID, String username, String password, double accountBalance, int unreadCount) {
		super();
		this.accountNumber = userID;
		this.username = username;
		this.password = password;
		this.accountBalance = accountBalance;
		this.unreadCount = unreadCount;
	}
	
	public String getUserID() {
		return accountNumber;
	}
	public void setUserID(String userID) {
		this.accountNumber = userID;
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
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public int getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}
}
