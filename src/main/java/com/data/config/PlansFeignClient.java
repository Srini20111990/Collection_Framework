package com.data.config;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="PLANS-API",url= "http://localhost:9090/plans_api_01/plan")
public interface PlansFeignClient {
	
	@GetMapping("/plannames")
	public ResponseEntity<Map<Integer, String>> planNames();

}
