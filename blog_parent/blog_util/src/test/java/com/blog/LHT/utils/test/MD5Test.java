package com.blog.LHT.utils.test;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.blog.LHT.utils.MD5Util;

public class MD5Test {

	@Test
	public void testMD5(){
//		String code = "013";
		String code = "123";
		String old = null;
		try {
			old = MD5Util.md5(code);
			System.out.println(old);
			System.out.println(old.equals(MD5Util.md5("123")));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
