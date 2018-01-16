package com.example.demo.forms.customer;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.example.demo.forms.FormBase;
import com.example.demo.models.UserEntity;

import lombok.Data;
import lombok.ToString;

@Component
@ToString
@Data
public class CustomerForm implements FormBase{
	
	private Integer id;
	
	@NotEmpty(message="{name.required}")
	@Length(max = 10, message="{name.maxlen}")
	private String name;

	@NotEmpty(message="{loginId.required}")
	@Size(min = 6, max = 255, message="{loginId.range}")
	private String loginId;
	
	@NotEmpty(message="{password.required}")
	@Size(min = 8, max = 20, message="{password.range}")
	private String password;
	
	@NotEmpty(message="{email.required}")
	@Length(max = 255, message="{email.maxlen}")
	private String email;
	
	public UserEntity createUser(){
		
		return new UserEntity(this.loginId,this.password);
	}
}
