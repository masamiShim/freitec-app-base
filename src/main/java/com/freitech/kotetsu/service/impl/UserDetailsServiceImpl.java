package com.freitech.kotetsu.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.db.repos.UserRepository;
import com.freitech.kotetsu.models.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException{
		Optional<User> user = userRepository.findByLoginId(loginId);
		if(!user.isPresent()){
			throw new UsernameNotFoundException("Not Found User -> loginId = " + loginId);
		}
		return user.get();
		
	}
}
