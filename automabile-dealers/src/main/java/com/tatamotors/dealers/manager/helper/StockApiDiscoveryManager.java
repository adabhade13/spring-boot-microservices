package com.tatamotors.dealers.manager.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class StockApiDiscoveryManager {
	
	private static final String INVENTORY_MGMT ="INVENTORY-MGMT-SERVICE";
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public List<ServiceInstance> getStocks(){
		return discoveryClient.getInstances(INVENTORY_MGMT);
	}

}
