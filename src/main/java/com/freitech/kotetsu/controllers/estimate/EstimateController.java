package com.freitech.kotetsu.controllers.estimate;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freitech.kotetsu.forms.estimate.EstimateSearchForm;
import com.freitech.kotetsu.models.Estimate;
import com.freitech.kotetsu.service.EstimateService;

@Controller
@RequestMapping("estimate")
public class EstimateController {

  @Autowired
  EstimateService estimateService;
  
  @GetMapping(value = "index")
  public String index() {
    return "/estimate/index";

  }

  @PostMapping(value = "index")
  public String list(@Valid EstimateSearchForm form, BindingResult res,
      Model model) {
    // エラーあれば
    if (res.hasErrors()) {
      return "/users/index";
    }
    // 結果セット
    List<Estimate> result = estimateService.findBySerachCond(form).orElse(Collections.emptyList());
    model.addAttribute("result", result);
    return "/estimate/index";

  }

}
