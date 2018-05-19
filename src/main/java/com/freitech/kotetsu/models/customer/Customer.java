package com.freitech.kotetsu.models.customer;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.apache.commons.collections.CollectionUtils;

import com.freitech.kotetsu.models.SecurityAuditor;

@Entity
public class Customer extends SecurityAuditor {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	List<CustomerAttribute> attributes;

	private CustomerAttribute getLatestAttribute(LocalDate targetDate) {
		if (CollectionUtils.isNotEmpty(attributes)) {
			return attributes.stream()
				.filter(attr -> attr.getApplyStartDate().isBefore(targetDate))
			  .sorted(Comparator.comparing(CustomerAttribute::getApplyStartDate).reversed())
			  .findFirst()
			  .orElse(null);
		}
		return null;
	}
}
