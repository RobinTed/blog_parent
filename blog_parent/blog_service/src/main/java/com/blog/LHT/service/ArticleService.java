package com.blog.LHT.service;

import java.util.List;

import com.blog.LHT.entity.Article;
import com.blog.LHT.utils.Pager;

public interface ArticleService {

	public void add(Article article);
	public void delete(Article article);
	public void update(Article article);
	public Article load(int id);
	public List<Article>loadAll();
	public List<Article>loadSpecAll(int userId);//查询某个用户的所有
	public Pager<Article> findByCondition(String query);
	public List<Article> findByHQL(String queryString,Object[]params);//根据hql查询
	public Pager<Article> listPage(int currentPage,int pageSize,int beginPosition);       //分页列出文章
//	public Pager<Article> listPage(int userId,int currentPage,int pageSize);
	public Pager<Article> listPage(int userId,int currentPage,int pageSize,int beginPosition);//自定义起始位置
	public List<Article>aroundArticle(int userId,int articleId);//查找当前文章周围的文章
	
}
