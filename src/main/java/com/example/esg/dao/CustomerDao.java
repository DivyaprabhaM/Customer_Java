package com.example.esg.dao;

import org.springframework.stereotype.Component;
import com.example.esg.model.Customer;


@Component
public interface CustomerDao {

	public Customer getCustomerData(String customerId);

	public int saveCustomerData(Customer customer);

}
