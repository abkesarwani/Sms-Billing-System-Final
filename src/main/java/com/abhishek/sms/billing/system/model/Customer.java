package com.abhishek.sms.billing.system.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column
	String name;
	@Column
	String plan;

	
	public Customer() {}
	
	public Customer(int id, String name, String plan) {
		super();
		this.id = id;
		this.name = name;
		this.plan = plan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", plan=" + plan + "]";
	}
	

}
