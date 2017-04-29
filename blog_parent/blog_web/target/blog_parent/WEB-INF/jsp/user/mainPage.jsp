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
<title>个人主页</title>
<script type="text/javascript">
	$(function(){
		setInterval(showDate,900);
	});
	function showDate(){
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = myDate.getMonth()+1;
		var day = myDate.getDate();
		var hour = myDate.getHours();
		var minute = myDate.getMinutes();
		var second = myDate.getSeconds();
		var now = year+"年"+p(month)+"月"+p(day)+"日--"+p(hour)+":"+p(minute)+":"+p(second);
		$("#currentTime").html("<span>"+now+"</span>");
	}
	
	function p(s) {
		return s < 10 ? '0' + s: s;
	}
</script>
</head>
<body >
		<!--头部开始-->
		<div id="header" style="background-image: url('${loginUser.bgImgPath}'); background-position-x: center;background-position-y: center;background-size: 100% 100%;background-repeat: no-repeat;">
			<div class="overlay">
				<div class="row">
					<div class="col-md-4 logo-div">
						<div class="log-inner text-center">
							<div class="logo-name">
								<a href="${prc }/user/user_mainPage">
									<img src="${loginUser.headImgPath }" class="img-circle" />
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-8 header-text-top" id="about">
						<h1>${loginUser.blogName }的博客</h1>
						${loginUser.blogIntro }<br />
						<h2><strong>自我介绍</strong></h2>
						<i>${loginUser.intro }</i>
					</div>
				</div>
			</div>
		</div>
		<!--头部结束-->
		<!-- 导航栏开始 -->
		<nav class="navbar navbar-default " role="navigation">
			  <!-- Brand and toggle get grouped for better mobile display -->
			  <div class="navbar-header">
			    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			      <span class="sr-only">Toggle navigation</span>
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			    </button>
			    <a class="navbar-brand" href="${prc }/blog_mainPage" style="font-family: '微软雅黑';">博客首页</a>
			  </div>
			
			  <!-- Collect the nav links, forms, and other content for toggling -->
			  <div class="collapse navbar-collapse navbar-ex1-collapse">
			    <ul class="nav navbar-nav">
			    <li><a href="${prc }/article/article_listArticle?userId=${loginUser.userId}">我的文章</a></li>
			    <!-- 搜索 -->
			    <form class="navbar-form navbar-left" role="search" action="${prc }/search/search_searchResult">
				      <div class="form-group">
				        <input type="text" class="form-control" placeholder="请输入搜索内容"  name="searchContent">
				      </div>
				      <button type="submit" class="btn btn-default">
				      	<span class="glyphicon glyphicon-search"></span>
				      </button>
				    </form>
			   <ul class="nav navbar-nav navbar-right  " style="padding-left: 800px;"> 
			      <li class="pull-right"><a href="${prc }/login/login_logout" >注销</a></li>
			   </ul>
			  </div><!-- /.navbar-collapse -->
			</nav>
		<!-- 导航栏结束 -->
		<!--开始正文-->
		<div class="container">
			<div class="row">
			<form action="${prc }/use/user_mainPage?currentPage=${pager.currentPage}">
				<div class="col-md-8">
				<!-- 循环开始 -->
				<c:forEach items="${pager.currentRecord }" var="article">
					<div class="blog-post">
						<h2>${article.articleName }</h2>
						<h4>作者<a href="${prc }/article/article_listArticle?userId=${loginUser.userId}">${loginUser.userName }</a>&nbsp;浏览量:${article.articleView}&nbsp;日期:${article.updateTime }</h4>
						<p>
							${article.articleText }
						</p>
						<a href="${prc }/article/article_showArticle?articleId=${article.articleId}" class="btn btn-default btn-lg">详情</a>
					</div>
				</c:forEach>
				<!-- 循环结束 -->
		<%-- 			<div class="blog-post">
						<h2>范例文章</h2>
						<h4>作者<a href="#">${loginUser.userName }</a>日期:今天</h4>
						<p>
							啦啦啦啦啦啦啦啦啦啦啦啦啦
							啦啦啦啦啦啦啦啦啦啦绿绿绿绿绿。
							啦啦啦啦啦啦啦啦啦啦啦啦啦
							啦啦啦啦啦啦啦啦啦啦绿绿绿绿绿
						</p>
						<a href="#" class="btn btn-default btn-lg">详情</a>
					</div>
					<div class="blog-post">
						<h2>范例文章</h2>
						<h4>作者<a href="#">admin</a>日期:今天</h4>
						<p>
							啦啦啦啦啦啦啦啦啦啦啦啦啦
							啦啦啦啦啦啦啦啦啦啦绿绿绿绿绿。
							啦啦啦啦啦啦啦啦啦啦啦啦啦
							啦啦啦啦啦啦啦啦啦啦绿绿绿绿绿
						</p>
						<a href="#" class="btn btn-default btn-lg">详情</a>
					</div>
					<div class="blog-post">
						<h2>范例文章</h2>
						<h4>作者<a href="#">admin</a>日期:今天</h4>
						<p>
							啦啦啦啦啦啦啦啦啦啦啦啦啦
							啦啦啦啦啦啦啦啦啦啦绿绿绿绿绿。
							啦啦啦啦啦啦啦啦啦啦啦啦啦
							啦啦啦啦啦啦啦啦啦啦绿绿绿绿绿
						</p>
						<a href="#" class="btn btn-default btn-lg">详情</a>
					</div> --%>
					<br />
					<nav>
						<ul class="pagination">
						<li><a>总共【${pager.pageCount }】页</a></li>
							<li><a>总共【${pager.recordCount }】条记录</a></li>
							<li><a>当前【${pager.currentPage }】页</a></li>
							<li><a>|</a></li>
						<li><a href="${prc }/user/user_mainPage?currentPage=1">首页</a></li>
						  <%-- <c:if test="${pager.currentPage>1 }">
						  	<li><a href="${prc }/user/user_mainPage?currentPage=${pager.currentPage-1 }" title="上一页">&laquo;</a></li>
						  </c:if> --%>
						  <c:choose>
						  	<c:when test="${pager.currentPage>1}">
						  		<li><a href="${prc }/user/user_mainPage?currentPage=${pager.currentPage-1 }" title="上一页">&laquo;</a></li>
						  	</c:when>
						  	<c:otherwise>
						  		<li><a href="${prc }/user/user_mainPage?currentPage=1" title="上一页">&laquo;</a></li>
						  	</c:otherwise>
						  </c:choose>
						 <!--  <li><a href="#">1</a></li>
						  <li><a href="#">2</a></li>
						  <li><a href="#">3</a></li>
						  <li><a href="#">4</a></li>
						  <li><a href="#">5</a></li> -->
						  <%-- <c:if test="${pager.currentPage<pager.pageCount }">
						  		<li><a href="${prc }/user/user_mainPage?currentPage=${pager.currentPage+1}"  title="下一页">&raquo;</a></li>
						  </c:if> --%>
							<c:choose>
								<c:when test="${pager.currentPage<pager.pageCount }">
									<li><a href="${prc }/user/user_mainPage?currentPage=${pager.currentPage+1}"  title="下一页">&raquo;</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${prc }/user/user_mainPage?currentPage=${pager.pageCount}"  title="下一页">&raquo;</a></li>
								</c:otherwise>
							</c:choose>
						  <li><a href="${prc }/user/user_mainPage?currentPage=${pager.pageCount}">尾页</a></li>
						</ul>
					</nav>
				</div>
				</form>
				<!--中间空出来的一个-->
				<div class="col-md-1"></div>
				<!--开始右边的导航部分-->
				<div class="col-md-3" style="padding-top: 30px;">
					<div class="row">
						<ul class="list-group">
                    <li class="list-group-item"><strong>${loginUser.userName }的栏目</strong></li>
                    <li class="list-group-item" ">
                    </li>
                    <li class="list-group-item"><a href="${prc }/user/user_writePage">写博客</a></li>
                    <li class="list-group-item"><a href="${prc }/user/user_modifyPage">修改个人信息</a></li>
<!--                     <li class="list-group-item">关注人数：<a href="#">122</a></li>
                    <li class="list-group-item">粉丝数：<a href="#">122</a></li>
 -->                </ul>
 					<ul class="list-group">
 					<li class="list-group-item"><strong>当前时间</strong></li>
 						<li class="list-group-item" id="currentTime"></li>
 					</ul>
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