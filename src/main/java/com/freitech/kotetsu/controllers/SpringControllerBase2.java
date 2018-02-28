package com.freitech.kotetsu.controllers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.freitech.kotetsu.config.annotations.ViewAttribute;
import com.freitech.kotetsu.config.tymeleaf.ViewModel;

public abstract class SpringControllerBase2<T> {
	protected final String DISP = "disp";
	protected final String INDEX = "index";
	protected final String INPUT = "input";
	protected final String CONFIRM = "confirm";
	protected final String COMPLETE = "complete";
	protected final String CREATE = "create";
	protected final String BACK = "back";
	protected final String LIST = "list";
	protected final String FORM_PARAM = "form";
	protected final String REG_PARAM = "reg";
	protected final String SUCCESS_MESSAGE = "success";

	protected Class<T> m;

	private List<ViewModel> view;

	@ModelAttribute(value = "view")
	public List<ViewModel> getView() {
		return init();
	}

	public List<ViewModel> init() {
		// TODO: これどうにかしたい
		load();
		view = new ArrayList<>();

		Field[] fields = m.getDeclaredFields();
		// TODO: Annotationのなんかいるかなー
		// TODO: あとはこいつを別出ししたい
		for (Field f : fields) {
			Annotation[] anons = f.getAnnotations();
			ViewModel v = new ViewModel();
			for (Annotation a : anons) {
				if (a instanceof Size) {
					v.setMax(((Size) a).max());
					v.setMax(((Size) a).min());
				} else if (a instanceof NotNull || a instanceof NotBlank) {
					v.setRequired(true);
				} else if (a instanceof Max) {
					v.setMax((int) ((Max) a).value());
				} else if (a instanceof Min) {
					v.setMin((int) ((Min) a).value());
				} else if (a instanceof Length) {
					v.setMin(((Length) a).min());
					v.setMax(((Length) a).max());
				} else if (a instanceof ViewAttribute) {
					v.setLabel(((ViewAttribute) a).name());
					v.setOrdinal(((ViewAttribute) a).ordinal());
					v.setPlaceholder(((ViewAttribute) a).placeholder());
					v.setCssClass(((ViewAttribute) a).colSize());
					v.setVisible(((ViewAttribute) a).visible());
					v.setReadonly(((ViewAttribute) a).readonly());
					v.setDisabled(((ViewAttribute) a).disabled());
				} else if (a instanceof Range) {
					v.setMin((int) ((Range) a).min());
					v.setMax((int) ((Range) a).max());
				}
			}
			v.setName(f.getName());
			v.setType(f.getType().getTypeName());
			view.add(v);
		}
		return view;
	}

	protected void createBindingErrorView(BindingResult bindingResult, Model model) {

		model.addAttribute("errors",
				bindingResult.getAllErrors().stream()
						.sorted(Comparator.comparing(ObjectError::getObjectName).reversed()
								.thenComparing(Comparator.comparing(ObjectError::getDefaultMessage).reversed()))
						.collect(Collectors.toList()));
		model.addAttribute("fieldErrors",
				bindingResult.getFieldErrors().stream()
						.sorted(Comparator.comparing(FieldError::getObjectName).reversed()
								.thenComparing(Comparator.comparing(FieldError::getDefaultMessage).reversed()))
						.collect(Collectors.toList()));
	}

	protected abstract void load();
}
