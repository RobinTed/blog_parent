package com.blog.LHT.action.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void testList(){
		List<Integer>list1 = new ArrayList<>();
		List<Integer>list2 = new ArrayList<>();
		for(int i = 0;i<4;i++){
			list1.add(i);
		}
		
		for(int i = list1.size()-1;i>=0;i--){
			list2.add(i);
		}
		for(Integer i:list2){
			System.out.println(i);
		}
	}
	
}
