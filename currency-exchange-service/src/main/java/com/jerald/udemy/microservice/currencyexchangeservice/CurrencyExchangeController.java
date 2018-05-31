package com.jerald.udemy.microservice.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment env;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
		
		 ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		 
		 logger.info("info {}", exchangeValue);
		 exchangeValue.setPort(Integer.parseInt(env.getProperty("server.port")));;
		 return exchangeValue;
	}
}
