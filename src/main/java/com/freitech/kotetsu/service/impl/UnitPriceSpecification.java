package com.freitech.kotetsu.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.db.repos.specifications.SpecificationBase;
import com.freitech.kotetsu.models.UnitPrice;
import com.freitech.kotetsu.models.customer.Customer;
import com.freitech.kotetsu.models.item.Item;

@Component
public class UnitPriceSpecification extends SpecificationBase<UnitPrice> {

	public Specification<UnitPrice> equalsCustomer(Customer customer) {
		return customer == null ? null : (root, query, cb) -> {
			return cb.equal(root.get("customer"), customer);
		};
	}

	public Specification<UnitPrice> equalsItem(Item item) {
		return item == null ? null : (root, query, cb) -> {
			return cb.equal(root.get("item"), item);
		};
	}

}
