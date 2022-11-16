package com.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.code.Customer;

@Controller
@RequestMapping("/user")
public class MainController 
{
	ArrayList<Template> templates = new ArrayList<>(Arrays.asList(
				new Template("n/a", "n/a"),
				new Template("You're projected to owe a balance when your loan matures on <date>", "\\nRe: Your auto account ending in <last 4 digits accountNumber>\\n\\n<customer Name>,\\n\\nDue to events that occurred during the life of your loan, you're projected to have a balance due when your loan matures on <date>. You can choose to lower the balance due at the end of your loan now by making an additional one-time payment, or adding a little extra to your current monthly payments.\\n\\nNot sure how this happened? Activate your account in a few easy steps to check out our Auto Loan Tracker and see more details."),
				new Template("Your email address was updated", "\\nSuccess!\\n\\nYou updated your email address on <date>\\n\\nWe're providing this notification for your account security, and no additional action is required.\\n\\nIf you did not make this change, please visit our Information Protection Center.\\n\\nThanks for choosing Capital One®.")
			));
			
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private TemplateRepository templateRepository;
	
	@CrossOrigin
	@PostMapping("/savecustomer")
	public @ResponseBody String saveCustomer(@RequestParam String cKey, @RequestParam int cType, @RequestParam String username, @RequestParam String password, @RequestParam double balance, @RequestParam int accountID)
	{
		Customer temp = new Customer(cKey, cType, username, password, balance, accountID);
		System.out.println("cKey"+temp.getCustomerKey());
		System.out.println("cType"+temp.getCustomerType());
		System.out.println("username"+temp.getUsername());
		System.out.println("password"+temp.getPassword());
		System.out.println("balance"+temp.getBalance());
		System.out.println("accountID"+temp.getAccountID());
		customerRepository.save(temp);	
		return "Saved";
	}
	
	@CrossOrigin
	@PostMapping("/savenotif")
	public @ResponseBody String saveNotif(@RequestParam String username, @RequestParam boolean is_email, @RequestParam int template_id, @RequestParam boolean is_visible, @RequestParam boolean is_read)
	{
		Notification temp = new Notification();
		temp.setCustomer_key(username);
		temp.setIs_email(is_email);
		temp.setTemplate_id(template_id);
		temp.setIs_visible(is_visible);
		temp.setIs_read(is_read);
		notificationRepository.save(temp);
		return "Saved";
	}
	
	@CrossOrigin
	@PostMapping("/savetemplate")
	public @ResponseBody String saveTemplate(@RequestParam String subject, @RequestParam String body)
	{
		Template temp = new Template();
		temp.setSubject(subject);
		temp.setBody(body);
		
		templates.add(temp);

		return "Saved"+templates.size();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/allcustomers", method = RequestMethod.GET)
	public @ResponseBody Iterable<Customer> allCustomers()
	{
		//Iterable<Customer> customers = customerRepository.findAll();
		return customerRepository.findAll();
	}
	
	//newest to oldest emails
	@CrossOrigin
	@RequestMapping(value = "/loadNotifs", method = RequestMethod.GET)
	public @ResponseBody String newestNotifs(@RequestParam String username)
	{
		Collection<Notification> notifs = notificationRepository.getNewestNotifs(username);
		
		String resultSet="[";
		//String notifications = "[{id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(1).getSubject()+", body: "+templates.get(1).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}]";
		for (Iterator<Notification> iterator = notifs.iterator(); iterator.hasNext();)
		{
			Notification currentNotif = iterator.next();
			
			resultSet+="{";
			resultSet+="\"id\": "+currentNotif.getNotification_id()+", ";
			resultSet+="\"title\": \""+fillBlanks(username, currentNotif, templates.get(currentNotif.getTemplate_id()).getSubject())+"\", ";
			resultSet+="\"description\": \""+fillBlanks(username, currentNotif, templates.get(currentNotif.getTemplate_id()).getBody())+"\", ";
			resultSet+="\"time\": \""+currentNotif.getTimestamp()+"\", ";
			
			if(currentNotif.isIs_email())
				resultSet+="\"type\": \"email\", ";
			else
				resultSet+="\"type\": \"text\", ";
			if(currentNotif.isIs_read())
				resultSet+="\"read\": true, ";
			else
				resultSet+="\"read\": false, ";
			if(currentNotif.isIs_visible())
				resultSet+="\"visible\": true";
			else
				resultSet+="\"visible\": false";
			
			resultSet+="}";
			
			if(iterator.hasNext())
				resultSet+=", ";
		}
		
		resultSet+="]";
		
		return resultSet;
	}
	
	public String fillBlanks(String username, Notification input, String raw)
	{
		String digits=""+customerRepository.getAccountIdGivenUser(username);
		digits=digits.substring(digits.length()-4);
		
		HashMap<String, String> tempData = input.getData();
		tempData.put("last 4 digits accountNumber", digits);
		
		for(String key: tempData.keySet())
		{
			raw = raw.replace("<"+key+">", tempData.get(key));
			//System.out.println(raw);
			//System.out.println("\n\n");
		}
        
        //System.out.println(raw);
        return raw;
	}
        
	
	//newest to oldest emails
	@CrossOrigin
	@RequestMapping(value = "/emails/newest", method = RequestMethod.GET)
	public @ResponseBody String newestEmails(@RequestParam String username)
	{
		Collection<Notification> notifs = notificationRepository.getNewestEmails(username);
		
		String resultSet="[";
		//String notifications = "[{id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(1).getSubject()+", body: "+templates.get(1).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}]";
		for (Iterator<Notification> iterator = notifs.iterator(); iterator.hasNext();)
		{
			Notification currentNotif = iterator.next();
			
			resultSet+="{";
			resultSet+="id: "+currentNotif.getNotification_id()+", ";
			resultSet+="subject: "+templates.get(currentNotif.getTemplate_id()).getSubject()+",";
			resultSet+="body: "+templates.get(currentNotif.getTemplate_id()).getBody()+",";
			resultSet+="time: "+currentNotif.getTimestamp()+", ";
				resultSet+="type: email, ";
			if(currentNotif.isIs_read())
				resultSet+="read: true, ";
			else
				resultSet+="read: false";
			if(currentNotif.isIs_visible())
				resultSet+="visible: true";
			else
				resultSet+="visible: false";
			
			resultSet+="}";
			
			if(iterator.hasNext())
				resultSet+=", ";
		}
		
		resultSet+="]";
		
		return resultSet;
	}
	
	//oldest to newest emails
	@CrossOrigin
	@RequestMapping(value = "/emails/oldest", method = RequestMethod.GET)
	public @ResponseBody String oldestEmails(@RequestParam String username)
	{
		Collection<Notification> notifs = notificationRepository.getOldestEmails(username);
		
		String resultSet="[";
		//String notifications = "[{id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(1).getSubject()+", body: "+templates.get(1).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}]";
		for (Iterator<Notification> iterator = notifs.iterator(); iterator.hasNext();)
		{
			Notification currentNotif = iterator.next();
			
			resultSet+="{";
			resultSet+="id: "+currentNotif.getNotification_id()+", ";
			resultSet+="subject: "+templates.get(currentNotif.getTemplate_id()).getSubject()+",";
			resultSet+="body: "+templates.get(currentNotif.getTemplate_id()).getBody()+",";
			resultSet+="time: "+currentNotif.getTimestamp()+", ";
			resultSet+="type: email, ";
			if(currentNotif.isIs_read())
				resultSet+="read: true, ";
			else
				resultSet+="read: false, ";
			if(currentNotif.isIs_visible())
				resultSet+="visible: true, ";
			else
				resultSet+="visible: false, ";
			
			resultSet+="}";
			
			if(iterator.hasNext())
				resultSet+=", ";
		}
		
		resultSet+="]";
		
		return resultSet;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/texts/newest", method = RequestMethod.GET)
	public @ResponseBody String newestTexts(@RequestParam String username)
	{
		Collection<Notification> notifs = notificationRepository.getNewestTexts(username);
		
		String resultSet="[";
		//String notifications = "[{id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(1).getSubject()+", body: "+templates.get(1).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}]";
		for (Iterator<Notification> iterator = notifs.iterator(); iterator.hasNext();)
		{
			Notification currentNotif = iterator.next();
			
			resultSet+="{";
			resultSet+="id: "+currentNotif.getNotification_id()+", ";
			resultSet+="subject: "+templates.get(currentNotif.getTemplate_id()).getSubject()+",";
			resultSet+="body: "+templates.get(currentNotif.getTemplate_id()).getBody()+",";
			resultSet+="time: "+currentNotif.getTimestamp()+", ";
			resultSet+="type: text, ";
			if(currentNotif.isIs_read())
				resultSet+="read: true, ";
			else
				resultSet+="read: false";
			if(currentNotif.isIs_visible())
				resultSet+="visible: true";
			else
				resultSet+="visible: false";
			
			resultSet+="}";
			
			if(iterator.hasNext())
				resultSet+=", ";
		}
		
		resultSet+="]";
		
		return resultSet;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/texts/oldest", method = RequestMethod.GET)
	public @ResponseBody String oldestTexts(@RequestParam String username)
	{
		Collection<Notification> notifs = notificationRepository.getOldestTexts(username);
		
		String resultSet="[";
		//String notifications = "[{id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(1).getSubject()+", body: "+templates.get(1).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}, {id: 1293483, subject: "+templates.get(0).getSubject()+", body: "+templates.get(0).getBody()+", time: '8/11/2022', type: 'text', read: true, visible: true}]";
		for (Iterator<Notification> iterator = notifs.iterator(); iterator.hasNext();)
		{
			Notification currentNotif = iterator.next();
			
			resultSet+="{";
			resultSet+="id: "+currentNotif.getNotification_id()+", ";
			resultSet+="subject: "+templates.get(currentNotif.getTemplate_id()).getSubject()+",";
			resultSet+="body: "+templates.get(currentNotif.getTemplate_id()).getBody()+",";
			resultSet+="time: "+currentNotif.getTimestamp()+", ";
			resultSet+="type: text, ";
			if(currentNotif.isIs_read())
				resultSet+="read: true, ";
			else
				resultSet+="read: false";
			if(currentNotif.isIs_visible())
				resultSet+="visible: true";
			else
				resultSet+="visible: false";
			
			resultSet+="}";
			
			if(iterator.hasNext())
				resultSet+=", ";
		}
		
		resultSet+="]";
		
		return resultSet;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/loadEmails", method = RequestMethod.GET)
	public @ResponseBody String loadEmails(@RequestParam String username)
	{
		ArrayList<Integer> ids = notificationRepository.loadEmails(username);
		
		String resultSet="[";
		
		resultSet+=getNotif(ids.get(0));
		
		for(int i=0; i<ids.size(); i++)
		{
			if(i==0)
				continue;
			List<String> processed = lazyNotif(ids.get(i));
			String temp = processed.get(0);
			System.out.println(temp);
			resultSet+=", {subject: "+templates.get(Integer.parseInt(temp)).getSubject()+", ";
			resultSet+="time: "+lazyNotif(ids.get(i)).get(1)+", ";
			if(lazyNotif(ids.get(i)).get(2).equals("true"))
			resultSet+="read: "+lazyNotif(ids.get(i)).get(2)+"}";
		}
		
		resultSet+="]";
		
		return resultSet;
	}
	
	//technically shouldnt need a subject
	@CrossOrigin
	@RequestMapping(value = "/loadTexts", method = RequestMethod.GET)
	public @ResponseBody String loadTexts(@RequestParam String username)
	{
		ArrayList<Integer> ids = notificationRepository.loadTexts(username);
		
		
		String resultSet="[";
		
		if(ids.size()==0)
			return "";
		
		if(ids.size()>0)
		resultSet+=getNotif(ids.get(0));
		
		//return resultSet;
		
		for(int i=0; i<ids.size(); i++)
		{
			if(i==0)
				continue;
			List<String> processed = lazyNotif(ids.get(i));
			String temp = processed.get(0);
			System.out.println(temp);
			resultSet+=", {subject: "+templates.get(Integer.parseInt(temp)).getSubject()+", ";
			resultSet+="time: "+lazyNotif(ids.get(i)).get(1)+", ";
			if(lazyNotif(ids.get(i)).get(2).equals("true"))
			resultSet+="read: "+lazyNotif(ids.get(i)).get(2)+"}";
		}
		
		resultSet+="]";
		
		return resultSet;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/notif", method = RequestMethod.GET)
	public @ResponseBody String getNotif(@RequestParam int notif_id)
	{
		String resultSet="";
		Notification currentNotif = notificationRepository.getNotification(notif_id);
		
		resultSet+="{";
		resultSet+="id: "+currentNotif.getNotification_id()+", ";
		resultSet+="subject: "+templates.get(currentNotif.getTemplate_id()).getSubject()+", ";
		resultSet+="body: "+templates.get(currentNotif.getTemplate_id()).getBody()+", ";
		resultSet+="time: "+currentNotif.getTimestamp()+", ";
		
		if(currentNotif.isIs_email())
			resultSet+="type: email, ";
		else
			resultSet+="type: text, ";
		if(currentNotif.isIs_read())
			resultSet+="read: true, ";
		else
			resultSet+="read: false, ";
		if(currentNotif.isIs_visible())
			resultSet+="visible: true";
		else
			resultSet+="visible: false";
		
		resultSet+="}";
		
		return resultSet;
	}
	
	public List<String> lazyNotif(int notif_id)
	{
		
		String currentNotif = notificationRepository.lazyNotification(notif_id);
		List<String> data = new ArrayList<String>(Arrays.asList(currentNotif.split(",")));
		System.out.println(currentNotif);
		
		
		return data;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public @ResponseBody double getBalance(@RequestParam String username)
	{
		double accountBalance = 0.0;
		try
		{
			accountBalance = customerRepository.getBalanceGivenUser(username);
		}
		catch(Exception e)
		{
			System.out.println("Exception encountered: "+e);
			accountBalance = -404;
		}
		
		return accountBalance;
	}
	
	//returns true if both username and password are correct
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody boolean login(@RequestParam String username, @RequestParam String password)
	{
		ArrayList<String> usernames = customerRepository.getUsernames();
		ArrayList<String> passcodes = customerRepository.getPasscodes();
		
		if(usernames.contains(username))
			if(passcodes.contains(password))
				return true;
		
		
		return false;
	}
	
	//returns number of unread messages associated with user
	@CrossOrigin
	@RequestMapping(value = "/unreadcount", method = RequestMethod.GET)
	public @ResponseBody int getUnreadCount(@RequestParam String username)
	{
		int unreadCount=0;
		
		try
		{
			ArrayList<Integer> unreadNotifs = notificationRepository.getUnreadNotifs(username);
			unreadCount=unreadNotifs.size();
		}
		catch(Exception e)
		{
			System.out.println("Exception encountered: "+e);
			unreadCount=-404;//error code
		}
		
		return unreadCount;
	}
	
	//returns boolean indicating operation success or failure
	@CrossOrigin
	@RequestMapping(value = "/notification/visibility", method = RequestMethod.GET)
	public @ResponseBody boolean setVisibility(@RequestParam int notif_id, @RequestParam int visStatus)
	{
		boolean success=false;
		
		try
		{
			int result = notificationRepository.setVisibility(notif_id, visStatus);
			if(result>0)
				success=true;
		}
		catch(Exception e)
		{
			System.out.println("Exception encountered: "+e);
		}
		
		return success;
	}
	
	//returns boolean indicating operation success or failure
	@CrossOrigin
	@RequestMapping(value = "/notification/read", method = RequestMethod.GET)
	public @ResponseBody boolean setReadStatus(@RequestParam int notif_id, @RequestParam int readStatus)
	{
		boolean success=false;
		
		try
		{
			int result = notificationRepository.setRead(notif_id, readStatus);
			if(result>0)
				success=true;
		}
		catch(Exception e)
		{
			System.out.println("Exception encountered: "+e);
		}
		
		return success;
	}	
	
}
