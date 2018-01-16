package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.example.demo.db.repos.EstimateRepository;
import com.example.demo.db.repos.specifications.EstimateSpecification;
import com.example.demo.forms.estimate.EstimateSearchForm;
import com.example.demo.models.Estimate;
import com.example.demo.service.EstimateService;

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
