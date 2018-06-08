package com.freitech.kotetsu.db.specifications;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.models.customer.Customer;
import com.freitech.kotetsu.models.customer.CustomerAttribute;

@Component
public class CustomerSpecification extends SpecificationBase<Customer> {

	public Specification<Customer> likeHeadCustomerNo(String customerNo) {
		return customerNo == null ? null : (root, query, cb) -> {
			return cb.like(root.get("customerNo"), castHeadLikeString(customerNo));
		};
	}

	public Specification<Customer> partLikeCorpName(String corpName) {
		return corpName == null ? null : (root, query, cb) -> {
			Join<Customer, CustomerAttribute> attribute = root.join("attributes", JoinType.INNER);
			return cb.like(attribute.get("corpName"), castPartLikeString(corpName));
		};
	}

}
