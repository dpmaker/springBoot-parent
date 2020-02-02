package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client1Controller {
	
	@Autowired
	private EurekaFeign eurekaFeign;

	@RequestMapping("/sayHiFeign/{name}")
	public String sayHi(@PathVariable String name){
		return  this.eurekaFeign.sayHiFeign(name);
	}
	
	
}
