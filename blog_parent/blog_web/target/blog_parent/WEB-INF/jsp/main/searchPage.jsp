<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../base.jsp" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<script type="text/javascript" src="${prc }/js/jQuery/jquery-3.1.1.min.js" ></script>
<script type="text/javascript" src="${prc }/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${prc }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${prc }/css/user.css" />
<title>搜索页面</title>
</head>
<body>
<div class="container-fluid">
			<div class="row">
				<ul class="nav nav-pills navbar-inverse" style="padding-left: 150px;">
					<li class="active"><a href="${prc }/blog_mainPage">博客首页</a></li>
					<li ><a href="${prc }/user/user_mainPage">个人主页</a></li>
				</ul>
			</div>
		</div>
	<div class="container">
			<div class="row">
				    <form action="${prc }/search/search_searchResult">
				     <div class="col-sm-8">
				     	 <div class="form-group"  style="margin-top: 100px;">
				        <input type="text" class="form-control" placeholder="请输入搜索内容" name="searchContent">
				        	  <button type="submit" class="btn btn-default" title="点击搜索">
				      	<span class="glyphicon glyphicon-search"></span>
				      </button>
				      <span>
				      	<label class="label label-info">共搜索到：</label>
				      	<label class="label label-danger">${query.recordCount }</label>
				      	<label class="label label-info">条结果</label>
				      </span>
				      </div>
				     </div>
				    </form>
				<form action="">
					<div class="col-md-8">
					<c:forEach items="${query.currentRecord }" var="searchArticle">
						<div class="blog-post">
						<h2>${searchArticle.articleName }</h2>
						<!-- <h4>作者<a href="#">admin</a>日期:今天</h4> -->
						<p>
							${searchArticle.articleText }
						</p>
						<hr style="height:1px;border:none;border-top:1px solid #555555;" />
					</div>
					</c:forEach>
					<br />
					<!-- <nav>
						<ul class="pagination">
							<li><a>总共【#】页</a></li>
							<li><a>总共【#】条记录</a></li>
							<li><a>当前【#】页</a></li>
							<li><a>|</a></li>
							<li><a href="#">首页</a></li>
						  <li><a href="#" title="上一页">&laquo;</a></li>
						  <li><a href="#">&raquo;</a></li>
						  <li><a href="#">尾页</a></li>
						</ul>
					</nav> -->
				</div>
				</form>

				<!--中间空出来的一个-->
				<div class="col-md-1"></div>
				<div class="col-md-3" style="padding-top: 30px;">
				
				</div>
			</div>
		</div>
</body>
</html>