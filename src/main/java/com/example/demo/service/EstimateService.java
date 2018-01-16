package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.forms.estimate.EstimateSearchForm;
import com.example.demo.models.Estimate;

public interface EstimateService {
  Optional<List<Estimate>> findBySerachCond(EstimateSearchForm cond);
}
