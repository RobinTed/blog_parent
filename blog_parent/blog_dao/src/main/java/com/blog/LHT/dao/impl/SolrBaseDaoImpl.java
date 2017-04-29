package com.blog.LHT.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.blog.LHT.dao.SolrBaseDao;
import com.blog.LHT.entity.ArticleSearch;
import com.blog.LHT.utils.Pager;

public class SolrBaseDaoImpl implements SolrBaseDao{

	private SolrServer solrServer;
	
	public void setSolrServer(SolrServer solrServer) {
		this.solrServer = solrServer;
	}

	@Override
	public Pager<ArticleSearch> queryArticle(SolrQuery solrQuery) throws SolrServerException {
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList documentList = queryResponse.getResults(); //返回的全部结果
		Pager<ArticleSearch>pager = new Pager<>();
		List<ArticleSearch>resultList = new ArrayList<>();
		
		if(documentList!=null){
			pager.setRecordCount(documentList.getNumFound());    //返回的结果总数
			for(SolrDocument document:documentList){
				ArticleSearch articleSearch = new ArticleSearch();
				articleSearch.setArticleId(Integer.valueOf(String.valueOf(document.getFieldValue("id"))));
				Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
				if(highlighting!=null){
					List<String> nameList = highlighting.get(document.get("id")).get("articleName");
					List<String>textList = highlighting.get(document.get("id")).get("articleText");
					if(nameList != null && nameList.size() > 0){
						articleSearch.setArticleName(nameList.get(0));
					} else {
						articleSearch.setArticleName(String.valueOf(document.get("articleName")));
					}
					if(textList!=null&&textList.size()>0){
						articleSearch.setArticleText(textList.get(0));
					}else{
						articleSearch.setArticleText(String.valueOf(document.get("articleText")));
					}
				}else {
					articleSearch.setArticleName(String.valueOf(document.get("articleName")));
					articleSearch.setArticleText(String.valueOf(document.get("articleText")));
				}
				if(document.get("articleView")!=null){
					articleSearch.setArticleView(Integer.valueOf(String.valueOf(document.get("articleView"))));
				}
				resultList.add(articleSearch);
			}
			pager.setCurrentRecord(resultList);     //赋值查询结果列表
		}
		
		return pager;
	}

}
