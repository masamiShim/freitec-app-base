package com.example.demo.service.api;

import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Service(value = "CoincheckApi")
@Slf4j
public class CoincheckApiImpl implements ExchangeTemplateFactoryService {
	@Value(value = "${api.exchange.coincheck.key.apiKey}")
	private String apiKey;

	@Value(value = "${api.exchange.coincheck.key.secretKey}")
	private String secretKey;

	@Value(value = "${api.exchange.coincheck.url.base}")
	private String baseUrl;

	@Value(value = "${api.exchange.coincheck.url.transaction}")
	private String transUrl;

	@Value(value = "${api.exchange.coincheck.url.balance}")
	private String balanceUrl;

	@Value(value = "${api.exchange.coincheck.url.trades}")
	private String tradeUrl;

	private HttpHeaders getRequestHeader(String url) {
		HttpHeaders headers = new HttpHeaders();
		String nonce = getNonce();
		headers.add("ACCESS-KEY", apiKey);
		headers.add("ACCESS-NONCE", nonce);
		headers.add("ACCESS-SIGNATURE", createSignature(url, nonce));
		return headers;
	}

	/**
	 * 署名
	 * 
	 * @param url
	 * @param nonce
	 * @return
	 */
	public String createSignature(String url, String nonce) {
		return HMAC_SHA256Encode((nonce).concat(url));
	}

	@Override
	public Object getBalance() {
		String uri = baseUrl.concat(balanceUrl);
		
		RequestEntity req = new RequestEntity<Map<String, String>>(getRequestHeader(uri),
				HttpMethod.GET, URI.create(uri));
		log.info(req.getHeaders().toString());
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;
	}

	@Override
	public String getTrades() {
		URI uri = UriComponentsBuilder.fromUriString(baseUrl.concat(transUrl)).build().toUri();

		RequestEntity req = new RequestEntity<Map<String, String>>(getRequestHeader(uri.toString()),
				HttpMethod.GET, uri);
		log.info(req.getHeaders().toString());
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;
	}

	@Override
	public String getOrders() {
		URI uri = UriComponentsBuilder.fromUriString(baseUrl.concat(tradeUrl)).queryParam("pair", "btc_jpy").build().toUri();

		RequestEntity req = new RequestEntity<Map<String, String>>(getRequestHeader(uri.toString()),
				HttpMethod.GET, uri);
		log.info(req.getHeaders().toString());
		String response = template.exchange(req, String.class).getBody();
		log.info(response);
		return response;
	}

	/**
	 * Nonceを作成
	 * 
	 * @return
	 */
	private String getNonce() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * SHA256エンコードを実施する。
	 * 
	 * @param message
	 * @return
	 */
	private String HMAC_SHA256Encode(String message) {

		SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), HmacAlgorithms.HMAC_SHA_256.toString());

		Mac mac = null;
		try {
			mac = Mac.getInstance(HmacAlgorithms.HMAC_SHA_256.toString());
			mac.init(keySpec);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			log.error("Can't Encode HMAC_SHA256");
		}
		return Optional.ofNullable(mac).map(m -> Hex.encodeHexString(m.doFinal(message.getBytes())))
				.orElse(Optional.empty().toString());
	}

	@Override
	public String getMarkets() {
		// TODO Auto-generated method stub
		return null;
	}

}
