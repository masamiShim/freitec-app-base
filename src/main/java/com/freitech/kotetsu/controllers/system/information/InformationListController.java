package com.freitech.kotetsu.controllers.system.information;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freitech.kotetsu.controllers.SpringControllerBase2;
import com.freitech.kotetsu.forms.system.information.InformationSearchForm;
import com.freitech.kotetsu.service.InformationService;

@Controller
@RequestMapping("/information")
public class InformationListController extends SpringControllerBase2<InformationSearchForm> {

	@Autowired
	private InformationService informationService;

	@ModelAttribute(value = "cond")
	public InformationSearchForm searchCond() {
		return new InformationSearchForm();
	}

	@GetMapping(path = "/index")
	public String index() {
		return "/information/index";
	}

	@PostMapping(path = "/index")
	public String list(@Valid @ModelAttribute("cond") InformationSearchForm cond, BindingResult error, Model model) {
		// エラーあればさよなら
		if (error.hasErrors()) {
			createBindingErrorView(error, model);
			return "/information/index";
		}

		model.addAttribute("result", informationService.getInformationList());
		// TODO: 検索条件が必要なら
		return "/information/index";
	}

	protected void load() {
		m = InformationSearchForm.class;
	}
}
