package com.freitech.kotetsu.models.customer;

import java.math.RoundingMode;

import com.freitech.kotetsu.codes.EnumStringBase;

public enum Rounding implements EnumStringBase {

	// @formatter:off
	ROUND_UP(RoundingMode.HALF_UP,"四捨五入")
	,FLOOR(RoundingMode.FLOOR,"切り上げ")
	,CEILING(RoundingMode.CEILING,"切り捨て")
	,HALF_EVEN(RoundingMode.HALF_EVEN,"近似");
	//@formatter:on

	private RoundingMode code;
	private String name;

	@Override
	public String getCode() {
		return code.toString();
	}

	public String getName() {
		return name;
	}

	Rounding(java.math.RoundingMode mode, String name) {
		this.code = mode;
		this.name = name;
	}

}
