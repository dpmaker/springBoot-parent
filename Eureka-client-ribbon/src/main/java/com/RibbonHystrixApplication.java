package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class RibbonHystrixApplication {

	public static void main(String[] args){
		SpringApplication.run(RibbonHystrixApplication.class, args);
	}
}
