package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.config.security.SpringSecurityAuditorAware;

@EnableJpaAuditing
@Configuration
public class AuditingConfig {
	
	@Bean
	public AuditorAware<String> createAuditorProvider(){
		return new SpringSecurityAuditorAware();
	}
	
	@Bean
	public AuditingEntityListener createAuditingListener(){
		return new AuditingEntityListener();
	}
	
}
