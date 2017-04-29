package com.blog.LHT.action.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONObject;

public class JsonTest {

	@Test
	public void testJson(){
		Map<String,String>myMap = new HashMap<>();
		myMap.put("userName", "Milla");
		myMap.put("password", "123");
		JSONObject jsonObject = JSONObject.fromObject(myMap);
		System.out.println(jsonObject);
	}
	
}
