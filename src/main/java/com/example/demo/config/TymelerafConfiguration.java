package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
public class TymelerafConfiguration {
  @Bean
  public Java8TimeDialect java8TimeDialect() {
    return new Java8TimeDialect();
  }
}
