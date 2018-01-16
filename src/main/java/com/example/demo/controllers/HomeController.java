package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.TopService;

@Controller
public class HomeController{

    @Autowired
    private TopService topService;
    
    /**
     * 初期画面表示処理
     * @return
     */
    @GetMapping(value = "top")
	public String index(Model model) {
        model.addAttribute("informations", topService.getDispInformationList());
		return "top";
	}
}
