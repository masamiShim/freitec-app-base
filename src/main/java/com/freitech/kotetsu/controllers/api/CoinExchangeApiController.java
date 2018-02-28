package com.freitech.kotetsu.controllers.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freitech.kotetsu.service.api.ExchangeTemplateFactoryService;

@Controller
@RequestMapping(path="/service/coinexchange")
public class CoinExchangeApiController {

	@Autowired
	@Qualifier(value="CoinexchangeApi")
	private ExchangeTemplateFactoryService coinexchangeApi;
	
	@GetMapping(path="/orders")
	public String orders(HttpServletRequest request) {
		coinexchangeApi.getOrders();
		return "/service/trade/index";
	}

	@GetMapping(path="/balances")
	public String balance(HttpServletRequest request) {
		coinexchangeApi.getBalance();
		return "/service/trade/index";
	}

	@GetMapping(path="/trade")
	public String trade(HttpServletRequest request) {
		coinexchangeApi.getTrades();
		return "/service/trade/index";
	}

	@GetMapping(path="/market")
	public String market(HttpServletRequest request) {
		coinexchangeApi.getMarkets();
		return "/service/trade/index";
	}
}
