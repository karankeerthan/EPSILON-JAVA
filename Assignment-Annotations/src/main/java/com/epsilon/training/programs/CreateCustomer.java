package com.epsilon.training.programs;

import com.epsilon.training.annotations.processors.JsonSerializer;
import com.epsilon.training.entity.Customer;

public class CreateCustomer {

	public static void main(String[] args) {
		Customer c1 = new Customer();
		c1.setName("Karan");
		c1.setEmail("karan@email.com");
		c1.setPhone("9928392483");
		c1.getAddress().setStreet("1st cross");
		c1.getAddress().setCity("Bengaluru");
		c1.getAddress().setState("Karnataka");
		c1.getAddress().setCountry("India");
		
		JsonSerializer js = new JsonSerializer();
		String json = js.serialize(c1);
		
		System.out.println(json);
		

	}
}
