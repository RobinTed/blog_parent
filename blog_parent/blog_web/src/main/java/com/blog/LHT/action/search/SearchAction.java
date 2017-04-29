package com.blog.LHT.action.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.struts2.ServletActionContext;

import com.blog.LHT.entity.ArticleSearch;
import com.blog.LHT.service.SearchService;
import com.blog.LHT.utils.ClearHTMLTag;
import com.blog.LHT.utils.Pager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SearchAction extends ActionSupport implements ModelDriven<Pager<ArticleSearch>>{

	private static final long serialVersionUID = 1L;
	private Pager<ArticleSearch>pager = new Pager<>();

	private SearchService searchService;
	private String searchContent;
	
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	//搜索页面的分页
	public String searchPage(){
		return "searchPage";
	}
	
	//搜索结果
	public String searchResult(){
		try {
			String queryString = "";
			if(searchContent == null||searchContent.trim().equals("")){
				queryString = "*:*";
			}else{
				queryString = "article_keywords:"+searchContent;
			}
			Pager<ArticleSearch> query = searchService.query(queryString, pager.getCurrentPage());
			//摘要过滤
//			for(int i = 0;i<query.getCurrentRecord().size();i++){
//				ArticleSearch article = query.getCurrentRecord().get(i);
//				String htmlStr = ClearHTMLTag.getTextHTML(article.getArticleText());
//				String subStr = "";
//				if(htmlStr.length()<=200){
//					subStr = htmlStr;
//				}else{
//					subStr = htmlStr.substring(0, 201);
//				}
//				query.getCurrentRecord().get(i).setArticleText(subStr);
//			}
			ServletActionContext.getRequest().setAttribute("query", query);
//			ServletActionContext.getRequest().setAttribute("searchResult", query.getCurrentRecord());
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return "searchPage";
	}
	
	//移动端接口
	public String mSearchResult(){
		try {
			String queryString = "";
			if(searchContent == null||searchContent.trim().equals("")){
				queryString = "*:*";
			}else{
				queryString = "article_keywords:"+searchContent;
			}
			Pager<ArticleSearch> query = searchService.query(queryString, pager.getCurrentPage());
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				//解决中文乱码
				response.setContentType("text/json");
				response.setCharacterEncoding("UTF-8");
				List<ArticleSearch> currentRecord = query.getCurrentRecord();
				List<Map<String, String>>responseData = new ArrayList<>();
				if(currentRecord == null || currentRecord.size()<=0){
					response.getWriter().println("");
				}else{
					for(ArticleSearch articleSearch:currentRecord){
						String name = ClearHTMLTag.getTextHTML(articleSearch.getArticleName());
						Map<String,String>map = new HashMap<>();
//						map.put(articleSearch.getArticleId().toString(), name);
						map.put("id", articleSearch.getArticleId().toString());
						map.put("title", name);
						responseData.add(map);
					}
					JSONArray jsonArray = JSONArray.fromObject(responseData);
					response.getWriter().println(jsonArray);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	@Override
	public Pager<ArticleSearch> getModel() {
		return pager;
	}

}
