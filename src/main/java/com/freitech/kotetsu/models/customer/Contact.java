package com.freitech.kotetsu.models.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Contact {
	@Column(name = "Phone")
	private String phone;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "Email")
	private String email;

}
