package com.example.demo.dao;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.models.User;

public class MyUserDetails implements UserDetails{



/**
	 * 
	 */
private static final long serialVersionUID = -2105440858334026592L;
private String username;
private String password;
private boolean isActive;
private List<GrantedAuthority> roles;
	

	public MyUserDetails(User user) {

		this.username = user.getUsername();
		this.password = user.getPassword();
		this.isActive = user.isActive();
		this.roles = user.getRoleNames().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}
	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}

	
	

}
