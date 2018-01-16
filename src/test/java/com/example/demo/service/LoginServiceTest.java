package com.example.demo.service;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.demo.AbstractTest;
import com.example.demo.service.impl.LoginServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginServiceTest extends AbstractTest{


	@Test
	public void 暗号化とでコードテスト(){
		String seeds = "test";
		LoginServiceImpl loginService = new LoginServiceImpl();
		
		String encrypted  = loginService.encryptWithSalt(seeds);
		log.debug(encrypted);
//		assertTrue(loginService.isMatch(seeds, encrypted));
	}
	
	@Before
	public void setUp(){
		
	}

	@After
	public void after(){
		
	}

}
