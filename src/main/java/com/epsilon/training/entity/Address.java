package com.epsilon.training.entity;

import com.epsilon.training.annotations.JsonSerializable;
import com.epsilon.training.annotations.XMLProperty;
import com.epsilon.training.annotations.XMLSerializable;

@JsonSerializable
@XMLSerializable
public class Address {
	@XMLProperty
	private String street;
	@XMLProperty
	private String city;
	@XMLProperty
	private String state;
	@XMLProperty
	private String country;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
