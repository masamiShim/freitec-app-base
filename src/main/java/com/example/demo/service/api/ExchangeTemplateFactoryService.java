package com.example.demo.service.api;

import org.springframework.web.client.RestTemplate;

public interface ExchangeTemplateFactoryService {

	RestTemplate template = new RestTemplate();
	
	/**
	 * 残高を取得
	 * 
	 * @return
	 */
	Object getBalance();

	/**
	 * 取引を取得
	 * 
	 * @return
	 */
	String getTrades();

	/**
	 * 注文履歴を取得
	 * 
	 * @return
	 */
	String getOrders();
	
	/**
	 * 
	 * @return
	 */
	String getMarkets();
}
