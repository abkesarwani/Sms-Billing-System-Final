package com.abhishek.sms.billing.system.service;


public interface SmsBillingService {

	String sendSms(int custID);

	String getCurrentMonthBill(int custID);

}
