package com.example.demo.dao;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  
        Optional<User> user =  userRepository.findByUsername(username);
   
        user.orElseThrow(()-> new UsernameNotFoundException("User not found"));
       return user.map(MyUserDetails::new).get();
          
	}
	
	
	public void saveUser(User user) {
		System.out.println("user id = "+ user.getId());
		userRepository.save(user);
		System.out.println("user is saved");
	}
	
	
	

}
