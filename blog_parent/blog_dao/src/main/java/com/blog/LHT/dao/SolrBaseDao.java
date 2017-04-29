package com.blog.LHT.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.blog.LHT.entity.ArticleSearch;
import com.blog.LHT.utils.Pager;

public interface SolrBaseDao {

	public Pager<ArticleSearch>queryArticle(SolrQuery solrQuery) throws SolrServerException;
	
}
