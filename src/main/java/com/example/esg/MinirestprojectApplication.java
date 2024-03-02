package com.example.esg;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.example.esg.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@Controller
@Slf4j
@ComponentScan ({"com.example.esg.dao", "com.example.esg.controller", "com.example.esg.service", "com.example.esg.model"})
public class MinirestprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinirestprojectApplication.class, args);
		
		try {
			
			FileInputStream customerFile = new FileInputStream("/Users/naveenmani/Documents/minirestproject/CustomerInfo.xlsx");
		    XSSFWorkbook customerWorkBook = new XSSFWorkbook(customerFile);
		    XSSFSheet customerWorkSheet = customerWorkBook.getSheetAt(0);
		    
		    for(int i=1;i<customerWorkSheet.getPhysicalNumberOfRows() ;i++) {
		        log.info("Processing the data in the row: "+i);
		    	Customer customer = new Customer();
		        XSSFRow row = customerWorkSheet.getRow(i);
		            
		        customer.setCustomerId((int) row.getCell(0).getNumericCellValue());
		        customer.setCustomerName(row.getCell(1).getStringCellValue());
		        customer.setAddressLine1(row.getCell(2).getStringCellValue());
		        customer.setAddressLine2(row.getCell(3).getStringCellValue());
		        customer.setTown(row.getCell(4).getStringCellValue());
		        customer.setPostcode(row.getCell(5).getStringCellValue());
		        customer.setCounty(row.getCell(6).getStringCellValue());
		        customer.setCountry(row.getCell(7).getStringCellValue());
		        
		        ObjectMapper mapper = new ObjectMapper();
		        String jsonInString = mapper.writeValueAsString(customer);
		        jsonInString = mapper.writerWithDefaultPrettyPrinter()
		                       .writeValueAsString(customer);
		       
		        RestTemplate restTemplate = new RestTemplate();
		        
		        String url = "http://localhost:8080/customer/saveCustomerData";
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        
		        HttpEntity<String> entity = new HttpEntity<String>(jsonInString,headers);
		        String response = restTemplate.postForObject(url, entity, String.class);
		        log.info("Response from End point: "+response);
		       }
		} catch (Exception ae) {
			ae.printStackTrace();
		}
	}

}
