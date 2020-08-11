package com.example.demo;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@Configuration
@PropertySource(value = {"classpath:application.properties" })
public class PojoConfig {

	@Autowired
	Environment env;
	
	
	@Bean
	public DataSource dataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();

		    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		    dataSource.setUsername(env.getProperty("spring.datasource.username"));
		    dataSource.setPassword(env.getProperty("spring.datasource.password"));
		    dataSource.setUrl(env.getProperty("spring.datasource.url")); 
		     
		    return dataSource;
		}
	

}
