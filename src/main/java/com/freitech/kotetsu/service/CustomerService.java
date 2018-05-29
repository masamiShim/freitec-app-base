package com.freitech.kotetsu.service;

import java.util.List;

import com.freitech.kotetsu.forms.customer.CustomerSearchForm;
import com.freitech.kotetsu.models.customer.Customer;

public interface CustomerService extends MasterServiceBase<Customer>{

	/**
	 * 顧客の一覧を取得する。
	 * 
	 * @return
	 */
	List<Customer> getSearchResutls(CustomerSearchForm form);

}
