package com.freitech.kotetsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@EnableAutoConfiguration
@PropertySource(value = {"classpath:app.setting.properties"})
public class WebApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(WebApplicationTests.class, args);
	}
	/** PropertySourceで読み込んだpropertyファイルを構成する*/
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}

}
