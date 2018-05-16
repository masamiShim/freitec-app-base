package com.freitech.kotetsu.db.repos.specifications;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationBase<T> {

	/**
	 * 部分一致
	 * 
	 * @param param
	 * @return
	 */
	protected String castPartLikeString(String param) {
		return "%".concat(param).concat("%");

	}

	/**
	 * 前方一致
	 * 
	 * @param param
	 * @return
	 */
	protected String castHeadLikeString(String param) {
		return param.concat("%");
	}

	/**
	 * 後方一致
	 * 
	 * @param param
	 * @return
	 */
	protected String castTailLikeString(String param) {
		return "%".concat(param);
	}

	public Specification<T> deleted(boolean deleted) {
		return deleted ? (root, query, cb) -> {
			return cb.isNotNull(root.get("deleted"));
		} : (root, query, cb) -> {
			return cb.isNull(root.get("deleted"));
		};
	}

}
