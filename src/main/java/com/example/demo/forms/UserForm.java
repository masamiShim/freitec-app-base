package com.example.demo.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserForm {
	@NotEmpty(message="{user.firstName.required}")
	@Length(max = 10, message="{user.firstName.maxlen}")
	private String firstName;
	
	@NotEmpty(message="{user.lastName.required}")
	@Length(max = 10, message="{user.lastName.maxlen}")
	private String lastName;

	@NotEmpty(message="{user.zipCode.required}")
	@Length(max = 10, message="{user.zipCode.maxlen}")
	private String zipCode;

	@NotEmpty(message="{user.gender.required}")
	private boolean gender;
	
	@NotEmpty(message="{user.address1.required}")
	@Length(max = 10, message="{user.address1.maxlen}")
	private String address1;

	@NotEmpty(message="{user.address2.required}")
	@Length(max = 10, message="{user.address2.maxlen}")
	private String address2;
	
	@NotEmpty(message="{user.email.required}")
	@Length(max = 10, message="{user.email.maxlen}")
	private String email;
	
	@NotEmpty(message="{user.telNo.required}")
	@Length(max = 10, message="{user.telNo.maxlen}")
	private String telNo;

}
