package com.example.demo.controllers.system.information;

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

import com.example.demo.controllers.SpringControllerBase;
import com.example.demo.forms.system.information.InformationForm;
import com.example.demo.forms.system.information.InformationSearchForm;
import com.example.demo.service.InformationService;

@Controller
@RequestMapping("/system/information")
public class InformationController extends SpringControllerBase {

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

  @GetMapping(path = "/index")
  public String index() {
    return "/system/information/index";
  }

  @PostMapping(path = "/index")
  public String list(Model model) {
    // TODO: 検索条件が必要なら
    model.addAttribute("result", informationService.getInformationList());
    return "/system/information/index";
  }

  @GetMapping(path = "/input")
  public String input() {
    return "/system/information/input";
  }

  @PostMapping(path = "/confirm")
  public String confirm(@Valid InformationForm form, BindingResult error,
      Model model) {
    model.addAttribute("form", form);
    // エラーあればさよなら
    if (error.hasErrors()) {
      return "/system/information/input";
    }
    return "/system/information/confirm";
  }

  @PostMapping(path = "/complete")
  public String complete(@Valid InformationForm form, BindingResult error,
      Model model) {
    model.addAttribute("form", form);
    // エラーあればさよなら
    if (error.hasErrors()) {
      return "/system/information/input";
    }
    if (informationService.regist(form).isPresent()) {
      model.addAttribute("success", "登録しました。");
      return "/system/information/input";
    } else {
      error.addError(new ObjectError("saveError", "登録に失敗しました。再度お試しください。"));
      model.addAttribute("form", form);
      return "/system/information/input";
    }
  }

}
