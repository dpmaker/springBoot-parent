package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Client2Controller {

	@GetMapping("/sayHi/{name}")
	@ResponseBody
	public String sayHi(@PathVariable String name){
		System.out.println("i am here...");
		return "hi, "+name+",this is client 2 responsing..";
	}
}
