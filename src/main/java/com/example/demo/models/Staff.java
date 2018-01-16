package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@ToString
@Table(name = "Staff")
public class Staff extends SecurityAuditor {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name="staffId")
  private Long id;

  @Column(name="firstName")
  private String firstName;
  
  @Column(name="lastName")
  private String lastName;
  
  @Column(name="telNo")
  private String telNo;
  
  @OneToOne
  @JoinColumn(name="depertmentId")
  private Depertment depertment;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="customerId")
  private Customer customer;

}
