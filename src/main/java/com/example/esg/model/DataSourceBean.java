package com.example.esg.model;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceBean {
	@ConfigurationProperties(prefix="spring.datasource")
	@Primary
	@Bean
	public DataSource dataSource () {
		return DataSourceBuilder.create().build();
	}

}
