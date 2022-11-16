package com.code;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "notifications")
public class Notification 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int notification_id;
	private boolean is_email;	
	private int template_id;	
	private boolean is_visible;		
	private boolean is_read;
	private String username;
	private LocalDateTime timestamp;
	
	@Transient
	private HashMap<String, String> data = new HashMap<>();	//JSON
	
	//returns all the data
	public HashMap<String, String> getData()
	{
		return data;
	}
	
	//add data to the field
	public boolean setData(String field, String value)
	{
		try
		{
			data.put(field, value);
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	public int getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(int notification_id) {
		this.notification_id = notification_id;
	}

	public String getCustomer_key() {
		return username;
	}

	public void setCustomer_key(String customer_key) {
		this.username = customer_key;
	}

	public boolean isIs_email() {
		return is_email;
	}

	public void setIs_email(boolean is_email) {
		this.is_email = is_email;
	}

	public int getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}

	public boolean isIs_visible() {
		return is_visible;
	}

	public void setIs_visible(boolean is_visible) {
		this.is_visible = is_visible;
	}

	public boolean isIs_read() {
		return is_read;
	}

	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}

	public Notification(int notification_id, String customerKey, boolean isEmail, int templateID, boolean isVisible,
			boolean isRead) {
		super();
		this.notification_id = notification_id;
		this.username = customerKey;
		this.is_email = isEmail;
		this.template_id = templateID;
		this.is_visible = isVisible;
		this.is_read = isRead;
		this.setTimestamp(LocalDateTime.now()); //time of creation
		//temp data
		data.put("customer Name", "Pavan");
		data.put("date", "10/14/2024");
	}
	
	public Notification()
	{
		this.notification_id = -4;
		this.username = "defaultkey";
		this.is_email = false;
		this.template_id = -4;
		this.is_visible = false;
		this.is_read = false;
		this.setTimestamp(LocalDateTime.now());
		this.setTimestamp(timestamp);
		//temp data
		data.put("customer Name", "Pavan");
		data.put("date", "10/14/2024");
	}

	public String getTimestamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return timestamp.format(formatter);
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
