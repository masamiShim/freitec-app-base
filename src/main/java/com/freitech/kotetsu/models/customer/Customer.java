package com.freitech.kotetsu.models.customer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.freitech.kotetsu.models.SecurityAuditor;

@Entity
public class Customer extends SecurityAuditor {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	List<CustomerAttribute> attributes;

}
