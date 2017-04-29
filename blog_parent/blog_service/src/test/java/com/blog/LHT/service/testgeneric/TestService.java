package com.blog.LHT.service.testgeneric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestService {

	@Autowired
	private TestDao< Integer>testDao;
	
	public void testService(){
		testDao.dao(1);
	}
}
