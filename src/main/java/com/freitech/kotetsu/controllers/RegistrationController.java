package com.freitech.kotetsu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.freitech.kotetsu.forms.RegistrationForm;
import com.freitech.kotetsu.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("registration")
public class RegistrationController extends ControllerBaseImpl<RegistrationForm> {

  @Autowired
  RegistrationService registrationService;

  public RegistrationForm setBind() {
    return new RegistrationForm();
  }

  /**
   * コンストラクタ
   */
  public RegistrationController() {
    // viewファイルまでのパスを指定
    PREF = "registration";
  }

  @GetMapping(value = INPUT)
  public ModelAndView input() {
    return super.modelAndViewFactory(INPUT);
  }

  /**
   * 確認画面へ遷移する
   * 
   * @param form
   * @param bindingResult
   * @return
   */
  @PostMapping(value = CONFIRM)
  public ModelAndView confirm(@Valid @ModelAttribute RegistrationForm form, BindingResult result) {

    result.getAllErrors().stream().forEach(er -> log.error(er.getDefaultMessage()));
    log.warn(String.valueOf(result.hasErrors()));
    if (result.hasErrors() || inValid(form, result).hasErrors()) {
      return super.createBindingErrorView(form, result, INPUT);
    }
    // ModelAndView mv = new ModelAndView();
    return super.confirm(form);
  }

  /**
   * 登録処理
   */
  @PostMapping(value = COMPLETE)
  public ModelAndView create(@Valid @ModelAttribute RegistrationForm form, BindingResult bindingResult) {
    if (bindingResult.hasErrors() || inValid(form, bindingResult).hasErrors()) {
      return super.createBindingErrorView(form, bindingResult, "/registration/input");
    }

    registrationService.saveUser(form);
    // ModelAndView mv = super.create();
    ModelAndView mv = new ModelAndView();
    mv.addObject("success", "登録OK");
    mv.setViewName("redirect:/top");
    return mv;
  }

  protected BindingResult inValid(RegistrationForm form, BindingResult bindingResult) {
    // 入力したパスワードと確認用のパスワードが異なる場合はエラー
    if (!form.getPassword().equals(form.getConfirmation())) {
      bindingResult.addError(new ObjectError("パスワード", "入力したパスワードがパスワード（確認用）と一致しません。"));
    }
    // FIXME: ajaxでよいかも
    if (registrationService.alreadyUsedLoginId(form.getLoginId())) {
      bindingResult.addError(new ObjectError("ログインID", "入力したログインIDはすでに利用されています。"));
    }
    return bindingResult;
  }
}
