package com.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapper.dao.TUser2Mapper;
import com.mapper.entity.TUser2;


@RestController
public class CacheTestController {

	
	
	@RequestMapping("/put/{name}")
	@CachePut(value="name",key="#name")
	String put(@PathVariable String name){
		
		return name;
	}
	
	@RequestMapping("/get/{name}")
	@Cacheable(value="name",key="#name")
	String get(@PathVariable String name){
		return name;
	}
}
