package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.demo.validation.PasswordConfirmation;

@Getter
@Setter

@PasswordConfirmation(field = "password", fieldMatch = "repeatPassword", message = "Passwords don't match!")
@Component
public class UserForm {
	private String username;
	private String email;
	private String password;
	private String repeatPassword;

private	ApplicationContext appContext;
    @Autowired
	private UserDetailsService userDetailsService;

	
	// creates new user and saves to the database.
	public User createUser() {
		Set<Authorities> roles = new HashSet<Authorities>();
		Authorities auth = new Authorities();
		auth.setRoleName("ROLE_USER");
		roles.add(auth);

		User user = new User(username, password, true, roles);
       
		return user;
		

	}



	public ApplicationContext getApplictionContext() {
		
		return this.appContext;
	}
	
	
	

}
