package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@SpringBootApplication
@EnableCaching
public class CacheTestApplication {
	public static void main(String[] args){
		SpringApplication.run(CacheTestApplication.class, args);
	}
}
