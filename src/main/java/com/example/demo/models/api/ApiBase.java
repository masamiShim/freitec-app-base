package com.example.demo.models.api;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class ApiBase {
	private String success;
	
	private boolean isSuccess() {
		return StringUtils.equals(success, "true");
	}
}
