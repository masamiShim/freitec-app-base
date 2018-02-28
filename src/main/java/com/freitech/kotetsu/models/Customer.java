package com.freitech.kotetsu.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Customer extends SecurityAuditor {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	List<CustomerAttribute> attributes;

}
