package com.freitech.kotetsu.controllers.commons;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public abstract class SpringControllerBase2<T> {
	protected final String DISP = "/disp";
	protected final String INDEX = "/index";
	protected final String INPUT = "/input";
	protected final String MODIFY = "/modify";
	protected final String DELETE = "/delete";
	protected final String CONFIRM = "/confirm";
	protected final String COMPLETE = "/complete";
	protected final String CREATE = "/create";
	protected final String BACK = "back";
	protected final String LIST = "/list";
	protected final String FORM_PARAM = "form";
	protected final String REG_PARAM = "reg";
	protected final String SUCCESS_MESSAGE = "success";

	protected Class<T> m;

	protected void createBindingErrorView(BindingResult bindingResult, Model model) {

		model.addAttribute("errors",
				bindingResult.getAllErrors().stream()
						.sorted(Comparator.comparing(ObjectError::getObjectName).reversed()
								.thenComparing(Comparator.comparing(ObjectError::getDefaultMessage).reversed()))
						.collect(Collectors.toList()));
/*
 * 		model.addAttribute("fieldErrors",
				bindingResult.getFieldErrors().stream()
						.sorted(Comparator.comparing(FieldError::getObjectName).reversed()
								.thenComparing(Comparator.comparing(FieldError::getDefaultMessage).reversed()))
						.collect(Collectors.toList()));
*/
	}

}
