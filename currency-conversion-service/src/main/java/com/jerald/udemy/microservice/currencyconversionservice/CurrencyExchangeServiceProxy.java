package com.jerald.udemy.microservice.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value="currency-exchange-service", url="localhost:8000")
//@FeignClient(value="currency-exchange-service")
@FeignClient(value="netflix-zuul-api-gateway-server")
@RibbonClient(value="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	//@GetMapping("currency-exchange/from/{from}/to/{to}")
	@GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
