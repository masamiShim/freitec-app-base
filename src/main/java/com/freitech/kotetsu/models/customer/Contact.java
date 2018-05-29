package com.freitech.kotetsu.models.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {
	@Column(name = "PhoneNum")
	private String phoneNum;

	@Column(name = "FaxNum")
	private String faxNum;

	@Column(name = "Email")
	private String email;

}
