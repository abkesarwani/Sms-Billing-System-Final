package com.abhishek.sms.billing.system.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.sms.billing.system.dao.CustomerRepository;
import com.abhishek.sms.billing.system.dao.SmsRecordRepository;
import com.abhishek.sms.billing.system.model.Customer;
import com.abhishek.sms.billing.system.model.SmsRecord;

@Service
public class SmsBillingServiceImpl implements SmsBillingService {

	private static final String GOLD = "GOLD";

	private static final String SILVER = "SILVER";

	private static final String USD = "USD";

	private static final String BASIC = "BASIC";

	@Autowired
	private SmsRecordRepository smsRecordRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String sendSms(int custID) {
		SmsRecord smsRecord;
		Customer customer = customerRepository.findById(custID);
		if (customer == null) {
			
			return "CustomerId " + custID + " is not an valid Id";
		}

		String monthAndYear = getMonthAandYear();

		smsRecord = smsRecordRepository.findByCustIdAndMonthYear(custID, monthAndYear);

		if (smsRecord == null) {
			
			smsRecord = createSmsRecordData(custID, monthAndYear);
			smsRecord = smsRecordRepository.save(smsRecord);
			
		} else {
			
			smsRecord.setSmsCount(smsRecord.getSmsCount() + 1);
			smsRecord = smsRecordRepository.save(smsRecord);
		}

		String smsCost = getSmsCost(customer.getPlan(), smsRecord.getSmsCount());
		
		return "SMS sent successfully. Cost of SMS is " + smsCost + USD;
	}

	
	@Override
	public String getCurrentMonthBill(int custID) {
		Customer customer = customerRepository.findById(custID);

		if (customer == null) {
			return "CustomerId " + custID + " is not an valid Id";
		}

		String monthAndYear = getMonthAandYear();
		
		SmsRecord smsRecord = smsRecordRepository.findByCustIdAndMonthYear(custID, monthAndYear);
		
		if(smsRecord == null) {
			
			return "Bill for " + monthAndYear + " = " + 0.00 + USD;
		}
		
		int smsCount = smsRecord.getSmsCount();

		if (customer.getPlan().equals(BASIC)) {

			return "Bill for " + monthAndYear + " = " + smsCount * 0.001 + USD;

		} else if (customer.getPlan().equals(SILVER)) {

			return getBillForSilverCustomer(monthAndYear, smsCount);

		} else if (customer.getPlan().equals(GOLD)) {

			return getBillForGoldCustomer(monthAndYear, smsCount);
		}

		return "Bill for " + monthAndYear + " = " + 0.00 + USD;
	}

	private String getBillForGoldCustomer(String monthAndYear, int smsCount) {
		if (smsCount < 1001) {

			return "Bill for " + monthAndYear + " = " + 0.00 + USD;

		} else if (smsCount > 100000) {

			double bill = ((100000 - 1000) * 0.003) + ((smsCount - 100000) * 0.0005);

			return "Bill for " + monthAndYear + " = " + bill + USD;

		} else {

			return "Bill for " + monthAndYear + " = " + (smsCount - 1000) * 0.003 + USD;
		}
	}

	private String getBillForSilverCustomer(String monthAndYear, int smsCount) {
		if (smsCount < 101) {

			return "Bill for " + monthAndYear + " = " + 0.00 + USD;

		} else {

			return "Bill for " + monthAndYear + " = " + (smsCount - 100) * 0.002 + USD;
		}
	}

	private String getSmsCost(String plan, int smsCount) {

		if (plan.equals(BASIC)) {
			
			return "0.001";
			
		} else if (plan.equals(SILVER)) {
			
			return smsCount < 101 ? "0.00" : "0.002";
			
		} else if (plan.equals(GOLD)) {

			return getSmsCostForGoldMember(smsCount);

		}
		return "0.001";
	}

	private String getSmsCostForGoldMember(int smsCount) {
		if (smsCount < 1001) {
			
			return "0.00";

		} else if (smsCount > 100000) {
			
			return "0.0005"; 

		} else {

			return "0.003";
		}
	}

	private SmsRecord createSmsRecordData(int custID, String monthAndYear) {
		SmsRecord smsRecord1 = new SmsRecord();
		smsRecord1.setCustId(custID);
		smsRecord1.setSmsCount(1);
		smsRecord1.setMonthYear(monthAndYear);
		return smsRecord1;
	}

	private String getMonthAandYear() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String month = localDate.getMonth().toString();
		int year = localDate.getYear();
		String monthAndYear = month + "-" + year;
		return monthAndYear;
	}
	

}
