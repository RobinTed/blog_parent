package com.blog.LHT.action;


import java.io.IOException;
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

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.blog.LHT.entity.Article;
import com.blog.LHT.entity.User;
import com.blog.LHT.service.ArticleService;
import com.blog.LHT.service.UserService;
import com.blog.LHT.utils.Pager;
import com.blog.LHT.utils.ResourceRead;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class WelcomeAction extends ActionSupport implements ModelDriven<Article>{

	private static final long serialVersionUID = 1L;
	private Article article = new Article();

	private ArticleService articleService;
	private Integer currentPage;
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public String mainPage(){
		if(this.currentPage == null||currentPage<1){
			this.currentPage = 1;
		}
		int totalCount = articleService.loadAll().size();
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentPage>totalPage){
			currentPage = totalPage;
		}
		int beginPosition = totalCount-pageSize*currentPage;
		Pager<Article> pager = new Pager<>();
		if(beginPosition<=0){    //小于0取子串
//			if(totalCount == 0){
//				Article article = new Article();
//				article.setArticleText("发一篇试试");
//				article.setArticleName("初始博文");
//				article.setArticleView(0);
//				List<Article>init = new ArrayList<>();
//				init.add(article);
//				pager.setCurrentRecord(init);
//			}else{
				int subSize = 10+beginPosition;
				beginPosition = 0;
				if(subSize<0){
					subSize = 5;
				}
//				pageSize = totalCount-pageSize*(currentPage-1);
				pager = articleService.listPage(currentPage, pageSize, beginPosition);
				List<Article>subList = pager.getCurrentRecord().subList(0, subSize);
				pager.setCurrentRecord(subList);
//			}
		}else{
			pager = articleService.listPage(currentPage, pageSize,beginPosition);
		}
		
		//再全部倒转过来
		List<Article>descArticle = new ArrayList<>();
		List<User>users = new ArrayList<>();
//		List<User>us = new ArrayList<>();
		User user = new User();
		try {
			for(int i = pager.getCurrentRecord().size()-1;i>=0;i--){
				descArticle.add(pager.getCurrentRecord().get(i));
				user = userService.load(pager.getCurrentRecord().get(i).getUserId());
				users.add(user);
			}
		} catch (NullPointerException e) {
			user.setUserName("原始人");
			String headPath = ResourceRead.getImgPath()+"1.jpg";
			user.setHeadImgPath(headPath);
		}
//		for(int i = users.size()-1;i>=0;i--){
//			User u = new User();
//			u = users.get(i);
//			u.setHeadImgPath(ResourceRead.getImgPath()+u.getHeadImgPath());
//			us.add(u);
//		}
		pager.setCurrentRecord(descArticle);
//		int nextCount = currentPage;
//		nextCount = nextCount+1;
//		ServletActionContext.getRequest().setAttribute("nextCount", nextCount);
		ServletActionContext.getRequest().setAttribute("users", users);
		ServletActionContext.getRequest().setAttribute("allArticlePager", pager);
//		System.out.println(currentPage);
		
		////////////////////////////////////////////////////////推荐最热的文章中的三张图片
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
		String articleText = "";
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
		String regEx = "<img([^>]*)";         //以<img开头>结尾
    	Pattern pattern = Pattern.compile(regEx);
    	List<String>result = new ArrayList<>();
    	for(int i = 0;i<articleImg.size();i++){   //遍历最高的文章内容
    		Matcher matcher = pattern.matcher(articleImg.get(i));
        	while(matcher.find()){
        		result.add(matcher.group(1));  //只获取第一个，不要写0获取全部，有bug
        		break;
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
//    	System.out.println(myList);
//    	System.out.println(articleId);
//    	System.out.println(imgURL);
		ServletActionContext.getRequest().setAttribute("articleId", articleId);
		ServletActionContext.getRequest().setAttribute("imgURL", imgURL);
		ServletActionContext.getRequest().setAttribute("imgPath", ResourceRead.getImgPath());
		return "mainPage";
	}
	
//	public String listAllArticle(){
//		if(this.currentPage == null){
//			this.currentPage = 1;
//		}
//		int totalCount = articleService.loadAll().size();
//		int pageSize = 10;
//		int beginPosition = totalCount-pageSize*currentPage;
//		if(beginPosition<=0){    //注意改变
//			beginPosition = 0;
//			pageSize = totalCount-pageSize*(currentPage-1);
//		}
//		Pager<Article> pager = articleService.listPage(currentPage, pageSize);
//		//再全部倒转过来
//		List<Article>descArticle = new ArrayList<>();
//		List<User>users = new ArrayList<>();
//		for(int i = pager.getCurrentRecord().size()-1;i>=0;i--){
//			descArticle.add(pager.getCurrentRecord().get(i));
//			users.add(userService.load(pager.getCurrentRecord().get(i).getUserId()));
//		}
//		pager.setCurrentRecord(descArticle);
//		ServletActionContext.getRequest().setAttribute("users", users);
//		ServletActionContext.getRequest().setAttribute("allArticlePager", pager);
//		return "mainPage";
//	}

	
	//移动端的接口，返回json
	public String mListView(){
		if(currentPage == null){
			currentPage = 1;
		}
		int pageSize = 10;
		int totalCount = articleService.loadAll().size();
		int beginPosition = totalCount - currentPage*pageSize;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentPage>totalPage){
			currentPage = totalPage;
		}
		Pager<Article> pager = new Pager<>();
		if(beginPosition<=0){    //小于0取子串
			if(beginPosition<-10){
				pager.setCurrentRecord(null);
			}else{
				int subSize = 10+beginPosition;
				beginPosition = 0;
//				pageSize = totalCount-pageSize*(currentPage-1);
				pager = articleService.listPage(currentPage, pageSize, beginPosition);
				List<Article>subList = pager.getCurrentRecord().subList(0, subSize);
				pager.setCurrentRecord(subList);
			}
		}else{
			pager = articleService.listPage(currentPage, pageSize,beginPosition);
		}
		List<Article>descArticle = new ArrayList<>();
		List<User>users = new ArrayList<>();
		User user = new User();
		if(pager.getCurrentRecord() == null){
			try {
				ServletActionContext.getResponse().getWriter().print("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			for(Article article: pager.getCurrentRecord()){
				descArticle.add(article);
				user = userService.load(article.getUserId());
				users.add(user);
			}
//			Map<String,Object>infoMap = new HashMap<>();
			List<Map<String, Object>>infoMap = new ArrayList<>();
			for(int i = descArticle.size()-1;i>=0;i--){
				Map<String,Object>currentMap = new HashMap<>();
				currentMap.put("id", descArticle.get(i).getArticleId());
				String title = descArticle.get(i).getArticleName();
				int begin = title.indexOf("\">");
				int end = title.indexOf("</");
				currentMap.put("title", title.substring(begin+2, end));
				currentMap.put("user", users.get(i).getUserName());
				currentMap.put("scan", descArticle.get(i).getArticleView());
				currentMap.put("time", descArticle.get(i).getUpdateTime().toString());
				infoMap.add(currentMap);
			}
			JSONArray jsonArray = JSONArray.fromObject(infoMap);
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				//解决中文乱码
				response.setContentType("text/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(jsonArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return NONE;
	}
	
	//根据map的value排序
    private Map sortMap(Map oldMap) {  
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
	
	@Override
	public Article getModel() {
		return article;
	}
	
}
