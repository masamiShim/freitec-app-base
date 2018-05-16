package com.freitech.kotetsu.service;

import java.util.List;
import java.util.Optional;

import com.freitech.kotetsu.forms.estimate.EstimateSearchForm;
import com.freitech.kotetsu.models.estimate.Estimate;

public interface EstimateService {
	Optional<List<Estimate>> findBySearchCond(EstimateSearchForm cond);

	/**
	 * 登録処理
	 * 
	 * @param form
	 * @return
	 */
	Optional<Estimate> regist(Estimate form);

	/**
	 * 更新処理
	 * 
	 * @param form
	 * @return
	 */
	Estimate update(Long id, Estimate form);

	boolean delete(Long id);

}
