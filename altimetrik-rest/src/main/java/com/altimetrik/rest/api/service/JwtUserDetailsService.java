package com.altimetrik.rest.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.altimetrik.rest.api.model.User;
import com.altimetrik.rest.api.model.UserModel;
import com.altimetrik.rest.api.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		user.orElseThrow(()->new UsernameNotFoundException("Not Found :"+username));
		return new UserModel(user.get());
	}

}
