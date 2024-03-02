package com.example.esg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.esg.model.Customer;
import com.example.esg.service.CustomerService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customer/getCustomerData")
	public ResponseEntity<Customer> getCustomerData (@RequestParam String customerId) {
		        log.info("Inside getCustomerData Controller with customerId: "+customerId);
		        Customer customer=customerService.getCustomerData(customerId);
		        log.info("Retrieved customer"+customer.toString());
				return new ResponseEntity<Customer> (customer,HttpStatus.OK);
	}
	
	@PostMapping(value = "/customer/saveCustomerData", consumes = "application/json", produces = "text/plain")
	public ResponseEntity<String> saveCustomerData (@RequestBody Customer customer) {
		log.info("Inside saveCustomerData Controller with customer: "+customer.toString());
		int response = customerService.saveCustomerData(customer);
		if(response == 1) {
			 log.info("Customer information is saved successfully");
		     return new ResponseEntity<String>("Saved Successfully",HttpStatus.OK);
		}
		else {
			 log.info("Customer information is not saved successfully");
			 return new ResponseEntity<String>("Failed to Save",HttpStatus.OK);
		}
	}
}
