package com.freitech.kotetsu.controllers.system.information;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freitech.kotetsu.controllers.SpringControllerBase2;
import com.freitech.kotetsu.forms.system.information.InformationForm;
import com.freitech.kotetsu.forms.system.information.InformationSearchForm;
import com.freitech.kotetsu.models.Information;
import com.freitech.kotetsu.service.InformationService;

@Controller
@RequestMapping("/information")
public class InformationController extends SpringControllerBase2<Information> {

	@Autowired
	private InformationService informationService;

	@ModelAttribute(value = "cond")
	public InformationSearchForm searchCond() {
		return new InformationSearchForm();
	}


	@ModelAttribute(name = "form")
	public InformationForm getForm() {
		return new InformationForm();
	}

	@ModelAttribute(name = "inputForm")
	public InformationSearchForm form() {
		return new InformationSearchForm();
	}
	
	@GetMapping(path = "/input")
	public String input() {
		return "/system/information/input";
	}

	@PostMapping(path = "/confirm")
	public String confirm(@Valid InformationForm form, BindingResult error, Model model) {
		model.addAttribute("form", form);
		// エラーあればさよなら
		if (error.hasErrors()) {
			return "/system/information/input";
		}
		return "/system/information/confirm";
	}

	@PostMapping(path = "/complete")
	public String complete(@Valid InformationForm form, BindingResult result, Model model) {
		model.addAttribute("form", form);
		// エラーあればさよなら
		if (result.hasErrors()) {
			return "/system/information/input";
		}
		if (informationService.regist(form).isPresent()) {
			model.addAttribute("success", "登録しました。");
			return "/system/information/input";
		} else {
			result.addError(new ObjectError("saveError", "登録に失敗しました。再度お試しください。"));
			model.addAttribute("form", form);
			return "/system/information/input";
		}
	}

	protected void load() {
		m = Information.class;
	}
}
