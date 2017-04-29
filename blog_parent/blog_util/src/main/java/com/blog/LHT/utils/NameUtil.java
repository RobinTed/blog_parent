package com.blog.LHT.utils;

import java.time.LocalTime;

import org.junit.Test;


/**
 * 规范上传文件的命名
 * 用户图片存储名称说明
	*_*_*
	第一位：上传用户的ID
	第二位:上传的用途，可取值：head或者bg
	第三位：上传时间，毫秒
 * @author Tanl
 *
 */

public class NameUtil {

	public static String getHeadName(int id,String suffix){
		String headName = id+"_"+"head"+suffix;
		return headName;
	}
	
	public static String getBgName(int id,String suffix){
		String bgName = id+"_"+"bg"+suffix;
		return bgName;
	}
	
	@Test
	public void testTime(){
		System.out.println(LocalTime.now().toString());
	}
}
