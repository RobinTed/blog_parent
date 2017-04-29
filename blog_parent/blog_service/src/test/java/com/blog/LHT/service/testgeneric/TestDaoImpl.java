package com.blog.LHT.service.testgeneric;

import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl<T> implements TestDao<T>{

	public void dao(T t){
		System.out.println(t);
		System.out.println(t.getClass());
	}
	
}
