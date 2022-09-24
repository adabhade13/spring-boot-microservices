package com.tatamotors.dealers.service;

import java.beans.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tatamotors.dealers.dto.StockDto;

@Service
public class StockManagerService {
	
	@Autowired
	private RestTemplate restTemplate;

	@T
	public List<StockDto> getStocksAvailable(String stockName){
		Map<String,Object> variableMap = null;
		List<StockDto> listDto = null;
		String uri = "http://INVENTORY-MGMT-SERVICE/stock/{stockName}/available";
		ResponseEntity<List<StockDto>> response = null;
		String url = null;
		variableMap = new HashMap<String, Object>();
		variableMap.put("stockName", stockName);
		
		url = UriComponentsBuilder.fromUriString(uri).uriVariables(variableMap).build().toUriString();
		
		response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<StockDto>>() {
		});
		
		if(response.getStatusCode() == HttpStatus.OK) {
			listDto = response.getBody();
		}	
		return listDto;
	}
	
}
