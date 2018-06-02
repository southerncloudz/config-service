package com.jerald.udemy.microservice.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="netflix-zuul-api-gateway-server")
public interface LimitsServiceProxy {

	@GetMapping("limits-service/limits")
	public LimitsConfiguration getCurrencyLimit();
}
