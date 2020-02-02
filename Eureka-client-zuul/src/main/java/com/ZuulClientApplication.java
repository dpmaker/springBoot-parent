package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulClientApplication {
	public static void main(String[] args){
		SpringApplication.run(ZuulClientApplication.class, args);
	}
}
