package com.blog.LHT.action.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.LHT.utils.VerifyCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VerifyCodeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String verifyCode() throws IOException{
		/**
		 * 设置响应头信息
		 */
		ServletActionContext.getResponse().setContentType("image/jpeg");
		ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
		ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires", 0);
		HttpSession session = ServletActionContext.getRequest().getSession();
		//清除verifyCode
		session.removeAttribute("verifyCode");
//		//验证码大小
//		int width = 70;
//		int height = 23;
//		//去读缓存图片
//		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		//获取图像
//		Graphics graphics = image.getGraphics();
//		Random random = new Random();
//		//设定背景
//		graphics.setColor(getRandomColor(200, 250));
//		graphics.fillRect(0, 0, width, height);
//		graphics.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		graphics.setColor(getRandomColor(160, 200));
//		graphics.drawRect(0, 0, width-1, height-1);
//		graphics.setColor(getRandomColor(160, 200));
//		//画100条线
//		for(int i = 0;i<150;i++){
//			int x = random.nextInt(width);
//			int y = random.nextInt(height);
//			int xl = random.nextInt(10);
//			int yl = random.nextInt(10);
//			graphics.drawLine(x, y, xl, yl);
//		}
//		String sRandom = "";
//		for(int i = 0;i<4;i++){
//			String rand = String.valueOf(random.nextInt(10));
//			sRandom+=rand;
//			graphics.setColor(new Color(20+random.nextInt(110), 20+random.nextInt(110), 20+random.nextInt(110)));
//			graphics.drawString(rand, 13*i+14, 20);
//		}
//		//将验证码存入Session
//		session.setAttribute("verifyCode", sRandom);
//		//不能存request，因为
////		ServletActionContext.getRequest().setAttribute("code", sRandom);
////		System.out.println(ServletActionContext.getRequest().getAttribute("code"));
//		System.out.println(session.getAttribute("verifyCode"));
//		System.out.println(session.getAttribute("verifyCode").getClass());
//		//生成图像
//		graphics.dispose();
		//输出图像
		VerifyCode verifyCode = VerifyCode.getInstance();
		verifyCode.setSize(200, 100);
		String checkCode = verifyCode.getCheckCode();
		BufferedImage image = verifyCode.getCheckImg(checkCode);
		session.setAttribute("verifyCode", checkCode);
		System.out.println(session.getAttribute("verifyCode"));
		System.out.println(session.getAttribute("verifyCode").getClass());
		ImageIO.write(image, "jpeg", ServletActionContext.getResponse().getOutputStream());
		return null;
	}
	
//	private Color getRandomColor(int m,int n){
//		Random random = new Random();
//		if(m>255)m = 255;
//		if(n>255)n = 255;
//		int r = m + random.nextInt(n - m);
//		int g = m + random.nextInt(n - m);
//		int b = m + random.nextInt(n - m);
//		return new Color(r, g, b);
//	}
}
