package com.freitech.kotetsu.service;

public interface MasterServiceBase<T> {

	/**
	 * 登録処理
	 * 
	 * @param form
	 * @return
	 */
	T regist(T form);

	/**
	 * 更新処理
	 * 
	 * @param form
	 * @return
	 */
	T update(Long id, T form);

	boolean delete(Long id);

}
