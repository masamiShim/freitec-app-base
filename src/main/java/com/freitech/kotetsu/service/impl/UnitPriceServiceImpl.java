package com.freitech.kotetsu.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repositories.UnitPriceRepository;
import com.freitech.kotetsu.exceptions.BusinessException;
import com.freitech.kotetsu.forms.unitPrice.UnitPriceSearchForm;
import com.freitech.kotetsu.models.UnitPrice;
import com.freitech.kotetsu.service.UnitPriceService;

@Service
public class UnitPriceServiceImpl implements UnitPriceService {

	@Autowired
	UnitPriceSpecification spec;

	@Autowired
	UnitPriceRepository unitPriceRepository;

	@Override
	public UnitPrice regist(UnitPrice form) {
		return unitPriceRepository.save(form);
	}

	@Override
	public boolean delete(Long id) {
		return unitPriceRepository.findByIdAndDeletedIsNull(id).map(exists -> {
			exists.setDeleted(LocalDateTime.now());
			return unitPriceRepository.save(exists) != null ? true : false;
		}).orElseThrow(BusinessException::new);

	}

	@Override
	public UnitPrice update(Long id, UnitPrice form) {
		return unitPriceRepository.findByIdAndDeletedIsNull(id).map(exists -> {
			exists.setPersistInfo(form);
			return unitPriceRepository.save(exists);
		}).orElseThrow(BusinessException::new);
	}

	@Override
	public List<UnitPrice> search(UnitPriceSearchForm form) {
		return unitPriceRepository.findAll(Specifications
		  .where(spec.equalsCustomer(form.getCustomer() != null ? form.getCustomer()
		    : null))
		  .and(spec.equalsItem(form.getItem() != null ? form.getItem()
		    : null))
		  .and(spec.deleted(false)));

	}

}
