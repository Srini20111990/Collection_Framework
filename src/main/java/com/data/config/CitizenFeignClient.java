package com.data.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Application-Register",url= "http://localhost:9494/app")
public interface CitizenFeignClient {
	
	    @GetMapping("/verify/{appId}")
	    ResponseEntity<Boolean> verifyCitizen(@PathVariable("appId") Integer appId);

}
