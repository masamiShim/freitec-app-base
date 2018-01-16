package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.db.repos.UserRepository;
import com.example.demo.models.UserEntity;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException{
		Optional<UserEntity> user = userRepository.findByLoginId(loginId);
		if(!user.isPresent()){
			throw new UsernameNotFoundException("Not Found User -> loginId = " + loginId);
		}
		return user.get();
		
	}
}
