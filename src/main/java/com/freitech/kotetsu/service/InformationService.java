package com.freitech.kotetsu.service;

import java.time.LocalDate;
import java.util.List;

import com.freitech.kotetsu.forms.system.information.InformationSearchForm;
import com.freitech.kotetsu.models.information.Information;

public interface InformationService extends MasterServiceBase<Information> {

	/**
	 * お知らせの一覧を取得する。
	 * 
	 * @return
	 */
	List<Information> getInformationList(InformationSearchForm form);

	/**
	 * 指定された日付に表示されている情報の一覧を取得する。
	 * 
	 * @param date
	 * @return
	 */
	List<Information> findByDateFromToAndDeletedNull(LocalDate date);



}
