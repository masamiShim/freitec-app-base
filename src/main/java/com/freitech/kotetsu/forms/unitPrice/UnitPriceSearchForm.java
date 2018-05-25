package com.freitech.kotetsu.forms.unitPrice;

import com.freitech.kotetsu.models.customer.Customer;
import com.freitech.kotetsu.models.item.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitPriceSearchForm {
	
	private Customer customer;

	private Item item;

}
