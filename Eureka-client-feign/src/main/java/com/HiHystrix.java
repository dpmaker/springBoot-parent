package com;

import org.springframework.stereotype.Component;

@Component
public class HiHystrix implements EurekaFeign{

	@Override
	public String sayHiFeign(String name) {
		return "Sorry,"+name+",there is something wrong here by feign..";
	}

}
