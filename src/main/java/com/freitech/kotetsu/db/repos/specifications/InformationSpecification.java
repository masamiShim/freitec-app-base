package com.freitech.kotetsu.db.repos.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.models.information.Information;

@Component
public class InformationSpecification extends SpecificationBase<Information> {

	public Specification<Information> startDateGte(LocalDate startDate) {
		return startDate == null ? null : (root, query, cb) -> {
			return cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
		};
	}

	public Specification<Information> endDateLte(LocalDate endDate) {
		return endDate == null ? null : (root, query, cb) -> {
			return cb.lessThanOrEqualTo(root.get("endDate"), endDate);
		};
	}

}
