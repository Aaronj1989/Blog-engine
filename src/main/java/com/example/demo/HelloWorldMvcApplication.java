package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.dao.ArticleRepository;
import com.example.demo.dao.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepository.class,ArticleRepository.class})
public class HelloWorldMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldMvcApplication.class, args);
		
		
	}
	
	

}
