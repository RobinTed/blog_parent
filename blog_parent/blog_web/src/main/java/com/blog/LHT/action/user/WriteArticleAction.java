package com.blog.LHT.action.user;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
//import org.junit.Test;

import com.blog.LHT.entity.Article;
import com.blog.LHT.entity.User;
import com.blog.LHT.service.ArticleService;
import com.blog.LHT.utils.ResourceRead;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class WriteArticleAction extends ActionSupport implements ModelDriven<Article>{

	private static final long serialVersionUID = 1L;

	private Article article = new Article();
	private ArticleService articleService;
	
	private static List<String>fileType = Arrays.asList("bmp","gif","jpeg","png","jpg","tga","JPG","BMP","GIF","JPEG","PNG","TAG");
	private static final String ARTICLE_PATH = ResourceRead.getImgPath()+"article/";
	private static final String REAL_ARTICLE_PATH = ResourceRead.getImgRealPath()+"article/";
	
//	private String test;
	private File articleImg;
	private String articleImgFileName;
//	private File file;
	
	public void setArticleImg(File articleImg) {
		this.articleImg = articleImg;
	}
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public void setArticleImgFileName(String articleImgFileName) {
		this.articleImgFileName = articleImgFileName;
	}
	
	//获得文件的后缀
	private static String getFileType(String fileName){
		int position = fileName.lastIndexOf(".")+1;
		if(position == 0){
			return null;
		}else{
			return fileName.substring(position);
		}
	}
	
	//上传的方法,图片插入文章，是要先上传到服务器，服务器生成链接，再把响应图片的链接插入到文章当中的
	public String upload(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String imgName = "";
		try {
			if(articleImgFileName == null){
				ServletActionContext.getResponse().getWriter().print("0");    //表示没有选择文件，为空
			}else if(!fileType.contains(getFileType(articleImgFileName))){
				ServletActionContext.getResponse().getWriter().print("2");      //表示文件的格式不正确
			}else{
				//文章图片的命名：用户ID+article+当前时间精确到秒
				imgName = user.getUserId()+"_"+"article"+"_"+LocalDateTime.now().toString().split("\\.")[0].replaceAll(":", "").replaceAll("-", "")+"."+getFileType(articleImgFileName);
				ServletActionContext.getRequest().setAttribute("articleImgPath", ARTICLE_PATH+imgName);
				FileUtils.copyFile(articleImg, new File(REAL_ARTICLE_PATH+imgName));
				ServletActionContext.getResponse().getWriter().print(ARTICLE_PATH+imgName);       //表示正常上传
			}
		} catch (IOException e) {
//			try {
//				ServletActionContext.getResponse().getWriter().print("-1");      //文件超过2M
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}  
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String publish(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		this.article.setUserId(user.getUserId());
		this.article.setUpdateTime(new Timestamp(new Date().getTime()));
		articleService.add(this.article);
		Integer articleId = this.article.getArticleId();
		if(this.article.getArticleName() == null||"".equals(this.article.getArticleName().trim())){
			this.article.setArticleName(LocalDateTime.now().toString());
		}
		//<a href="${prc}/article/article_showArticle?articleId=articleId">articleName</a>
		String articleURL = "<a href=\"/blog_parent/article/article_showArticle?articleId="+articleId+"\">"+this.article.getArticleName()+"</a>";
		Article article = articleService.load(articleId);
		article.setArticleName(articleURL);           //更改文章的名称，加上链接
		if(article.getArticleView() == null){                
			article.setArticleView(0);                     //默认0次
		}
		try {
			articleService.update(article);
			ServletActionContext.getResponse().getWriter().print("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
//	@Test
//	public void testTime(){
//		String timeStr = LocalDateTime.now().toString();
//		String[]strs = timeStr.split("\\.");
//		System.out.println(timeStr);
//		System.out.println(strs[0].replaceAll(":", "").replaceAll("-", ""));
//	}
//	@Override
	public Article getModel() {
		return article;
	}
	
}
