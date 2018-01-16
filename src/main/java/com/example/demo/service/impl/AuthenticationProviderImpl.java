package com.example.demo.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo.db.repos.UserRepository;
import com.example.demo.models.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider{
	@Autowired
	private UserRepository userRepository;

	@Override	
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String loginId = auth.getName();
		String password = auth.getCredentials().toString();
		
		if(StringUtils.isEmpty(loginId) || StringUtils.isEmpty(password)){
			throw new AuthenticationCredentialsNotFoundException("login Info has error credntials");
		}
		Optional<UserEntity> user = userRepository.findByLoginIdAndPassword(loginId, password);
		if(!user.isPresent()){
			throw new AuthenticationCredentialsNotFoundException("User Not Found");
		}
		return new UsernamePasswordAuthenticationToken(user.get(), password, auth.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> token){
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(token);
	}
}
