package com.blog.LHT.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {

	public static String md5(String str) throws NoSuchAlgorithmException{
		String securityCode = null;
		MessageDigest md = MessageDigest.getInstance("md5");
		md.update(str.getBytes());
		securityCode = new BigInteger(1,md.digest()).toString(16);
		return securityCode;
	}
	
}
