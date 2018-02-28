package com.freitech.kotetsu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "UserAttribute")
public class UserAttributeEntity extends SecurityAuditor {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "UserAttributeId")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "UserId")
  private User user;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "zipCode")
  private String zipCode;
  
  @Column(name = "gender")
  private boolean gender;
  
  @Column(name = "address1")
  private String address1;
  
  @Column(name = "address2")
  private String address2;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "telNo")
  private String telNo;

}
