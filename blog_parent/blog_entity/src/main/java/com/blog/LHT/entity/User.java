package com.blog.LHT.entity;

import java.sql.Timestamp;
import java.util.Date;

public class User {

	private Integer userId;
	private String userName;
	private String password;
	private String sex;
	private String phoneNum;
	private String QQ;
	private String email;
	private String address;
	private Date birth;
	private String intro;
	private Timestamp regiTime;
	private Integer permiss;
	private Date lastUpdateTime;
	private String readHistory;
	private String headImgPath;
	private String blogName;
	private String blogIntro;
	private Integer pageView;
	private String bgImgPath;
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogIntro() {
		return blogIntro;
	}
	public void setBlogIntro(String blogIntro) {
		this.blogIntro = blogIntro;
	}
	public Integer getPageView() {
		return pageView;
	}
	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}
	public String getBgImgPath() {
		return bgImgPath;
	}
	public void setBgImgPath(String bgImgPath) {
		this.bgImgPath = bgImgPath;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public Timestamp getRegiTime() {
		return regiTime;
	}
	public void setRegiTime(Timestamp regiTime) {
		this.regiTime = regiTime;
	}
	public Integer getPermiss() {
		return permiss;
	}
	public void setPermiss(Integer permiss) {
		this.permiss = permiss;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getReadHistory() {
		return readHistory;
	}
	public void setReadHistory(String readHistory) {
		this.readHistory = readHistory;
	}
	
	public String getHeadImgPath() {
		return headImgPath;
	}
	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", sex=" + sex
				+ ", phoneNum=" + phoneNum + ", QQ=" + QQ + ", email=" + email + ", address=" + address + ", birth="
				+ birth + ", intro=" + intro + ", regiTime=" + regiTime + ", permiss=" + permiss + ", lastUpdateTime="
				+ lastUpdateTime + ", readHistory=" + readHistory + ", headImgPath=" + headImgPath + ", blogName="
				+ blogName + ", blogIntro=" + blogIntro + ", pageView=" + pageView + ", bgImgPath=" + bgImgPath + "]";
	}
	
}
