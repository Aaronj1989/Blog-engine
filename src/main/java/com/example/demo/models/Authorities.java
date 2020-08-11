package com.example.demo.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authorities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName="id", nullable = false)
	private User user;
	
	@Column(name="role_name")
	String roleName;
	
	
	public String toString() {
		return this.getRoleName();
	}
	
	
	
}
