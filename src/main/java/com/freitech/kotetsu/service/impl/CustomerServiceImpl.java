package com.freitech.kotetsu.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repositories.CustomerRepository;
import com.freitech.kotetsu.db.specifications.CustomerSpecification;
import com.freitech.kotetsu.exceptions.BussinessException;
import com.freitech.kotetsu.forms.customer.CustomerSearchForm;
import com.freitech.kotetsu.models.customer.Customer;
import com.freitech.kotetsu.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerSpecification spec;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer regist(Customer form) {
		return customerRepository.save(form);
	}

	@Override
	public boolean delete(Long id) {
		return customerRepository.findByIdAndDeletedIsNull(id).map(exists -> {
			exists.setDeleted(LocalDateTime.now());
			return customerRepository.save(exists) != null ? true : false;
		}).orElseThrow(BussinessException::new);

	}

	@Override
	public Customer update(Long id, Customer form) {
		return customerRepository.findByIdAndDeletedIsNull(id).map(exists -> {
			exists.setPersistInfo(form);
			return customerRepository.save(exists);
		}).orElseThrow(BussinessException::new);
	}

	@Override
	public List<Customer> getSearchResutls(CustomerSearchForm form) {
		return customerRepository.findAll(Specifications
		  .where(spec.likeHeadCustomerNo(form.getCustomerNo()))
		  .and(spec.partLikeCorpName(form.getCorpName()))
		  .and(spec.deleted(false)));

	}

}
