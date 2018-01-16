package com.example.demo.models;

import java.time.LocalDate;

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

@Entity
@Data
@Table(name="Login_Lock_Attribute")
public class LoginLockAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name="loginAttributeId")
    private long id;
  
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private UserEntity user;
	
    @Column(name="loginFailure")
	private int loginFailure;
	
    @Column(name="lastFailed")
	private LocalDate lastFailed;
	
}
