package com.freitech.kotetsu.db.specifications;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.models.User;

@Component
public class UserSpecification extends SpecificationBase<User> {

	public Specification<User> loginIdContains(String loginId) {
		return StringUtils.isEmpty(loginId) ? null : (root, query, cb) -> {
			return cb.like(root.get("loginId"), castPartLikeString(loginId));
		};
	}

	public Specification<User> emailContains(String email) {
		return StringUtils.isEmpty(email) ? null : (root, query, cb) -> {
			return cb.like(root.get("email"), castPartLikeString(email));
		};
	}

}
