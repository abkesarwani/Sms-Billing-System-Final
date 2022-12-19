package com.abhishek.sms.billing.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.abhishek.sms.billing.system.model.SmsRecord;

@Repository
public interface SmsRecordRepository extends JpaRepository<SmsRecord, Integer> {

	//SmsRecord getByCustId(int custId);
	SmsRecord findByCustIdAndMonthYear(int custId, String monthAndYear);

	
	

}
