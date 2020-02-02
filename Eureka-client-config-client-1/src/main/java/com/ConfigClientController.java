package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

	/*
	@Value("${foo}")
	String foo;
	*/
	
	@GetMapping("/config-client/{name}")
	String test(@PathVariable String name){
		return "hello "+name+",this is config client. the foo is ";
	}
	
	@GetMapping("/config-client-test")
	String test(){
		return "test";
	}
}
