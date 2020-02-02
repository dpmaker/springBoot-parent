package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignClientApplication {
	public static void main(String[] args){
		SpringApplication.run(FeignClientApplication.class, args);
	}
}
