package com.example.demo.config;

import java.io.Serializable;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class NamingStrategyImpl extends SpringPhysicalNamingStrategy implements Serializable {

  private static final long serialVersionUID = 1L;
  
  public static final NamingStrategyImpl INSTANCE = new NamingStrategyImpl();

  @Override
  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
      return new Identifier(name.getCanonicalName(), name.isQuoted());
  }

  @Override
  public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
      return new Identifier(name.getText(), name.isQuoted());
  }
}
