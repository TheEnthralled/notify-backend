package com.PoC;

public class Notification {
	private boolean isEmail;
	private String subject;
	private String body;
	private String date;
	private boolean isRead;
	
	public Notification(boolean isEmail, String subject, String body, String date, boolean isRead) {
		super();
		this.isEmail = isEmail;
		this.subject = subject;
		this.body = body;
		this.date = date;
		this.isRead = isRead;
	}
	
	public boolean isEmail() {
		return isEmail;
	}
	public void setEmail(boolean isEmail) {
		this.isEmail = isEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

}
