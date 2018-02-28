package com.freitech.kotetsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.freitech.kotetsu.service.TopService;

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

    /**
     * 初期画面表示処理
     * @return
     */
    @GetMapping(value = "index")
	public String example(Model model) {
        //model.addAttribute("informations", topService.getDispInformationList());
		return "index";
	}
}
