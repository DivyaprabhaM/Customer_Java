package com.example.esg.service;


import org.springframework.stereotype.Component;
import com.example.esg.model.Customer;

@Component
public interface CustomerService {

	public Customer getCustomerData(String customerId);

	public int saveCustomerData(Customer customer);

}
