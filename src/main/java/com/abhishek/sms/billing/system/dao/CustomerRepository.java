package com.abhishek.sms.billing.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.sms.billing.system.model.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
	
	Customer findById(int custId);

}
