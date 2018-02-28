package com.freitech.kotetsu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class CustomerController2 extends SpringControllerBase{
	
	@ModelAttribute
	/**
	 * 入力画面
	 * @return
	 */
	@GetMapping("/input")
	public String input(){
		return INPUT;
	}
	
	@PostMapping("/confirm")
	public String confirm(){
		return CONFIRM;
	}

	@PostMapping("/complete")
	public String complete(){
		return COMPLETE;
	}

	@GetMapping("/back")
	public String back(){
		return "redirect:input";
	}
}
