package com.freitech.kotetsu.db.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.codes.ItemType;
import com.freitech.kotetsu.models.item.Item;

@Component
public class ItemSpecification extends SpecificationBase<Item> {

	public Specification<Item> likeHeadItemNo(String itemNo) {
		return itemNo == null ? null : (root, query, cb) -> {
			return cb.like(root.get("itemNo"), castHeadLikeString(itemNo));
		};
	}

	public Specification<Item> likeName(String name) {
		return name == null ? null : (root, query, cb) -> {
			return cb.like(root.get("name"), castPartLikeString(name));
		};
	}

	public Specification<Item> equalsType(ItemType type) {
		return type == null ? null : (root, query, cb) -> {
			return cb.equal(root.get("type"), type);
		};
	}

}
