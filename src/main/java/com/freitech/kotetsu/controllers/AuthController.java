package com.freitech.kotetsu.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.freitech.kotetsu.forms.LoginForm;

@Controller
public class AuthController extends ControllerBaseImpl<LoginForm> {

  @RequestMapping("/")
  public String index() {
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String login(
      @RequestParam(name = "timeout", required = false) Optional<String> timeout,
      Model model) {
    if (timeout.isPresent()) {
      model.addAttribute("timeout", true);
    }
    return "login";
  }

  @PostMapping("/login")
  public ModelAndView doLogin(@Validated LoginForm form,
      BindingResult bindingResult) {
    if (inValid(form, bindingResult).hasErrors()) {
      return createBindingErrorView(form, bindingResult, "login");
    }
    ModelAndView mv = new ModelAndView();
    ;
    mv.setViewName("login");
    return mv.addObject("form", form);

    // return "redirect:/top";
  }

  @GetMapping("/login-error")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login";

  }

  @GetMapping("/sign-in/input")
  public String signIn() {
    // TODO:登録内容のチェック
    return "singIn";
  }

  @PostMapping("/sign-in/confirm")
  public String confirm() {
    // TODO:登録内容のチェックと登録
    return "confirm";
  }

  @PostMapping("/sign-in/complete")
  public String complete() {
    return "redirect:/top";
  }

  @Override
  BindingResult inValid(LoginForm form, BindingResult bindingResult) {
    // TODO Auto-generated method stub
    return null;
  }

}
