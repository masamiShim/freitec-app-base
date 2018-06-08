package com.freitech.kotetsu.models.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Name {
	
	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "FirstNameKana")
	private String firstNameKana;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "LastNameKana")
	private String lastNameKana;
	
}
