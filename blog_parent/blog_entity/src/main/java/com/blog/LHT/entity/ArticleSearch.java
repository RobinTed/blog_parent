package com.blog.LHT.entity;

public class ArticleSearch {

	private Integer articleId;
	private Integer articleView;
	private String articleName;
	private String articleText;
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getArticleView() {
		return articleView;
	}
	public void setArticleView(Integer articleView) {
		this.articleView = articleView;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getArticleText() {
		return articleText;
	}
	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}
	@Override
	public String toString() {
		return "ArticleSearch [articleId=" + articleId + ", articleView=" + articleView + ", articleName=" + articleName
				+ ", articleText=" + articleText + "]";
	}

	
	
}
