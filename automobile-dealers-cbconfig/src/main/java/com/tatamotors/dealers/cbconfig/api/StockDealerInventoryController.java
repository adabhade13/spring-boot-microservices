package com.tatamotors.dealers.cbconfig.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tatamotors.dealers.cbconfig.dto.StockDto;

@RestController
@RequestMapping("/dealer")
public class StockDealerInventoryController {

	private static final String INVENTORY_MGMT_SERVICE ="INVENTORY-MGMT-SERVICE";
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CircuitBreakerFactory<Resilience4JCircuitBreakerConfiguration, Resilience4JConfigBuilder> circuitBreakerFactory;
	
	@GetMapping(value="/stock/{stockName}" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getStocksDealers(@PathVariable String stockName){
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("slow");
		return circuitBreaker.run(()->{
			String uri = null;
			Map<String, Object> variableMap = null;
			String resourceUrl = null;
			ResponseEntity<List<StockDto>> listDto=null;
			
		  variableMap = new HashMap<>();
		  variableMap.put("stockName", stockName);
		  uri = "http://"+INVENTORY_MGMT_SERVICE+"/stock/{stockName}/available";
		  resourceUrl = UriComponentsBuilder.fromUriString(uri).uriVariables(variableMap).build().toUriString();
		  listDto= restTemplate.exchange(resourceUrl,HttpMethod.GET, null, new ParameterizedTypeReference<List<StockDto>>() {
		});
	      if(listDto.getStatusCode() == HttpStatus.OK) {
	    	  return listDto;
	      }
	      return null;
		},(t)->{
			return ResponseEntity.internalServerError().body("Internal Server Error");
		});
		
	}
}
