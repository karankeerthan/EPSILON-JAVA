package com.epsilon.training.entity;

import com.epsilon.training.annotations.JsonIgnored;
import com.epsilon.training.annotations.JsonProperty;
import com.epsilon.training.annotations.JsonSerializable;
import com.epsilon.training.annotations.XMLProperty;
import com.epsilon.training.annotations.XMLSerializable;

@JsonSerializable
@XMLSerializable
public class Customer {
	@XMLProperty
	private String name;
	@XMLProperty
	private String email;
	@XMLProperty
	private String phone;
	@XMLProperty
	private Address address;
	
	public Customer() {
		this.address = new Address();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
