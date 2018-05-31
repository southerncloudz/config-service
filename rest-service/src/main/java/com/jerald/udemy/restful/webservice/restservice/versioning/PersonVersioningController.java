package com.jerald.udemy.restful.webservice.restservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {


	@GetMapping("v1/person")
	public PersonV1 personV1(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "v2/person")
	public PersonV2 personV2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(value = "/person/param", params="version=1")
	public PersonV1 personParam1(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/param", params="version=2")
	public PersonV2 personParam2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(value = "/person/header", headers="X-API-VERSION=1")
	public PersonV1 personHeader1(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/header", headers="X-API-VERSION=2")
	public PersonV2 personHeader2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(value = "/person/produces", produces = "application/vn.company.app-v1+json")
	public PersonV1 personProduces1(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/produces", produces = "application/vn.company.app-v2+json")
	public PersonV2 personProduces2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
