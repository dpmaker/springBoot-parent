package com;

import org.antlr.runtime.tree.RewriteRuleNodeStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RibbonService {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "hiError")
	String sayHi(String name) {
		return this.restTemplate.getForObject("http://eureka-client/sayHi/name=" + name, String.class);
	}

	String hiError(String name) {
		return "Sorry," + name + ",there is something wrong here by ribbon..";
	}

	public String postHi(String name) {
		String url = "http://eureka-client/sayHiPost";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap();
		map.add("name", name);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		//return this.restTemplate.postForEntity(url, request, String.class).getBody();
		return  this.restTemplate.exchange(url,HttpMethod.POST, request, String.class).getBody();

	}
}
