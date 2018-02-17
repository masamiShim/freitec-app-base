package com.example.demo.controllers.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.api.ExchangeTemplateFactoryService;

@Controller
@RequestMapping(path="/service/trade")
public class CoincheckApiController {

	@Autowired
	@Qualifier(value="CoincheckApi")
	private ExchangeTemplateFactoryService coincheckApi;
	
	@GetMapping(path="/orders")
	public String orders(HttpServletRequest request) {
		coincheckApi.getOrders();
		return "/service/trade/index";
	}

	@GetMapping(path="/balances")
	public String balance(HttpServletRequest request) {
		coincheckApi.getBalance();
		return "/service/trade/index";
	}

	@GetMapping(path="/trade")
	public String trade(HttpServletRequest request) {
		coincheckApi.getTrades();
		return "/service/trade/index";
	}

}
