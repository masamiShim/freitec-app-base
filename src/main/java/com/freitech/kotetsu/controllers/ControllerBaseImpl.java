package com.freitech.kotetsu.controllers;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.freitech.kotetsu.forms.FormBase;

public abstract class ControllerBaseImpl<T extends FormBase> {

  @Autowired
  private T iniForm;

  /** パス */
  protected String PREF = "";

  public String getPref() {
    return PREF;
  }

  @ModelAttribute(name = "form")
  public T setupBind() {
    return this.iniForm;
  }

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

  protected final boolean REDIRECT_ON = true;

  /**
   * 入力画面テンプレートメソッド 入力画面へ遷移する。
   * 
   * @return
   */
  public ModelAndView input(boolean... redirect) {
    ModelAndView mv = this.modelAndViewFactory(INPUT, redirect);
    return mv;
  }

  /**
   * 確認画面テンプレートメソッド 確認画面へ遷移する。
   * 
   * @return
   */
  public ModelAndView confirm(T form, boolean... redirect) {
    return this.modelAndViewFactory(CONFIRM, redirect).addObject(FORM_PARAM, form);
  }

  /**
   * 入力内容を保持して、指定画面へ戻る
   * 
   * @return
   */
  public ModelAndView back(T form, String tempName, boolean... isRedirect) {
    ModelAndView mv = this.modelAndViewFactory(tempName, isRedirect);
    mv.addObject("form", form);
    return mv;
  }

  /**
   * 登録処理テンプレートメソッド 完了画面へ遷移する。
   * 
   * @return
   */
  public ModelAndView create() {
    // if (bindingResult.hasErrors()) {
    // return createBindingErrorView(form, bindingResult, INPUT);
    // }
    // 登録後検索画面へ遷移
    // ModelAndView mv = this.ModelAndViewFactory(INDEX);

    return new ModelAndView().addObject(SUCCESS_MESSAGE, "登録処理が完了しました。");
  }

  /**
   * 表示するテンプレートを指定したModelAndViewを生成する。
   * 
   * @param tempName
   *          テンプレート名
   * @return
   */
  protected ModelAndView modelAndViewFactory(String tempName, boolean... isRedirect) {
    ModelAndView mv = new ModelAndView();
    if (isRedirect.length > 0) {
      mv.setViewName("redirect:".concat("/").concat(PREF.concat(toHeadUpper(tempName))));
    } else {
      mv.setViewName(getPref().concat("/").concat(PREF.concat(toHeadUpper(tempName))));
    }
//    return mv.addObject(FORM_PARAM, iniForm);
    return mv;
  }

  /**
   * エラー発生時にエラー内容表示用の画面データ作成
   * 
   * @param form
   *          フォームの値
   * @param bindingResult
   *          値バインド結果
   * @param retView
   *          戻り先の画面名
   * @return
   */
  protected ModelAndView createBindingErrorView(T form, BindingResult bindingResult, String retView) {
    ModelAndView mv = this.modelAndViewFactory(retView);
    mv.addObject("errors",
        bindingResult.getAllErrors().stream()
            .sorted(Comparator.comparing(ObjectError::getObjectName).reversed()
                .thenComparing(Comparator.comparing(ObjectError::getDefaultMessage).reversed()))
            .collect(Collectors.toList()));
    mv.addObject("fieldErrors",
        bindingResult.getFieldErrors().stream()
            .sorted(Comparator.comparing(FieldError::getObjectName).reversed()
                .thenComparing(Comparator.comparing(FieldError::getDefaultMessage).reversed()))
            .collect(Collectors.toList()));
    mv.addObject(FORM_PARAM, form);
    return mv;
  }

  abstract BindingResult inValid(T form, BindingResult bindingResult);
  
  private String toHeadUpper(String str){
   return str.substring(0, 1).toUpperCase().concat(str.substring(1));
  }
}
