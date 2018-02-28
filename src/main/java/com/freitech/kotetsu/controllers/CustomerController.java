package com.freitech.kotetsu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.freitech.kotetsu.db.repos.CustomerRepository;
import com.freitech.kotetsu.forms.customer.CustomerForm;


@Controller
@RequestMapping("customers")
public class CustomerController extends ControllerBaseImpl<CustomerForm> {

    @Autowired
	private CustomerRepository customerRepo;

	
	/**
	 * コンストラクタ
	 */
	public CustomerController(){
		//viewファイルまでのパスを指定
		PREF = "customers";
	}
		

    /**
     * 初期画面表示処理
     * @return
     */
    @GetMapping(value = INDEX)
	public ModelAndView index() {
		return super.modelAndViewFactory(INDEX);
	}
	
    /**
     * 検索結果表示
     * @return
     */
    @PostMapping(value = INDEX, params= LIST)
	public ModelAndView show() {
		ModelAndView mv = super.modelAndViewFactory(INDEX);
		return mv;
	}

    /**
     * 登録内容入力画面へ遷移する
     * @param form
     * @param bindingResult
     * @return
     */
    @GetMapping(value = INPUT)
	public ModelAndView input(CustomerForm form, BindingResult bindingResult) {
    	return super.input();
	}
    
    /**
     * 確認画面へ遷移する
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping(value = INPUT, params= CONFIRM)
	public ModelAndView confirm(@Valid CustomerForm form, BindingResult bindingResult) {
    	return super.confirm(form);
	}
    
    //TODO:ここら辺は設計思想による
    /**
     * 一覧画面へ遷移する
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping(value = INPUT, params= BACK)
	public ModelAndView input_back() {
    	return super.modelAndViewFactory(INDEX);
	}

    /**
     * 入力画面へ戻る
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping(value = CONFIRM, params= BACK)
	public ModelAndView confirm_back(CustomerForm form) {
    	return super.back(form, INPUT);
	}
    
    /**
     * 登録処理
     */
    @PostMapping(value = CONFIRM, params= COMPLETE)
	public ModelAndView create(@Valid CustomerForm form, BindingResult bindingResult) {
    	return super.create();
	}


    @Override
    BindingResult inValid(CustomerForm form, BindingResult bindingResult) {
      // TODO Auto-generated method stub
      return null;
    }



}
