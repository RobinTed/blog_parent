package com.blog.LHT.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;

import com.blog.LHT.dao.SolrBaseDao;
import com.blog.LHT.entity.ArticleSearch;
import com.blog.LHT.service.SearchService;
import com.blog.LHT.utils.Pager;

public class SearchServiceImpl implements SearchService{

	private SolrBaseDao solrBaseDao;
	
	public void setSolrBaseDao(SolrBaseDao solrBaseDao) {
		this.solrBaseDao = solrBaseDao;
	}

	@Override
	public Pager<ArticleSearch> query(String queryString,Integer currentPage) throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("df", "article_keywords");//默认搜索域
		if(queryString!=null&&!"".equals(queryString.trim())){
			solrQuery.setQuery(queryString);      //设置搜索语句
		}else{
			solrQuery.setQuery("*:*");
		}
		solrQuery.addSort("articleView", ORDER.desc);   //按照浏览量倒序排
		if(currentPage == null ||currentPage<1){
			currentPage = 1;
		}
		int pageSize = 10;
		Integer start = (currentPage - 1)*pageSize;
		solrQuery.setStart(start);
		solrQuery.setRows(pageSize);  //每页显示10条
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("articleName OR articleText");
		solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</span>");
		Pager<ArticleSearch> pager = solrBaseDao.queryArticle(solrQuery);  //将异常抛出去
		Long pageCount = 0L;
		if(pager.getRecordCount()%pageSize == 0){
			pageCount = pager.getRecordCount()/pageSize;
		}else{
			pageCount = pager.getRecordCount()/pageSize+1;
		}
		pager.setCurrentPage(currentPage);
		pager.setPageCount(pageCount);
		return pager;
	}

}
