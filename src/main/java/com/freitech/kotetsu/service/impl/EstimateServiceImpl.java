package com.freitech.kotetsu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repos.EstimateRepository;
import com.freitech.kotetsu.db.repos.specifications.EstimateSpecification;
import com.freitech.kotetsu.forms.estimate.EstimateSearchForm;
import com.freitech.kotetsu.models.Estimate;
import com.freitech.kotetsu.service.EstimateService;

@Service
public class EstimateServiceImpl implements EstimateService {

  @Autowired
  EstimateRepository estimateRepository;

  @Autowired
  EstimateSpecification spec;

  @Override
  public Optional<List<Estimate>> findBySerachCond(EstimateSearchForm cond) {
    return Optional.ofNullable(estimateRepository.findAll(
        Specifications
          .where(spec.customerNoContains(cond.getCustomerNo()))
          .and(spec.noContains(cond.getNo()))
          .and(spec.estimateDateFrom(cond.getEstimateDateFrom()))
          .and(spec.estimateDateTo(cond.getEstimateDateTo()))));
  }

}
