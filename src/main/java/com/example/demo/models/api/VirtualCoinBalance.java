package com.example.demo.models.api;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class VirtualCoinBalance extends ApiBase{
	private BigDecimal jpy;
	private BigDecimal bch;
	private BigDecimal btc;
	private BigDecimal dao;
	private BigDecimal dash;
	private BigDecimal eth;
	private BigDecimal fct;
	private BigDecimal lsk;
	private BigDecimal ltc;
	private BigDecimal rep;
	private BigDecimal xem;
	private BigDecimal xmr;
	private BigDecimal xrp;
	private BigDecimal zec;
	
}
