package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
	public static void main(String[] args){
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
