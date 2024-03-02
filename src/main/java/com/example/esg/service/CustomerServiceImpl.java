package com.example.esg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.esg.dao.CustomerDao;
import com.example.esg.model.Customer;


@Component
@Service
public class CustomerServiceImpl  implements CustomerService {

	@Autowired
    CustomerDao customerDao;
	
	@Override
	public Customer getCustomerData(String customerId) {
		return customerDao.getCustomerData(customerId);
	}

	@Override
	public int saveCustomerData(Customer customer) {
		return customerDao.saveCustomerData(customer);
	}
}
