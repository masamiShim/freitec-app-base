package com.freitech.kotetsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.freitech.kotetsu.config.annotations.MyDialect;

@SpringBootApplication
@PropertySource(value = { "classpath:application.yml" })
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	/** PropertySourceで読み込んだpropertyファイルを構成する */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public MyDialect dialect() {
		return new MyDialect();
	}
}
