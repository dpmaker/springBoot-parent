package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client1Controller {
	
	@Autowired
	RibbonService ribbonService;

    @Autowired
    private LoadBalancerClient loadBalancer;
	
	@GetMapping("/sayHi/{name}")
	public String sayHi(@PathVariable String name){
		//String n=this.restTemplate.getForObject("http://eureka-client/sayHi?name="+name,String.class);
		//String n= this.restTemplate.getForObject("http://localhost:8110/sayHi/tom12",String.class);
		//return n;
		//return this.restTemplate.getForObject("http://10.25.27.112:8110/sayHi/sayHi?name="+name,String.class);
		return this.ribbonService.sayHi(name);
	}
	
	@GetMapping("/sayHiPost/{name}")
	public String sayHiPost(@PathVariable String name){
		//String n=this.restTemplate.getForObject("http://eureka-client/sayHi?name="+name,String.class);
		//String n= this.restTemplate.getForObject("http://localhost:8110/sayHi/tom12",String.class);
		//return n;
		//return this.restTemplate.getForObject("http://10.25.27.112:8110/sayHi/sayHi?name="+name,String.class);
		return this.ribbonService.postHi(name);
	}
	
	 @GetMapping("/testRibbon")
    public String testRibbon(){
        ServiceInstance instance=loadBalancer.choose("eureka-client");
        return instance.getHost()+":"+instance.getPort();
    }
	 
	 @GetMapping("/testRibbonLocal")
	    public String testRibbonLocal(){
	        ServiceInstance instance=loadBalancer.choose("stores");
	        return instance.getHost()+":"+instance.getPort();
	    }
	 
	 @GetMapping("/send/{name}")
	 public String send(@PathVariable String name){
		 ServiceInstance instance=loadBalancer.choose("eureka-client");
		 String  url="http://"+instance.getHost()+":"+instance.getPort()+"/sayHi/name="+name;
		 return new RestTemplate().getForObject(url, String.class);
		 
	 }
	 
	 @GetMapping("/sendLocal/{name}")
	 public String sendLocal(@PathVariable String name){
		 ServiceInstance instance=loadBalancer.choose("stores");
		 String  url="http://"+instance.getHost()+":"+instance.getPort()+"/sayHi/name="+name;
		 return new RestTemplate().getForObject(url, String.class);
		 
	 }
}
