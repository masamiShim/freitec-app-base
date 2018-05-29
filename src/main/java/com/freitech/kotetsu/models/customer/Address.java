package com.freitech.kotetsu.models.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name = "ZipCode")
	private String zipCode;
	
	@Column(name = "Address1")
	private String address1;

	@Column(name = "Address2")
	private String address2;

	@Column(name = "Address3")
	private String address3;
}
