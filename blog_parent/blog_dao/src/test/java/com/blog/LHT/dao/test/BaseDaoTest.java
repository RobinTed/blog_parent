package com.blog.LHT.dao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.LHT.dao.BaseDao;

public class BaseDaoTest {

	//TODO  由于基本的dao类是泛型，不好注入，后面用service测试
	@Test
	public void testAdd(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
		BaseDao baseDao = (BaseDao) context.getBean("baseDaoImpl");
		System.out.println(baseDao.getClass());
	}
	
}
