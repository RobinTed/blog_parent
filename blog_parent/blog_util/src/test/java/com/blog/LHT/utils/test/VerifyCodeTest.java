package com.blog.LHT.utils.test;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.blog.LHT.utils.VerifyCode;

public class VerifyCodeTest {

	@Test
	public void testVerifyCode(){
		VerifyCode verifyCode = VerifyCode.getInstance();
		verifyCode.setSize(200, 100);
		String checkCode = verifyCode.getCheckCode();
		System.out.println(checkCode);
		try {
			ImageIO.write(verifyCode.getCheckImg(checkCode), "jpg", new File("C:/Users/Tanl/Desktop/checkCode.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
