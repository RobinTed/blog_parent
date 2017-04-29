package com.blog.LHT.service.recomm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.LHT.entity.Article;
import com.blog.LHT.service.ArticleService;

public class ImgRecomm {

	private ArticleService articleService;
	private Article article;
	private String articleText;
	
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		articleService = (ArticleService) context.getBean("articleServiceImpl");
		article = new Article();
	}
	
	//推荐三张最新的图片
	@Test
	public void testNewestImg(){
		List<Article> list = articleService.loadAll();
		List<Article>containsImg = new ArrayList<>();
		int totalArticle = list.size();
		if(totalArticle<=3){
			for(int i = totalArticle-1;i>=0;i--){
				article = list.get(i);
				if(article.getArticleText().contains("http://localhost:8080/img/article")){
					containsImg.add(article);
				}
			}
		}else{
			for(int i = totalArticle-1;i>=0;i--){
				article = list.get(i);
				if(article.getArticleText().contains("http://localhost:8080/img/article")){
					containsImg.add(article);
				}
				if(containsImg.size()>=3)break;
			}
		}
		
		for(Article article:containsImg){
			System.out.println("==========================================");
			System.out.println(article.getArticleText());
		}
	}
	
	//推荐阅读量最高的三张图片
	@Test
	public void testHotImg(){
		List<Article>allArticle = new ArrayList<>();
		allArticle = articleService.loadAll();
		//获取所有的浏览量和文章id
		Map<Integer,Integer>userImg = new HashMap<>();
		for(int i = 0;i<allArticle.size();i++){                      //此处效率极低，真的开发要么用配置文件，要么新建表
			Integer key = allArticle.get(i).getArticleId();
			Integer value = allArticle.get(i).getArticleView();
			if(value == null){
				value = 0;
			}
			userImg.put(key, value);
		}
		List<Integer>view = new ArrayList<>();
		for(Integer key:userImg.keySet()){
			view.add(userImg.get(key));
		}
		//将浏览量排序
		Collections.sort(view);
		for(int i = 0;i<view.size();i++){
			System.out.println(view.get(i));
		}
		System.out.println("===========================");
		//得到最高的个，测试用
		if(view.size()<3){
			for(int i:view){
				System.out.println(i);
			}
		}else{
			for(int i = view.size()-1;i>=view.size()-3;i--){
				System.out.println(view.get(i));
			}
		}
		//根据得到的map的value进行排序
		System.out.println("=============================");
		userImg = sortMap(userImg);
		System.out.println(userImg);
		List<Entry<Integer, Integer>>myList = new ArrayList<Map.Entry<Integer,Integer>>(userImg.entrySet());
		System.out.println("===================");
		System.out.println(myList);
		List<Entry<Integer,Integer>> subList = new ArrayList<>();
		//将排好的map装在一个list里面为了方便的获取位置，获得最后的三个
		if(myList.size()<3){
			subList = myList;
		}else{
			subList = myList.subList(myList.size()-3, myList.size());
		}
		System.out.println(subList);
	}
	
    public static Map sortMap(Map oldMap) {  
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
  
            @Override  
            public int compare(Entry<java.lang.String, Integer> arg0,  
                    Entry<java.lang.String, Integer> arg1) {  
                return arg0.getValue() - arg1.getValue();  
            }  
        });  
        Map newMap = new LinkedHashMap();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    } 
    
    /**
     * <p>范文芳<img style="width:60%;height:60%;" src="http://localhost:8080/img/article/14_article_20170423T161637.jpg" alt="" /><img alt="大哭" src="/blog_parent/xheditor_code/xheditor/xheditor_emot/default/wail.gif" /><br /></p><p><table cellspacing="1" cellpadding="1" border="1" width="200"><tbody><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><br /></p>
     */
	
    //正则表达式匹配图片标签
    @Test
    public void testTag(){
    	//<img([^/>]*)
    	String regEx = "<img([^>]*)";
    	Pattern pattern = Pattern.compile(regEx);
    	String text = articleService.load(11).getArticleText();
    	List<String>result = new ArrayList<>();
    	Matcher matcher = pattern.matcher(text);
    	while(matcher.find()){
    		result.add(matcher.group(0));
    	}
    	System.out.println(result);
    	List<String>imgTag = new ArrayList<>();
    	for(String str:result){
    		if(str.contains("/img/article/")){
    			imgTag.add(str);
    		}
    	}
    	List<String>imgURL = new ArrayList<>();
    	System.out.println(imgTag);
    	for(String str:imgTag){
    		imgURL.add(str.substring(str.indexOf("http://"), str.indexOf("\" alt")));
    	}
    	System.out.println(imgURL);
    }
    
    //获得阅读量最高的文章中的三张图片
    @Test
    public void getHotImg(){
    	//获得文章
    	List<Article>allArticle = new ArrayList<>();
		allArticle = articleService.loadAll();
		//获取所有的浏览量和文章id
		Map<Integer,Integer>userImg = new HashMap<>();
		for(int i = 0;i<allArticle.size();i++){                      //此处效率极低，真的开发要么用配置文件，要么新建表
			Integer key = allArticle.get(i).getArticleId();
			Integer value = allArticle.get(i).getArticleView();
			if(value == null){
				value = 0;
			}
			userImg.put(key, value);
		}
		List<Integer>view = new ArrayList<>();
		for(Integer key:userImg.keySet()){
			view.add(userImg.get(key));
		}
		//将浏览量排序
		Collections.sort(view);
		//根据得到的map的value进行排序
		userImg = sortMap(userImg);
		List<Entry<Integer, Integer>>myList = new ArrayList<Map.Entry<Integer,Integer>>(userImg.entrySet());
		List<Entry<Integer,Integer>> subList = new ArrayList<>();
		List<String>articleImg = new ArrayList<>();  //包含文章的最热的文章内容
		List<Integer>articleId = new ArrayList<>();   //包含最热文章的ID
		//将排好的map装在一个list里面为了方便的获取位置，获得最后的三个
		
		if(myList.size()<3){
			subList = myList;
		}else{
			//遍历myList，并且包含了img才加进去
			for(int i = myList.size()-1;i>=0;i--){
				articleText = articleService.load(myList.get(i).getKey()).getArticleText();
				if(articleText.contains("/img/article/")){  //表示包含了文章图片
					articleImg.add(articleText);
					articleId.add(myList.get(i).getKey());
				}
				if(articleImg.size()>=3){
					break;
				}
			}
//			System.out.println(articleId);
//			System.out.println(articleImg);
//			subList = myList.subList(myList.size()-3, myList.size());
		}
		
		//获得链接
		String regEx = "<img([^>]*)";
    	Pattern pattern = Pattern.compile(regEx);
    	List<String>result = new ArrayList<>();
    	for(int i = 0;i<articleImg.size();i++){   //遍历最高的文章内容
    		Matcher matcher = pattern.matcher(articleImg.get(i));
        	while(matcher.find()){
        		result.add(matcher.group(0));
        	}
    	}
    	List<String>imgTag = new ArrayList<>();
    	for(String str:result){
    		if(str.contains("/img/article/")){
    			imgTag.add(str);
    		}
    	}
    	List<String>imgURL = new ArrayList<>();
//    	System.out.println(imgTag);
    	for(String str:imgTag){
    		imgURL.add(str.substring(str.indexOf("http://"), str.indexOf("\" alt")));
    	}
    	System.out.println(myList);
    	System.out.println(articleId);
    	System.out.println(imgURL);
    }
    
}
