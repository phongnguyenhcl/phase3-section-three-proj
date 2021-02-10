package com.phase3section3project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.phase3section3project.model.User;
import com.phase3section3project.repository.UserRepository;
import com.phase3section3project.service.UserService;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("No matching user!");
		}
		return new CustomUserDetails(user);
	}

}
