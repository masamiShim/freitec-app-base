package com.freitech.kotetsu.controllers.estimate;

import java.util.Collections;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freitech.kotetsu.config.setting.Path;
import com.freitech.kotetsu.controllers.commons.ListControllerBase;
import com.freitech.kotetsu.forms.estimate.EstimateSearchForm;
import com.freitech.kotetsu.service.EstimateService;

@Controller
@RequestMapping("/estimate")
public class EstimateListController extends ListControllerBase<EstimateSearchForm> {

	@Autowired
	EstimateService estimateService;

	@Autowired
	HttpSession session;

	@GetMapping(value = "index")
	public String index(Model model) {
		EstimateSearchForm cond = super.searchCond();
		model.addAttribute("result", estimateService.findBySearchCond(cond).orElse(Collections
		  .emptyList()));
		model.addAttribute("cond", cond);
		return Path.ESTIMATE.getPath().concat(INDEX);

	}

	@PostMapping(value = "index")
	public String list(@Valid EstimateSearchForm cond, BindingResult res,
	                   Model model) {
		// エラーあれば
		if (res.hasErrors()) {
			return Path.ESTIMATE.getPath().concat(INDEX);
		}
		// 結果セット
		model.addAttribute("result", estimateService.findBySearchCond(cond).orElse(Collections
		  .emptyList()));
		model.addAttribute("cond", cond);
		session.setAttribute("cond", cond);
		return Path.ESTIMATE.getPath().concat(INDEX);

	}

	@Override
	protected EstimateSearchForm load() {
		return new EstimateSearchForm();
	}

}
