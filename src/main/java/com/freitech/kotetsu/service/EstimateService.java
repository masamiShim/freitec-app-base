package com.freitech.kotetsu.service;

import java.util.List;
import java.util.Optional;

import com.freitech.kotetsu.forms.estimate.EstimateSearchForm;
import com.freitech.kotetsu.models.Estimate;

public interface EstimateService {
  Optional<List<Estimate>> findBySerachCond(EstimateSearchForm cond);
}
