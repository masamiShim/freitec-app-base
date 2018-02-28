package com.freitech.kotetsu.controllers;

import org.springframework.validation.BindingResult;

import com.freitech.kotetsu.forms.FormBase;

public interface ControllerBase {
	public <T extends FormBase> BindingResult inValid(T form, BindingResult result);
}
