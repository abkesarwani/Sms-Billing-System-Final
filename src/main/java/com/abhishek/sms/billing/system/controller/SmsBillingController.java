package com.abhishek.sms.billing.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.sms.billing.system.service.SmsBillingService;



@RestController
@RequestMapping("api/v1/sms-billing-service")
public class SmsBillingController {
	
	@Autowired
	private SmsBillingService billingService;
	
	@GetMapping("/sendSms/{custID}")  
	public ResponseEntity<String>  sendSms(@PathVariable("custID") int custID)   
	{  
		
	String result = billingService.sendSms(custID);
	
	return new ResponseEntity<>(result, HttpStatus.OK);
	
	
	}  
	
	@GetMapping("/getbill/{custID}") 
	public ResponseEntity<String> getCurrentMonthBill (@PathVariable("custID") int custID)   
	{  
		
	String result = billingService.getCurrentMonthBill(custID);
	
	return new ResponseEntity<>(result, HttpStatus.OK);
	
	}  

}
