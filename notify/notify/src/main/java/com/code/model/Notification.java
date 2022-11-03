package com.code.model;

import java.time.*;
import org.json.simple.*;

public class Notification 
{
	private int notificationID; 		//unique notification id
	private int customerKey;			//customer associated with notification
	private boolean isEmail;			//whether or not notification is email
	private int templateID;				//type of template it is associated with
	private boolean isVisible;			//whether or not notification has been cleared from portal
	private boolean isRead;				//has the notification been read
	private LocalDateTime dateReceived;	//when the notification was received
	private JSONObject dataHooks;		//the data to be inserted into the template
	
	public int getNotificationID() {
		return notificationID;
	}
	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}
	public int getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(int customerKey) {
		this.customerKey = customerKey;
	}
	public boolean isEmail() {
		return isEmail;
	}
	public void setEmail(boolean isEmail) {
		this.isEmail = isEmail;
	}
	public int getTemplateID() {
		return templateID;
	}
	public void setTemplateID(int templateID) {
		this.templateID = templateID;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public LocalDateTime getDateReceived() {
		return dateReceived;
	}
	public void setDateReceived(LocalDateTime dateReceived) {
		this.dateReceived = dateReceived;
	}
	public JSONObject getDataHooks() {
		return dataHooks;
	}
	public void setDataHooks(JSONObject dataHooks) {
		this.dataHooks = dataHooks;
	}
}
