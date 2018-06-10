package com.freitech.kotetsu.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repositories.InformationRepository;
import com.freitech.kotetsu.db.specifications.InformationSpecification;
import com.freitech.kotetsu.exceptions.BusinessException;
import com.freitech.kotetsu.forms.system.information.InformationSearchForm;
import com.freitech.kotetsu.models.information.Information;
import com.freitech.kotetsu.service.InformationService;

@Service
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationRepository informationRepository;

	@Autowired
	InformationSpecification spec;

	@Override
	public List<Information> getInformationList(InformationSearchForm form) {
		return informationRepository.findAll(Specifications
		  .where(spec.startDateGte(form.getFromTo().getStartDate()))
		  .and(spec.endDateLte(form.getFromTo().getEndDate()))
		  .and(spec.deleted(false)));

	}

	@Override
	public Information update(Long id, Information form) {
		return informationRepository.findById(id).map(exists -> {
			exists.setPersistInfo(form);
			return informationRepository.save(exists);
		}).orElseThrow(BusinessException::new);
	}

	@Override
	public Information regist(Information form) {
		return informationRepository.save(form);
	}

	@Override
	public boolean delete(Long id) {
		return informationRepository.findById(id).map(exists -> {
			exists.setDeleted(LocalDateTime.now());
			return informationRepository.save(exists) != null ? true : false;
		}).orElseThrow(BusinessException::new);
	}

	@Override
	public List<Information> findByDateFromToAndDeletedNull(LocalDate date) {
		return informationRepository.findByCurrentDisplayInfo(date).orElse(Collections.emptyList());
	}

}
