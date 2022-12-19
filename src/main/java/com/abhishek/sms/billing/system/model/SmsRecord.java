package com.abhishek.sms.billing.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name ="SMS_RECORD")
public class SmsRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column
	int custId;
	@Column
	int smsCount;
	@Column
	String monthYear;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getSmsCount() {
		return smsCount;
	}
	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}

	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	@Override
	public String toString() {
		return "SmsRecord [id=" + id + ", custId=" + custId + ", smsCount=" + smsCount + ", monthYear="
				+ monthYear + "]";
	}
	
	
	
}
