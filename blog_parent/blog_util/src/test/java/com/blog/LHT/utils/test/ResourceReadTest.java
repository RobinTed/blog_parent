package com.blog.LHT.utils.test;

import java.util.ResourceBundle;

public class ResourceReadTest {

	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("imgPath");
		String imgPath = bundle.getString("imgPath");
		String imgRealPath = bundle.getString("imgRealPath");
		System.out.println(imgPath);
		System.out.println(imgRealPath);
	}
	
}
