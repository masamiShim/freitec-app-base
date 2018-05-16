package com.freitech.kotetsu.forms.item;

import com.freitech.kotetsu.codes.ItemType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSearchForm {
	
	private String itemNo;

	private String name;

	private ItemType type;

}
