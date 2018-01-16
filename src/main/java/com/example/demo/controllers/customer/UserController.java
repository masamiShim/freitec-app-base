package com.example.demo.controllers.customer;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.forms.customer.UserSearchForm;
import com.example.demo.models.UserEntity;
import com.example.demo.service.UserService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("users")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  @ModelAttribute(value = "cond")
  public UserSearchForm searchCond() {
    return new UserSearchForm();
  }

  public List<UserEntity> result;

  @GetMapping("index")
  public String index() {
    return "/users/index";
  }

  @PostMapping("index")
  public String list(@Valid UserSearchForm form,
      BindingResult bindingResult, Model model) {
    // エラーあれば
    if (bindingResult.hasErrors()) {
      return "/users/index";
    }
    // 結果セット
    result = userService.findBySearchSpec(form).orElse(Collections.emptyList());
    model.addAttribute("result", result);
    model.addAttribute("cond", form);
    return "/users/index";
  }

}
