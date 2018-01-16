package com.example.demo.controllers;

import org.springframework.validation.BindingResult;

import com.example.demo.forms.FormBase;

public interface ControllerBase {
	public <T extends FormBase> BindingResult inValid(T form, BindingResult result);
}
