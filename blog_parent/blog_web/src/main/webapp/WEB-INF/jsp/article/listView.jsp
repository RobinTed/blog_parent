<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<script type="text/javascript" src="${prc }/js/jQuery/jquery-3.1.1.min.js" ></script>
<script type="text/javascript" src="${prc }/bootstrap/js/bootstrap.min.js" ></script>
<link rel="stylesheet" href="${prc }/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${prc }/css/user.css" />
<title>当前用户所有文章列表</title>
</head>
<body>
<div class="container-fluid">
			<div class="row">
				<ul class="nav nav-pills navbar-inverse" style="padding-left: 150px;">
					<li class="active"><a href="${prc }/blog_mainPage">博客首页</a></li>
					<li ><a href="${prc }/user/user_mainPage">个人主页</a></li>
					<li><a href="${prc }/article/article_listArticle?userId=${user.userId}">文章列表</a></li>
				</ul>
			</div>
		</div>
		
		<!--开始正文-->
		<div class="container">
			<div class="row">
			<ul class="list-group" class="col-sm-8">
					<li class="list-group-item">
						<img class="img-circle" style="width: 100px;height: 100px;" src="${user.headImgPath }" />
						<a>${user.userName }</a>
					</li>
				</ul>
				<form action="${prc }/article/article_listArticle?currentPage=${userArticlePager.currentPage}&userId=${user.userId}">
					<div class="col-md-8">
						<c:forEach items="${userArticlePager.currentRecord }" var="article">
							<div class="blog-post">
								<h2>${article.articleName }</h2>
								<h4>作者<a href="${prc }/article/article_listArticle?userId=${user.userId}">${user.userName }</a>日期:${article.updateTime }</h4>
							</div>	
						</c:forEach>
					
	<!--				<div class="blog-post">
 						<h2>范例文章</h2>
						<h4>作者<a href="#">admin</a>日期:今天</h4>
					</div>
					<div class="blog-post">
						<h2>范例文章</h2>
						<h4>作者<a href="#">admin</a>日期:今天</h4>
					</div> -->
					<br />
					<nav>
						<ul class="pagination">
							<li><a>总共【${userArticlePager.pageCount }】页</a></li>
							<li><a>总共【${userArticlePager.recordCount }】条记录</a></li>
							<li><a>当前【${userArticlePager.currentPage }】页</a></li>
							<li><a>|</a></li>
							<li><a href="${prc }/article/article_listArticle?currentPage=1&userId=${user.userId}">首页</a></li>
							<c:choose>
						  	<c:when test="${userArticlePager.currentPage>1}">
						  		<li><a href="${prc }/article/article_listArticle?currentPage=${userArticlePager.currentPage-1 }&userId=${user.userId}" title="上一页">&laquo;</a></li>
						  	</c:when>
						  	<c:otherwise>
						  		<li><a href="${prc }/article/article_listArticle?currentPage=1&userId=${user.userId}" title="上一页">&laquo;</a></li>
						  	</c:otherwise>
						  </c:choose>
						  <c:choose>
								<c:when test="${userArticlePager.currentPage<pager.pageCount }">
									<li><a href="${prc }/article/article_listArticle?currentPage=${userArticlePager.currentPage+1}&userId=${user.userId}"  title="下一页">&raquo;</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${prc }/article/article_listArticle?currentPage=${userArticlePager.pageCount}&userId=${user.userId}"  title="下一页">&raquo;</a></li>
								</c:otherwise>
							</c:choose>
						  <!-- <li><a href="#" title="上一页">&laquo;</a></li> -->
						  <!--<li><a href="#">1</a></li>
						  <li><a href="#">2</a></li>
						  <li><a href="#">3</a></li>
						  <li><a href="#">4</a></li>
						  <li><a href="#">5</a></li>-->
						  <!-- <li><a href="#">&raquo;</a></li> -->
						  <li><a href="${prc }/article/article_listArticle?currentPage=${userArticlePager.pageCount}&userId=${user.userId}">尾页</a></li>
						</ul>
					</nav>
				</div>
				</form>

				<!--中间空出来的一个-->
				<div class="col-md-1"></div>
				<!--开始右边的导航部分-->
				<div class="col-md-3" style="padding-top: 30px;">
					<div class="row">
					</div>
				</div>
			</div>
		</div>
		<!--页脚-->
		<div class="footer-sec" style="margin-top: 0px;">
			<div class="container">
				<div class="row">
					
					<div class="col-md-12 footer-inner">
						&copy;2016-2017       版权所有，翻版必究
					</div>
				</div>
			</div>
		</div>
</body>
</html>