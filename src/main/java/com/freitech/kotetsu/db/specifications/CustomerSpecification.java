package com.freitech.kotetsu.db.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.models.customer.Customer;

@Component
public class CustomerSpecification extends SpecificationBase<Customer> {

	public Specification<Customer> likeHeadCustomerNo(String customerNo) {
		return customerNo == null ? null : (root, query, cb) -> {
			return cb.like(root.get("customerNo"), castHeadLikeString(customerNo));
		};
	}

	public Specification<Customer> partLikeCorpName(String CorpName) {
		return CorpName == null ? null : (root, query, cb) -> {
			return cb.like(root.get("corpName"), castPartLikeString(CorpName));
		};
	}

}
