package com.tatamotors.dealers.cb.services;

import java.beans.Transient;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tatamotors.dealers.cb.dto.StockDto;

@Service
public class StockService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CircuitBreakerFactory breakerFactory;

	
	public List<StockDto> getStocks(String stockName) {
		CircuitBreaker cBreaker = null;
		cBreaker = breakerFactory.create("ims");
		return cBreaker.run(() -> {
			String baseUri = null;
			String subBaseUri = null;
			Map<String, Object> variableMap = null;
			variableMap = new HashMap<String, Object>();

			variableMap.put("stockName", stockName);
			baseUri = "http://INVENTORY-MGMT-SERVICE/stock";
			subBaseUri = "/{stockName}/available";
			String uriString = UriComponentsBuilder.fromUriString(baseUri + subBaseUri).uriVariables(variableMap)
					.build().toUriString();
			ResponseEntity<List<StockDto>> exchange = restTemplate.exchange(uriString, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<StockDto>>() {
					});

			if (exchange.getStatusCode() == HttpStatus.OK) {
				return exchange.getBody();
			}
			return null;
		}, (t) -> {
			return Arrays.asList(new StockDto[] {});
		});
	}
}
