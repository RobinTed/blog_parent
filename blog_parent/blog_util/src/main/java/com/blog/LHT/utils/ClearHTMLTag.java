package com.blog.LHT.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClearHTMLTag {

	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
    
    private static String deleteTag(String htmlStr){
    	Pattern patternHTML = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); //创建HTML的正则表达式
    	Matcher matcherHTML = patternHTML.matcher(htmlStr);
    	htmlStr = matcherHTML.replaceAll("");
    	return htmlStr;
    }
    
    public static String getTextHTML(String htmlStr){
    	return deleteTag(htmlStr);
    }
	
}
