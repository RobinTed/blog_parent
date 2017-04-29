package com.blog.LHT.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtil {

	public static void sendMessage(String receiver,String subject,String content) throws MessagingException{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");//设置邮件发送主机地址
		props.put("mail.smtp.auth", "true");//是否需要验证用户身份
		
		Session session = Session.getInstance(props);//得到Session
//		session.setDebug(true);//打开debug
		
		//设置邮件格式为mime类型
		MimeMessage message = new MimeMessage(session);
		
		//设置发送者
		Address senderAddress  = new InternetAddress("15629000782@163.com");
		message.setFrom(senderAddress);
		//设置接收者
		Address receiverAddress = new InternetAddress(receiver);
		message.setRecipient(RecipientType.TO, receiverAddress);
		
		//设置邮件主题
		message.setSubject(subject);
		//设置邮件内容
		message.setContent(content, "text/html;charset=utf-8");
		//保存邮件
		message.saveChanges();
		//得到发送手段
		Transport transport = session.getTransport("smtp");
		//连接上服务器，带上@163报错
		transport.connect("smtp.163.com", "15629000782", "163mail156");
		//发送邮件
		transport.sendMessage(message, message.getAllRecipients());
		//关闭通道
		transport.close();
	}
	
}
