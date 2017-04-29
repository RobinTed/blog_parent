package com.blog.LHT.service.solr;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.LHT.entity.ArticleSearch;
import com.blog.LHT.service.SearchService;
import com.blog.LHT.utils.Pager;

public class SolrTest {

	private SearchService searchService;
	
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		searchService = (SearchService) context.getBean("searchServiceImpl");
	}
	
	@Test
	public void testSelect() throws SolrServerException{
		SolrServer solrServer = new HttpSolrServer("http://share.wicp.io/solr");
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("df", "article_keywords");//默认搜索域
		solrQuery.setQuery("article_keywords:他");      //设置搜索语句
		solrQuery.addSort("articleView", ORDER.desc);   //按照浏览量倒序排
		solrQuery.setStart(0);
		solrQuery.setRows(10);
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("articleName OR articleText");
		solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</span>");
		
		QueryResponse queryResponse = solrServer.query(solrQuery);  //将异常抛出去
		SolrDocumentList documentList = queryResponse.getResults();
		System.out.println(documentList.getNumFound());
		for(SolrDocument document:documentList){
			System.out.println(document.get("id"));
			String articleName = (String) document.getFieldValue("articleName");
			String articleText = (String) document.getFieldValue("articleText");
			System.out.println(articleName);
			System.out.println(articleText);
			System.out.println("========================");
			//获得高亮
			Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
			Map<String, List<String>> map = highlighting.get(document.get("id"));
			List<String> nameHigh = map.get("articleName");
			List<String> textHigh = map.get("articleText");
			if(nameHigh!=null){
				System.out.println(nameHigh.get(0));
			}
			System.out.println("=================");
			if(textHigh!=null){
				System.out.println(textHigh.get(0));
			}
		}
		
	}
	
	@Test
	public void testSearch(){
		String queryString = "article_keywords:他";
		try {
			Pager<ArticleSearch> pager = searchService.query(queryString, 1);
			System.out.println(pager.getRecordCount());
//			System.out.println(pager.getCurrentRecord());
			for(ArticleSearch articleSearch:pager.getCurrentRecord()){
				System.out.println(articleSearch.toString());
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
}
