package com.freitech.kotetsu.models.customer;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.freitech.kotetsu.codes.EnumStringBase;

public enum IncludeTax implements EnumStringBase {

// @formatter:off
	EXCLUED("0", "税別")
	, INCLUDE("1", "内税");	
//@formatter:on

	public String code = "";
	public String name = "";

	private IncludeTax(final String code, final String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public static IncludeTax fromString(String val) {
		if (StringUtils.isEmpty(val)) {
			return null;
		}
		return Arrays.stream(IncludeTax.values())
		  .filter(c -> c.getCode().equals(val)).findFirst()
		  .get();
	}
}
