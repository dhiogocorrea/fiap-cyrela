package com.es.fiapcyreloapi.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.es.fiapcyreloapi.dao.User userCyrelo = customerService.getByEmail(email);
		if (userCyrelo != null && userCyrelo.getEmail().equals(email)) {
			return new User(userCyrelo.getEmail(), userCyrelo.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
	}
}
