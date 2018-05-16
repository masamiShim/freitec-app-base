package com.freitech.kotetsu.models.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import lombok.Getter;

public abstract class ListModelBase<T> {

	@Getter
	List<T> models = new ArrayList<>();

	public ListModelBase(List<T> others) {
		of(others);
	}

	public ListModelBase<T> of(List<T> others) {
		if (CollectionUtils.isNotEmpty(others)) {
			models = others;
		}
		return this;
	}

	public List<T> getAll() {
		return models;
	}

	public boolean isNotEmpty() {
		return CollectionUtils.isNotEmpty(models);
	}

	public long size() {
		return models.size();
	}

}
