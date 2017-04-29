package com.blog.LHT.action;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
//import org.junit.Test;

import com.blog.LHT.utils.ResourceRead;
import com.opensymphony.xwork2.ActionSupport;

public class ApplicationValue extends ActionSupport{

	private static final long serialVersionUID = 1L;

	static{
		String imgPath = ResourceRead.getImgPath();
		String imgRealPath = ResourceRead.getImgRealPath();
		ServletContext application = ServletActionContext.getServletContext();
				
		application.setAttribute("imgP", imgPath);
		application.setAttribute("imgRP", imgRealPath);
	}
	
//	@Test
//	public void testPath(){
//		String imgPath = ResourceRead.getImgPath();
//		String imgRealPath = ResourceRead.getImgRealPath();
//		System.out.println(imgPath);
//		System.out.println(imgRealPath);
//	}
	
}
