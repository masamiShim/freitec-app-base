package com.freitech.kotetsu.service;

import java.util.List;

import com.freitech.kotetsu.forms.item.ItemSearchForm;
import com.freitech.kotetsu.models.item.Item;

public interface ItemService extends MasterServiceBase<Item>{

	/**
	 * お知らせの一覧を取得する。
	 * 
	 * @return
	 */
	List<Item> getItemList(ItemSearchForm form);

}
