package com.blog.LHT.service.testgeneric;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenericInject {

	//测试泛型注入
	@Test
	public void testGeneric(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-serviceTest.xml");
		TestService service = (TestService) context.getBean("testService");
		service.testService();
	}
	
}
