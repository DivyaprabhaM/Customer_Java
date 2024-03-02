package com.example.esg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnection {
	
	private final static String url = "jdbc:postgresql://127.0.0.1/postgres";
	private final static String user = "naveenmani";
	
	public static Connection getDbConnection() {
		Connection con = null;
		try {
			log.info("Trying to establish DBConnection");
			con = DriverManager.getConnection(url,user,null);
			log.info("DBconnection is established");
		} catch(SQLException e) {
			log.info(e.getMessage());
		}
		return con;
		}

}
