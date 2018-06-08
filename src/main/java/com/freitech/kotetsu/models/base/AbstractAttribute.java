package com.freitech.kotetsu.models.base;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.freitech.kotetsu.models.SecurityAuditor;
import com.freitech.kotetsu.models.customer.Address;
import com.freitech.kotetsu.models.customer.Contact;
import com.freitech.kotetsu.models.customer.Name;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@Embeddable
public abstract class AbstractAttribute{


	@Embedded
	protected Name name;
	
	@Embedded
	protected Address address;

	@Embedded
	protected Contact contact;

	@Column(name = "Remark1")
	protected String remark1;

	@Column(name = "Remark2")
	protected String remark2;
}
