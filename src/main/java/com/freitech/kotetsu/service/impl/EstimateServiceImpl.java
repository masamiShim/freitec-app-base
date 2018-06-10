package com.freitech.kotetsu.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repositories.EstimateRepository;
import com.freitech.kotetsu.db.specifications.EstimateSpecification;
import com.freitech.kotetsu.exceptions.BusinessException;
import com.freitech.kotetsu.forms.estimate.EstimateSearchForm;
import com.freitech.kotetsu.models.estimate.Estimate;
import com.freitech.kotetsu.service.EstimateService;

@Service
public class EstimateServiceImpl implements EstimateService {

	@Autowired
	EstimateRepository estimateRepository;

	@Autowired
	EstimateSpecification spec;

	@Override
	public Optional<List<Estimate>> findBySearchCond(EstimateSearchForm cond) {
		return Optional.ofNullable(estimateRepository.findAll(
		  Specifications
		    .where(spec.customerNoContains(cond.getCustomerNo()))
		    .and(spec.noContains(cond.getNo()))
		    .and(spec.estimateDateFrom(cond.getDateFrom()))
		    .and(spec.estimateDateTo(cond.getDateTo()))));
	}

	@Override
	public Optional<Estimate> regist(Estimate form) {
		return Optional.ofNullable(estimateRepository.save(form));
	}

	@Override
	public Estimate update(Long id, Estimate form) {
		return estimateRepository.findById(id).map(exists -> {
			exists.setPersistInfo(form);
			return estimateRepository.save(exists);
		}).orElseThrow(BusinessException::new);
	}

	@Override
	public boolean delete(Long id) {
		return estimateRepository.findById(id).map(exists -> {
			exists.setDeleted(LocalDateTime.now());
			return estimateRepository.save(exists) != null ? true : false;
		}).orElseThrow(BusinessException::new);
	}

}
