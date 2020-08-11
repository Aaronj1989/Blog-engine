package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;



@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Authorities> roles;
	@Column(name = "active")
	private boolean isActive;
	@Transient
	private Set<String> roleNames;

	@Transient
	@Autowired
	private UserForm userForm;
	public User() {

	}

	
	public User(String username, String password, boolean isActive, Set<Authorities> roles) {
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.roles = roles;
		
		for(Authorities auth: this.roles) {
			auth.setUser(this);
			
		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Authorities> getRoles() {

		return this.roles;
	}

	public void setRoles(Set<Authorities> roles) {

		this.roles = roles;
		
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<String> getRoleNames() {
		roleNames = new HashSet<String>();
		
		for (Authorities a : roles) {
			
			roleNames.add(a.toString());
		}
		return this.roleNames;
	}

	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}


}
