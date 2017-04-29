package com.blog.LHT.utils.test;

import javax.mail.MessagingException;

import org.junit.Test;

import com.blog.LHT.utils.MailUtil;

public class MailTest {

	@Test
	public void testMail(){
		try {
//			MailUtil.sendMessage("389829614@qq.com", "你好", "晚上吃饭");
			MailUtil.sendMessage("2890248516@qq.com", "你好", "晚上吃饭");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
