package com.example.esg.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Customer {
	
	 private int customerId;
	 private String customerName;
	 private String addressLine1;
	 private String addressLine2;
	 private String town;
	 private String county;
	 private String country;
	 private String postcode;
	
}
