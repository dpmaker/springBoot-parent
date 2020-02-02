package com;

import org.jboss.jandex.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import groovy.util.logging.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Main.class) //classes指定springboot的启动类
public class Service1Test {


	@Test
	public void testTest1() {
		new Service1().test1("tom");
	}

}
