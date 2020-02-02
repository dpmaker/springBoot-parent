package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class FeignConfig {

	@Bean
	public Retryer feignRetry(){
		return new Retryer.Default(100, 1, 5);
	}
}
