package com.code.service;

import java.util.List;
import com.code.model.Notification;

public interface NotificationService {

	public Notification getByID(int requestedID);
	public List<Notification> getByCustomerKey(String requestedCustomer);
	public boolean save(Notification currentNotification);
}
