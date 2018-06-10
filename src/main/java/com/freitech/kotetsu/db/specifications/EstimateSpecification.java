package com.freitech.kotetsu.db.specifications;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.models.estimate.Estimate;

@Component
public class EstimateSpecification extends SpecificationBase<Estimate> {
	public Specification<Estimate> noContains(String no) {
		return StringUtils.isEmpty(no) ? null : (root, query, cb) -> {
			return cb.like(root.get("no"), castHeadLikeString(no));
		};
	}

	public Specification<Estimate> customerNoContains(String customerNo) {
		return StringUtils.isEmpty(customerNo) ? null : (root, query, cb) -> {
			return cb.equal(root.get("customer.no"), customerNo);
		};
	}

	public Specification<Estimate> estimateDateFrom(LocalDate startDate) {
		return startDate == null ? null : (root, query, cb) -> {
			return cb.greaterThanOrEqualTo(root.get("date"), startDate);
		};
	}

	public Specification<Estimate> estimateDateTo(LocalDate endDate) {
		return endDate == null ? null : (root, query, cb) -> {
			return cb.lessThanOrEqualTo(root.get("date"), endDate);
		};
	}
}
