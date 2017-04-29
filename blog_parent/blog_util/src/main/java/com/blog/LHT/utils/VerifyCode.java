package com.blog.LHT.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCode {

	private int width;
	private int height;
	private int num;
	private String code;
	private static Random random = new Random();
	private VerifyCode(){
		num = 4;
		code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	}
	private static VerifyCode verifyCode = null;
	public static synchronized VerifyCode getInstance(){
		if(verifyCode == null){
			verifyCode = new VerifyCode();
		}
		return verifyCode;
	}
	
	//设置图片信息
	public void setInfo(int width,int height,int num,String code){
		this.width = width;
		this.height = height;
		this.num = num;
		this.code = code;
	}
	
	//设置图片大小
	public void setSize(int width,int height){
		this.width = width;
		this.height = height;
	}
	
	//获取验证码
	public String getCheckCode(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<num;i++){
			sb.append(String.valueOf(code.charAt(random.nextInt(code.length()))));
		}
		return sb.toString();
	}
	
	//画图
	public BufferedImage getCheckImg(String checkCode){
		//创建一张图片
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		//获得画笔
		Graphics2D graphics2d = img.createGraphics();
		//设置画笔的颜色
		graphics2d.setColor(Color.WHITE);
		//画一个方角矩形
		graphics2d.fillRect(0, 0, width, height);
		//给画笔设置颜色
		graphics2d.setColor(Color.BLACK);
		//给图片画边框
		graphics2d.drawRect(0, 0, width-1, height-1);
		//设置字体，为宋体，粗体加斜体
		Font font = new Font("宋体", Font.BOLD+Font.ITALIC, (int) (height*0.7));
		//把字体赋给画笔
		graphics2d.setFont(font);
		//开始在图片上写字
		for(int i = 0;i<num;i++){
			//随机给画笔赋予颜色
			graphics2d.setColor(new Color(random.nextInt(200), random.nextInt(200), random.nextInt(200)));
			//在图片上写字
			graphics2d.drawString(String.valueOf(checkCode.charAt(i)), i*(width/num)+3, (int) (height*0.7));
		}
		
		//给图片加点
		for(int i = 0;i<width*num;i++){
			graphics2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			graphics2d.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		//给图片加线
		for(int i = 0;i<num;i++){
			graphics2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			graphics2d.drawLine(0, random.nextInt(height), width, random.nextInt(height));
		}
		return img;
	}
	
}
