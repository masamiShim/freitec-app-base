package com.freitech.kotetsu.service.api;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "CoinexchangeApi")
public class CoinExchangeApiImpl implements ExchangeTemplateFactoryService {
	@Value(value = "${api.exchange.coinExchange.version:}")
	private String version;

	@Value(value = "${api.exchange.coinExchange.url.base:}")
	private String baseUrl;

	@Value(value = "${api.exchange.coinExchange.url.market:}")
	private String marketUrl;

	@Value(value = "${api.exchange.coinExchange.url.marketSummaries:}")
	private String marketSumsUrl;

	@Value(value = "${api.exchange.coinExchange.url.marketSummary:}")
	private String marketSumUrl;

	@Value(value = "${api.exchange.coinExchange.url.orderBook:}")
	private String orderBookUrl;

	@Value(value = "${api.exchange.coinExchange.url.currencies:}")
	private String currenciesUrl;

	@Value(value = "${api.exchange.coinExchange.url.currency:}")
	private String currencyUrl;

	@Override
	public Object getBalance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMarkets() {
		URI uri = UriComponentsBuilder.fromUriString(getBaseUrl(marketUrl)).build().toUri();
		log.info(uri.toString());
		RequestEntity req = new RequestEntity<Map<String, String>>(HttpMethod.GET, uri);
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;

	}

	public String getMarketsSummaries() {
		URI uri = UriComponentsBuilder.fromUriString(getBaseUrl(marketUrl)).build().toUri();
		RequestEntity req = new RequestEntity<Map<String, String>>(HttpMethod.GET, uri);
		log.info(req.getHeaders().toString());
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;

	}

	public String getMarketsSummary(String marketId) {
		URI uri = UriComponentsBuilder.fromUriString(getBaseUrl(marketUrl)).queryParam("market_id", marketId).build()
				.toUri();
		RequestEntity req = new RequestEntity<Map<String, String>>(HttpMethod.GET, uri);
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;

	}

	public String getOrderBook(String marketId) {
		URI uri = UriComponentsBuilder.fromUriString(getBaseUrl(orderBookUrl)).queryParam("market_id", marketId).build()
				.toUri();
		RequestEntity req = new RequestEntity<Map<String, String>>(HttpMethod.GET, uri);
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;

	}

	public String getCurrencies() {
		URI uri = UriComponentsBuilder.fromUriString(getBaseUrl(orderBookUrl)).build().toUri();
		RequestEntity req = new RequestEntity<Map<String, String>>(HttpMethod.GET, uri);
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;
	}

	public String getCurrencyByCurrencyId(String currencyId) {
		URI uri = UriComponentsBuilder.fromUriString(getBaseUrl(currencyUrl)).queryParam("currency_id", currencyId)
				.build().toUri();
		RequestEntity req = new RequestEntity<Map<String, String>>(HttpMethod.GET, uri);
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;
	}

	public String getCurrencyByTickerCode(String tickerCode) {
		URI uri = UriComponentsBuilder.fromUriString(getBaseUrl(currencyUrl)).queryParam("ticker_code", tickerCode)
				.build().toUri();
		RequestEntity req = new RequestEntity<Map<String, String>>(HttpMethod.GET, uri);
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;
	}

	private String getBaseUrl(String url) {
		return baseUrl.concat(version).concat(url);
	}
}
