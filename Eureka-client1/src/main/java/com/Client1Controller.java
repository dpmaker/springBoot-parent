package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client1Controller {
	
	

	@RequestMapping("/sayHi/{name}")
	public String sayHi(@PathVariable String name){
		return "hi, "+name+",this is client 1 responsing..";
	}
	
	
	@RequestMapping(value="/sayHiPost",method=RequestMethod.POST)
	 @ResponseBody
	public String sayHiPost(String name){
		return "hi, "+name+",this is client 1 responsing sayHiPost..";

	}
}
