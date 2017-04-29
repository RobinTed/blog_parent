package com.blog.LHT.utils.test;

import org.junit.Test;

import com.blog.LHT.utils.ClearHTMLTag;

public class HTMLTest {

	@Test
	public void testDeleteTags(){
		String htmlStr = "<p>范文芳<img style=\"width:60%;height:60%;\" src=\"http://localhost:8080/img/article/14_article_20170423T161637.jpg\" alt=\"\" /><img alt=\"大哭\" src=\"/blog_parent/xheditor_code/xheditor/xheditor_emot/default/wail.gif\" /><br /></p><p><table cellspacing=\"1\" cellpadding=\"1\" border=\"1\" width=\"200\"><tbody><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><br /></p>";
		System.out.println(ClearHTMLTag.getTextHTML(htmlStr));
	}
	
}
