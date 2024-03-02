package com.example.esg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.esg.model.Customer;
import com.example.esg.model.DBConnection;

import lombok.extern.slf4j.Slf4j;

@Repository ("customerDao")
@Component
@Service
@Slf4j
public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer getCustomerData(String customerId) {
		Customer customer = new Customer();
		Connection con = null;
		
		try {
			con = DBConnection.getDbConnection();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select * from minirestproject.customer_info");
			sqlQuery.append (" where customer_id = ?");
			
			PreparedStatement ps = con.prepareStatement(sqlQuery.toString());
			ps.setInt(1, Integer.parseInt(customerId));
			log.info(ps.toString());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customer.setCustomerId(rs.getInt("customer_id"));
				customer.setCustomerName(rs.getString("customer_name"));
				customer.setAddressLine1(rs.getString("address_line1"));
				customer.setAddressLine2(rs.getString("address_line2"));
				customer.setTown(rs.getString("town"));
				customer.setCounty(rs.getString("county"));
				customer.setPostcode(rs.getString("postcode"));
				customer.setCountry(rs.getString("country"));
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			con = null;
		}
		return customer;
	}

	@Override
	public int saveCustomerData(Customer customer) {
		
		Connection con = null;
		int response = 0;
		
		try {
			con = DBConnection.getDbConnection();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append ("insert into minirestproject.customer_info");
			sqlQuery.append(" (customer_id,customer_name,address_line1,address_line2,town,postcode,county,country)");
			sqlQuery.append(" values (?,?,?,?,?,?,?,?)");
			
			PreparedStatement ps = con.prepareStatement(sqlQuery.toString());
			ps.setInt(1, customer.getCustomerId());
			ps.setString(2, customer.getCustomerName());
			ps.setString(3, customer.getAddressLine1());
			ps.setString(4, customer.getAddressLine2());
			ps.setString(5, customer.getTown());
			ps.setString(6, customer.getPostcode());
			ps.setString(7, customer.getCounty());
			ps.setString(8, customer.getCountry());
			
			log.info(ps.toString());
		
			int rs = ps.executeUpdate();
			
			if(rs > 0) {
				response = 1;
				}
			
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			con =null;
		}
		 return response;
	}

}
