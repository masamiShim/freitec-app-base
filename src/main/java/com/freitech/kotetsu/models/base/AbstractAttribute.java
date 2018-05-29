package com.freitech.kotetsu.models.base;

import javax.persistence.Column;
import javax.persistence.Embedded;

import com.fasterxml.jackson.core.sym.Name;
import com.freitech.kotetsu.models.SecurityAuditor;
import com.freitech.kotetsu.models.customer.Address;
import com.freitech.kotetsu.models.customer.Contact;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractAttribute extends SecurityAuditor {


	@Embedded
	private Name name;
	
	@Embedded
	private Address address;

	@Embedded
	private Contact contact;

	@Column(name = "Remark1")
	private String remark1;

	@Column(name = "Remark2")
	private String remark2;
}
