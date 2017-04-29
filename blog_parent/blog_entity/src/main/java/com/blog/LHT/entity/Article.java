package com.blog.LHT.entity;

import java.sql.Timestamp;

public class Article {

	private Integer articleId;
	private String articleName;
	private Timestamp updateTime;
	private Integer categoryId;
	private Integer userId;
	private Integer showDefault;
	private String articleText;
	private Integer stick;
	private Integer articleView;
	private Integer agree;
	private Integer disagree;
	public Integer getAgree() {
		return agree;
	}
	public void setAgree(Integer agree) {
		this.agree = agree;
	}
	public Integer getDisagree() {
		return disagree;
	}
	public void setDisagree(Integer disagree) {
		this.disagree = disagree;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getShowDefault() {
		return showDefault;
	}
	public void setShowDefault(Integer showDefault) {
		this.showDefault = showDefault;
	}
	public String getArticleText() {
		return articleText;
	}
	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}
	public Integer getStick() {
		return stick;
	}
	public void setStick(Integer stick) {
		this.stick = stick;
	}
	public Integer getArticleView() {
		return articleView;
	}
	public void setArticleView(Integer articleView) {
		this.articleView = articleView;
	}
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articleName=" + articleName + ", updateTime=" + updateTime
				+ ", categoryId=" + categoryId + ", userId=" + userId + ", showDefault=" + showDefault
				+ ", articleText=" + articleText + ", stick=" + stick + ", articleView=" + articleView + ", agree="
				+ agree + ", disagree=" + disagree + "]";
	}
	
	
}
