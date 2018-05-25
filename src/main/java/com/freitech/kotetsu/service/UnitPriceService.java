package com.freitech.kotetsu.service;

import java.util.List;

import com.freitech.kotetsu.forms.unitPrice.UnitPriceSearchForm;
import com.freitech.kotetsu.models.UnitPrice;

public interface UnitPriceService extends MasterServiceBase<UnitPrice>{

	/**
	 * 単価の一覧を取得する。
	 * 
	 * @return
	 */
	List<UnitPrice> search(UnitPriceSearchForm form);

}
