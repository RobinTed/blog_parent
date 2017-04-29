package com.blog.LHT.action.test;

import org.junit.Test;

import com.blog.LHT.utils.ResourceRead;

public class SubStringTest {

	@Test
	public void testSubString(){
		String path = "http://localhost:8080/img/user/head/14_head.jpg";
		int pos = path.indexOf("user/");
		System.out.println(pos);
		System.out.println(path.substring(pos));
		System.out.println(path.substring(path.indexOf("14_")));
	}
	
	@Test
	public void testSubTitle(){
		String title = "<a href=\"article_showArticle?articleId=12\">测试</a>";
		int begin = title.indexOf("\">");
		int end = title.indexOf("</");
		System.out.println(begin);
		System.out.println(end);
		System.out.println(title.substring(begin+2, end));
	}
	
	@Test
	public void testResource(){
		System.out.println(ResourceRead.getImgPath());
	}
	
}
