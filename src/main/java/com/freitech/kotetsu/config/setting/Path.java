package com.freitech.kotetsu.config.setting;

import lombok.Getter;

@Getter
public enum Path {
	INFORMATION("information"),
	CUSTOMER("customer"),
	ITEM("master/item"),
	UNITPRICE("master/unitprice"),
	ESTIMATE("estimate");
		
	private String path;
	
	Path(String path){
		this.path = path;
	}
}
