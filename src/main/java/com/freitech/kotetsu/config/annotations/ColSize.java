package com.freitech.kotetsu.config.annotations;

import lombok.Getter;

@Getter
public enum ColSize {

	ONE("col-md-12 col-sm-12 col-xs-12", "col-md-3 col-sm-3 col-xs-12", "col-md-7 col-xs-12"),
	TWO("col-md-12 col-sm-12 col-xs-12", "col-md-6 col-sm-6 col-xs-12", "col-md-7 col-xs-12");


	private String size;
	private String label;
	private String input;

	ColSize(String size, String label, String input) {
		this.size = size;
		this.label = label;
		this.input = input;
	}
}
