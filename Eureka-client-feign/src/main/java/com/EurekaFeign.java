package com;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="eureka-client",configuration=FeignConfig.class,fallback=HiHystrix.class)
public interface EurekaFeign {
	
	@RequestMapping("/sayHi/{name}")
	String sayHiFeign(@RequestParam(value="name") String name);

}
