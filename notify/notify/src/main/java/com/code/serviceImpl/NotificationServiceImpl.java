package com.code.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.code.model.Notification;
import com.code.service.NotificationService;
import com.code.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired NotificationRepository notificationRepository;

	@Override
	public Notification getByID(int requestedID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getByCustomerKey(String requestedCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Notification currentNotification) {
		// TODO Auto-generated method stub
		return true;
	}
}