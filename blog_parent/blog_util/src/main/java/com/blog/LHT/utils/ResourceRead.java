package com.blog.LHT.utils;

import java.util.ResourceBundle;

public class ResourceRead {

	private static ResourceBundle bundle = ResourceBundle.getBundle("imgPath");
	
	public static String getImgPath(){
		String imgPath = bundle.getString("imgPath");
		return imgPath;
	}
	
	public static String getImgRealPath(){
		String imgRealPath = bundle.getString("imgRealPath");
		return imgRealPath;
	}
	
}
