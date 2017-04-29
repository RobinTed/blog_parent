package com.blog.LHT.service;

import org.apache.solr.client.solrj.SolrServerException;

import com.blog.LHT.entity.ArticleSearch;
import com.blog.LHT.utils.Pager;

public interface SearchService {

	public Pager<ArticleSearch>query(String queryString,Integer currentPage) throws SolrServerException;
	
}
