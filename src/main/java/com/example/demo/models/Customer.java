package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer extends SecurityAuditor{

  @Id
  @Column(name="customerId")
  private Long id;

  private static final long serialVersionUID = 1L;

}
