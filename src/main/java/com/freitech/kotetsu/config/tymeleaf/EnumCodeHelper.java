package com.freitech.kotetsu.config.tymeleaf;

import org.springframework.context.annotation.Configuration;

import com.freitech.kotetsu.codes.EnumStringBase;

@Configuration(value = "ech")
public class EnumCodeHelper {
	public String code(EnumStringBase target) {
		if (target != null) {
			return target.getCode();
		}
		return "";
	}
}
