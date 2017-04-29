package com.blog.LHT.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.blog.LHT.dao.BaseDao;
import com.blog.LHT.entity.Article;
import com.blog.LHT.service.ArticleService;
import com.blog.LHT.utils.Pager;

@Transactional
public class ArticleServiceImpl implements ArticleService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void add(Article article) {
		baseDao.add(article);
	}

	@Override
	public void delete(Article article) {
		baseDao.delete(article);
	}

	@Override
	public void update(Article article) {
		baseDao.update(article);
	}

	@Override
	public Article load(int id) {
		return baseDao.load(Article.class, id);
	}

	@Override
	public List<Article> loadAll() {
		return baseDao.loadAll(Article.class);
	}

	@Override
	public Pager<Article> findByCondition(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findByHQL(String queryString, Object[] params) {
		return baseDao.findByHQL(queryString, params);
	}

	/**
	 * 用户ID为userId的分页查询
	 */
//	@Override
//	public Pager<Article> listPage(int userId,int currentPage, int pageSize) {
//		Pager<Article>pager = new Pager<>();
//		pager.setCurrentPage(currentPage);
//		pager.setPageSize(pageSize);
//		Long recordCount = (long) this.loadSpecAll(userId).size();
//		pager.setRecordCount(recordCount);
//		Long pageCount = (long) 0;
//		if(recordCount%pageSize == 0){
//			pageCount = recordCount/pageSize;
//		}else{
//			pageCount = recordCount/pageSize+1;
//		}
//		pager.setPageCount(pageCount);
//		int beginPosition = (currentPage-1)*pageSize;
//		pager.setBeginPosition(beginPosition);
//		Article article = new Article();
//		article.setUserId(userId);
//		List<Article>currentRecord = baseDao.findPage(article, beginPosition, pageSize);
//		pager.setCurrentRecord(currentRecord);
//		return pager;
//	}

	/**
	 * 用户ID为userId的分页查询
	 */
	@Override
	public Pager<Article> listPage(int userId,int currentPage, int pageSize,int beginPosition) {
		Pager<Article>pager = new Pager<>();
		pager.setCurrentPage(currentPage);
		pager.setPageSize(pageSize);
		Long recordCount = (long) this.loadSpecAll(userId).size();
		pager.setRecordCount(recordCount);
		Long pageCount = (long) 0;
		if(pageSize == 0){
			pageSize = 5;
		}
		if(recordCount%pageSize == 0){
			pageCount = recordCount/pageSize;
		}else{
			pageCount = recordCount/pageSize+1;
		}
		pager.setPageCount(pageCount);
//		int beginPosition = (currentPage-1)*pageSize;
		pager.setBeginPosition(beginPosition);
		Article article = new Article();
		article.setUserId(userId);
		List<Article>currentRecord = baseDao.findPage(article, beginPosition, pageSize);
		pager.setCurrentRecord(currentRecord);
		return pager;
	}
	
	/**
	 * 所有文章的分页查询
	 */
	@Override
	public Pager<Article> listPage(int currentPage, int pageSize,int beginPosition) {
		Pager<Article>pager = new Pager<>();
		pager.setCurrentPage(currentPage);
		pager.setPageSize(pageSize);
		Long recordCount = (long) baseDao.loadAll(Article.class).size();
		pager.setRecordCount(recordCount);
		Long pageCount = (long) 0;
		if(recordCount%pageSize == 0){
			pageCount = recordCount/pageSize;
		}else{
			pageCount = recordCount/pageSize+1;
		}
		pager.setPageCount(pageCount);
		pager.setBeginPosition(beginPosition);
		List<Article>currentRecord = baseDao.findPage(Article.class, beginPosition, pageSize);
		pager.setCurrentRecord(currentRecord);
		return pager;
	}

	@Override
	public List<Article> loadSpecAll(int userId) {
		Article article = new Article();
		article.setUserId(userId);
		List<Article> list = baseDao.loadSpecAll(article);
		return list;
	}

	@Override
	public List<Article> aroundArticle(int userId, int articleId) {    //传参数的时候，articleId一定是属于userId的
		List<Article>userArticles = this.loadSpecAll(userId);
		List<Integer>articleIds = new ArrayList<>();
		
		for(Article article:userArticles){
			articleIds.add(article.getArticleId());        //得到当前用户的所有文章ID
		}
		int position = articleIds.indexOf(articleId);    //indexOf从0开始
//		System.out.println(articleIds.size());
//		System.out.println(position);
		Article article = new Article();
		article.setUserId(userId);
		List<Article> list = new ArrayList<>();
		if(articleIds.size() == 1){         //只有一篇文章
			list = baseDao.findPage(article, position, 1);
		}else{                       //两篇及以上的文章
			if(position == 0){              //如果当前文章为第一篇，则没有上一篇文章，返回数组大小为2
				list = baseDao.findPage(article, position, 2);
			}else if(position == articleIds.size()-1){        //如果当前文章为最后一篇，则没有下一篇，返回数组大小为2
				list = baseDao.findPage(article, position-1, 2);
			}else{
				list = baseDao.findPage(article, position-1, 3);
			}
		}
		return list;
	}

}
