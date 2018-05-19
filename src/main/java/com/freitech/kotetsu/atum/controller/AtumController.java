package com.freitech.kotetsu.atum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.freitech.kotetsu.atum.ModelItemList;
import com.freitech.kotetsu.atum.scaffolds.service.Scaffold;
import com.freitech.kotetsu.atum.service.ModelCreatorService;

@Controller
public class AtumController {

	@Autowired
	public ModelCreatorService modelCreatorService;

	@Autowired
	public Scaffold scaffold;

	@ModelAttribute(name = "modelList")
	public List<String> modelList() {
		return modelCreatorService.searchModel();
	}

	@GetMapping("/atum/index")
	public String index(Model model) {
		return "/atum/index";
	}
	

	@PostMapping("/atum/index")
	public String load() {
		return "/atum/index";
	}

	@GetMapping("/atum/input/{beanName:.+}")
	public String input(@PathVariable(name = "beanName", required = true) String beanName,
	                    Model model) {
		scaffold.create(beanName);
		model.addAttribute("modelItems", new ModelItemList(beanName));
		model.addAttribute("handled", true);

		return "/atum/input";
	}

}
