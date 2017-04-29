package com.blog.LHT.action.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.blog.LHT.entity.Article;
import com.blog.LHT.entity.User;
import com.blog.LHT.service.ArticleService;
import com.blog.LHT.utils.ClearHTMLTag;
import com.blog.LHT.utils.Pager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	private User user = new User();

	private ArticleService articleService;
	private Integer currentPage;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public String mainPage(){
		if(this.currentPage == null){
			this.currentPage = 1;
		}
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
//		Pager< Article>pager = articleService.listPage(user.getUserId(),currentPage, 5);    //要倒序排列，放弃这种方法
		int userId = user.getUserId();
		int totalCount = articleService.loadSpecAll(userId).size();
		int pageSize = 5;
		int beginPosition = totalCount-pageSize*currentPage;
		if(beginPosition<=0){    //注意改变
			beginPosition = 0;
			pageSize = totalCount-pageSize*(currentPage-1);
		}
		Pager<Article> pager = articleService.listPage(userId, currentPage, pageSize, beginPosition);
		//做一个简单的摘要，前200个字
		for(int i = 0;i<pager.getCurrentRecord().size();i++){
			Article article = pager.getCurrentRecord().get(i);
			String htmlStr = ClearHTMLTag.getTextHTML(article.getArticleText());
			String subStr = "";
			if(htmlStr.length()<=200){
				subStr = htmlStr;
			}else{
				subStr = htmlStr.substring(0, 201);
			}
			pager.getCurrentRecord().get(i).setArticleText(subStr);
		}
		//再全部倒转过来
		List<Article>descArticle = new ArrayList<>();
		for(int i = pager.getCurrentRecord().size()-1;i>=0;i--){
			descArticle.add(pager.getCurrentRecord().get(i));
		}
		pager.setCurrentRecord(descArticle);
		ServletActionContext.getRequest().setAttribute("pager", pager);
		return "mainPage";
	}
	
	public String mUserPage(){
		if(this.currentPage == null){
			this.currentPage = 1;
		}
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
//		Pager< Article>pager = articleService.listPage(user.getUserId(),currentPage, 5);    //要倒序排列，放弃这种方法
		int userId = user.getUserId();
		int totalCount = articleService.loadSpecAll(userId).size();
		int pageSize = 5;
		int beginPosition = totalCount-pageSize*currentPage;
		if(beginPosition<=0){    //注意改变
			beginPosition = 0;
			pageSize = totalCount-pageSize*(currentPage-1);
		}
		Pager<Article> pager = articleService.listPage(userId, currentPage, pageSize, beginPosition);
		ServletActionContext.getRequest().setAttribute("pager", pager);
		List<Map<String, Object>>infoMap = new ArrayList<>();
		List<Article>descArticle = pager.getCurrentRecord();
		for(int i = descArticle.size()-1;i>=0;i--){
			Map<String,Object>currentMap = new HashMap<>();
			currentMap.put("id", descArticle.get(i).getArticleId());
			String title = descArticle.get(i).getArticleName();
			int begin = title.indexOf("\">");
			int end = title.indexOf("</");
			currentMap.put("title", title.substring(begin+2, end));
			currentMap.put("user", user.getUserName());
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
		return NONE;
	}
	
	public String modifyPage(){
		return "modifyPage";
	}
	
	public String writePage(){
		return "writePage";
	}
	
	@Override
	public User getModel() {
		return user;
	}

}
