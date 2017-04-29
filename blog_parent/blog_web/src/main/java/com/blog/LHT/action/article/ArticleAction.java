package com.blog.LHT.action.article;

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
import com.blog.LHT.service.UserService;
import com.blog.LHT.utils.Pager;
import com.blog.LHT.utils.ResourceRead;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class ArticleAction extends ActionSupport implements ModelDriven<Article>{

	private static final long serialVersionUID = 1L;
	private Article article = new Article();
	
	private ArticleService articleService;
	private UserService userService;
	private Integer currentPage;
//	private Integer userId;    被modeldriven覆盖了
//	
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public String showArticle(){
		Integer articleId = this.article.getArticleId();
		Article article = articleService.load(articleId);
		//下一篇文章
		Integer userId = article.getUserId();
		Integer articleView = article.getArticleView();
		if(articleView == null){
			articleView = 0;
		}else{
			articleView++;
		}
		article.setArticleView(articleView);
		articleService.update(article);
		User articleUser = userService.load(userId);
//		articleUser.setHeadImgPath(ResourceRead.getImgPath()+articleUser.getHeadImgPath());
//		ServletActionContext.getRequest().setAttribute("articleUser", articleUser);
		ServletActionContext.getRequest().setAttribute("currentArticle", article);
		//得到当前文章的页所属的页，pagesize为3，当前页为中间
		List<Article>aroundArticles = articleService.aroundArticle(userId, articleId);
		//得到上一篇文章
		if(aroundArticles.size() == 1){
			Article preArticle = new Article();
			Article nextArticle = new Article();
			preArticle.setArticleName("当前只有一篇文章");
			nextArticle.setArticleName("当前只有一篇文章");
			ServletActionContext.getRequest().setAttribute("preArticle",preArticle );
			ServletActionContext.getRequest().setAttribute("nextArticle", nextArticle);
		} else if(aroundArticles.size() == 2){    //最后一篇或者第一篇
			if(aroundArticles.get(0).getArticleId() == articleId){    //表明这是第一篇
				Article preArticle = new Article();       //为了使结构统一，这里new一个，下面一样
				preArticle.setArticleName("当前文章是该用户的第一篇");
				ServletActionContext.getRequest().setAttribute("preArticle",preArticle );
				ServletActionContext.getRequest().setAttribute("nextArticle", aroundArticles.get(1));
			}else{
				Article nextArticle = new Article();
				nextArticle.setArticleName("当前文章是该用户的最后一篇");
				ServletActionContext.getRequest().setAttribute("preArticle", aroundArticles.get(0));
				ServletActionContext.getRequest().setAttribute("nextArticle", nextArticle);
			}
		}else{
			ServletActionContext.getRequest().setAttribute("preArticle", aroundArticles.get(0));
			ServletActionContext.getRequest().setAttribute("nextArticle", aroundArticles.get(2));
		}
		
		//罗列5篇当前用户的最新文章
		List<Article>newArticle = new ArrayList<>();
		List<Article>descNewArticle = new ArrayList<>();
		int totalCount = articleService.loadSpecAll(userId).size();
		int currentPage = 1;
		int pageSize = 0;
		int beginPosition = 0;
		if(totalCount<5){
			beginPosition = 0;
			pageSize = totalCount;
		}else{
			pageSize = 5;
			beginPosition = totalCount - currentPage*pageSize;
		}
		Pager<Article> pager = articleService.listPage(userId, currentPage, pageSize, beginPosition);
		newArticle = pager.getCurrentRecord();
		for(int i = newArticle.size()-1;i>=0;i--){
			descNewArticle.add(newArticle.get(i));
		}
		ServletActionContext.getRequest().setAttribute("newArticle", descNewArticle);
		ServletActionContext.getRequest().setAttribute("totalCount", totalCount);
		//注意：如果把这一行代码放到上面的查询周围语句去，会导致hibernate执行update user的动作，因为这里改变了
		articleUser.setHeadImgPath(ResourceRead.getImgPath()+articleUser.getHeadImgPath());
		ServletActionContext.getRequest().setAttribute("articleUser", articleUser);
		return "showArticlePage";
	}
	
	public String listArticle(){
		if(this.currentPage == null){
			this.currentPage = 1;
		}
		Integer userId = this.article.getUserId();
		int totalCount = articleService.loadSpecAll(userId).size();
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
		if(beginPosition<=0){    //注意改变
			beginPosition = 0;
			pageSize = totalCount-pageSize*(currentPage-1);
		}
		Pager<Article> pager = articleService.listPage(userId, currentPage, pageSize, beginPosition);
		//再全部倒转过来
		List<Article>descArticle = new ArrayList<>();
		for(int i = pager.getCurrentRecord().size()-1;i>=0;i--){
			descArticle.add(pager.getCurrentRecord().get(i));
		}
		pager.setCurrentRecord(descArticle);
		ServletActionContext.getRequest().setAttribute("userArticlePager", pager);
		User user = userService.load(userId);
		user.setHeadImgPath(ResourceRead.getImgPath()+user.getHeadImgPath());
		ServletActionContext.getRequest().setAttribute("user", user);
		return "listArticle";
	}
	
	public String mUserList(){
		if(this.currentPage == null){
			this.currentPage = 1;
		}
		Integer userId = this.article.getUserId();
		int totalCount = articleService.loadSpecAll(userId).size();
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentPage>totalPage){
			try {
				ServletActionContext.getResponse().getWriter().print("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			int beginPosition = totalCount-pageSize*currentPage;
			if(beginPosition<=0){    //注意改变
				beginPosition = 0;
				pageSize = totalCount-pageSize*(currentPage-1);
			}
			Pager<Article> pager = articleService.listPage(userId, currentPage, pageSize, beginPosition);
			ServletActionContext.getRequest().setAttribute("pager", pager);
			List<Map<String, Object>>infoMap = new ArrayList<>();
			List<Article>descArticle = pager.getCurrentRecord();
			User user = userService.load(userId);
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
		}

		return NONE;
	}
	
	@Override
	public Article getModel() {
		return article;
	}

}
