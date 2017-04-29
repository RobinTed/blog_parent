package com.blog.LHT.service.testcrud;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.LHT.entity.Article;
import com.blog.LHT.service.ArticleService;
import com.blog.LHT.utils.Pager;

public class ArticleTest {

	private ArticleService articleService;
	private Article article;
	
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		articleService = (ArticleService) context.getBean("articleServiceImpl");
		article = new Article();
	}
	
	@Test
	public void testAdd(){
		article.setArticleName("测试");
		article.setUpdateTime(new Timestamp(new Date().getTime()));
		article.setArticleText("哈哈");
		article.setUserId(12);
		try {
			articleService.add(article);
			System.out.println(article.getArticleId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete(){
		article.setArticleId(1);
		articleService.delete(article);
	}
	
	@Test
	public void testLoad(){
		article = articleService.load(2);
		System.out.println(article);
	}
	
	@Test
	public void testUpdate(){
		article = articleService.load(3);
		article.setArticleName("嘻嘻");
		articleService.update(article);
	}
	
	@Test
	public void testListUserPage(){
		int currentPage = 1;
		int pageSize = 5;
		Pager<Article>pager = articleService.listPage(14,currentPage, pageSize);
		for(Article a:pager.getCurrentRecord()){
			System.out.println(a);
		}
	}
	
	@Test
	public void testListPage(){
		int currentPage = 1;
		int pageSize = 5;
		int beginPosition = 2;
		Pager<Article>pager = articleService.listPage(currentPage, pageSize,beginPosition);
		for(Article a:pager.getCurrentRecord()){
			System.out.println(a);
		}
	}
	
	@Test
	public void testSpecAll(){
		List<Article> list = articleService.loadSpecAll(12);
		for(Article article:list){
			System.out.println(article);
		}
	}
	
	//测试倒序查询
	@Test
	public void testDescList(){
		//获得最后一页的标号
		int totalCount = articleService.loadSpecAll(14).size();
		int pageSize = 5;
//		int totalPage = 0;
//		if(totalCount%pageSize == 0){
//			totalPage = totalCount/pageSize;
//		}else {
//			totalPage = totalCount/pageSize+1;
//		}
		int currentPage = 4;
		int beginPosition = totalCount-pageSize*currentPage;
		Pager<Article> listPage = articleService.listPage(14, currentPage, pageSize,beginPosition);
		List<Article>descPage = new ArrayList<>();
		System.out.println(totalCount);
		System.out.println(beginPosition);
		for(int i = listPage.getCurrentRecord().size()-1;i>=0;i--){
			descPage.add(listPage.getCurrentRecord().get(i));
		}
		for(Article article:listPage.getCurrentRecord()){
			System.out.println(article);
		}
		System.out.println("==========================================");
		for(Article article:descPage){
			System.out.println(article);
		}
	}
	
	@Test
	public void testAroundArticle(){
		int userId = 14;
		int articleId = 7;
		List<Article>articles = articleService.aroundArticle(userId, articleId);
		for(Article article:articles){
			System.out.println(article);
		}
	}
	
	@Test
	public void testByZero(){
		System.out.println(0%1);
	}
	
}
