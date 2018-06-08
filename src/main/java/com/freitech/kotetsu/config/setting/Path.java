package com.freitech.kotetsu.config.setting;

import lombok.Getter;

@Getter
public enum Path {
	INFORMATION("information"),
	UNKNOWN("customer"),
	ITEM("master/item"),
	ESTIMATE("estimate"),
	CUSTOMER("master/customer");
		
	private String path;
	
	Path(String path){
		this.path = path;
	}
}
