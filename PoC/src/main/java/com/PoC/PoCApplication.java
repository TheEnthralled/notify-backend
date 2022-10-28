package com.PoC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.regex.*;

@SpringBootApplication
@RestController
public class PoCApplication {

	//public static Map<String, Customer> customers;
	
	@GetMapping("/message")
	public String getMessage()
	{
		String str = null;
		for (int i = 0; i < 100; i++)
			str += "test";
		return str + "Endpoint active!";
	}
	
	private final Counter promRequestsTotal = Counter.build()
            .name("requests_total")
            .help("Total number of requests.")
            .register(); 
	
	//login
	@RequestMapping(path = "/login")
    public @ResponseBody boolean login() {
		//insert logic to check if username and password exist in database
		return true;
    }
	
	//account balance
    @RequestMapping(path = "/{userID}/balance")
    public @ResponseBody double accountBalance(@PathVariable("userID") double userID) {
    	
    	if(userID>0)
		return 9640.50;
    	
    	//logic to search through customers and find account balance
    	
    	else
    		return 0;//throw exception
    }
    
    //unread count
    @RequestMapping(path = "/{userID}/unreadCount")
    public @ResponseBody int unreadCount(@PathVariable("userID") int userID) {
    	
    	if(userID>0)
		return 11;
    	
    	//logic to search through and count number of uread notifications
    	
    	else
    		return 0;//throw exception
    }
    
    //delete function
    @RequestMapping(path = "/{userID}/inbox/{notifID}/delete")
    public @ResponseBody boolean delete(@PathVariable("userID") int userID, @PathVariable("notifID") int notifID) {
    	
    	if((userID>0)&&(notifID>0))
		return true;
    	
    	//search through database, find appropriate notification object, and mark its visible status as tru
    	
    	else
    		return false;//throw exception
    }
    
  //get method
    @RequestMapping(path = "/{userID}/inbox/{notifID}")
    public @ResponseBody String getNotification(@PathVariable("userID") int userID, @PathVariable("notifID") int notifID) {
    	
    	//json format
    	String notification = "{\"messages\":[{\"subject\":\"YourCapitalOneCreditCardStatementisReady\",\"body\":\"YourCapitalOneCreditCardStatementisReady\",\"attachments\":[],\"sender\":\"CapitalOne\",\"date\":\"2017-01-01T00:00:00.000Z\"},{\"subject\":\"WelcometoCapitalOne!\",\"body\":\"WelcometoCapitalOne!\",\"attachments\":[],\"sender\":\"CapitalOne\",\"date\":\"2017-01-01T00:00:00.000Z\"}]}";
    	
    	if((userID>0)&&(notifID>0))
		return notification;
    	
    	//search through database, find appropriate notification object, and mark its visible status as tru
    	
    	else
    		return "error";//throw exception
    }
    
    //markAsRead function
    @RequestMapping(path = "/{userID}/inbox/{notifID}/markRead")
    public @ResponseBody boolean markRead(@PathVariable("userID") int userID, @PathVariable("notifID") int notifID) {
    	
    	if((userID>0)&&(notifID>0))
		return true;
    	
    	//search through database, find appropriate notification object, and mark its read status as true
    	
    	else
    		return false;//throw exception
    }

    @RequestMapping(path = "/metrics")
    public void metrics(Writer responseWriter) throws IOException {
        TextFormat.write004(responseWriter, CollectorRegistry.defaultRegistry.metricFamilySamples());
        responseWriter.close();
    }
	
	public static void main(String[] args) {
		//dummy data to be loaded
		/*customers 
        = new HashMap<String, Customer>();
		Customer testCustomer = new Customer("91882345", "johndoe", "password", 125.50, 15);
		customers.put("91882345", testCustomer);
		Notification testNotification = new Notification (true, "Balance Due", "You have an upcoming payment due!", "10142022", false);*/
		
		SpringApplication.run(PoCApplication.class, args);
	}

}
