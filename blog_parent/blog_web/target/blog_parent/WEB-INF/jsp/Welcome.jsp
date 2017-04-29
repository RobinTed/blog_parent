 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Shortcut Icon" href="${imgPath }title.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<script type="text/javascript" src="${prc }/js/jQuery/jquery-3.1.1.min.js" ></script>
<script type="text/javascript" src="${prc }/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${prc }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${prc }/css/user.css" />
<script type="text/javascript">
$(function(){
	/* alert("${loginUser}"); */
	if("${loginUser}" == ""){
		$("#isLog").html('<a href="${prc }/login/login_loginPage">登录</a>');
		$("#operate").html('<a href="${prc}/login/login_registerPage">注册</a>')
	}else{
		$("#isLog").html('<a href="${prc }/login/login_logout">注销</a>');
		$("#operate").html('<a href="${prc}/user/user_mainPage">${loginUser.userName}</a>');
	};
	/* alert("${allArticlePager.currentPage}"); */
})
</script>
<title>HOME</title>
</head>
<body style="padding-top: 52px;">
		<div class="container">
			<div style="height: 10px;"></div>
		<!--头部信息-->
			<div class="row" >
				<div class="col-lg-12">
					<img src="${imgPath }1.jpg" width="80px" height="60px" alt="博客" class="img-circle" class="pull-left" />
				</div>
			</div>
		<!--导航条-->
			<div id="navBar" class="row" >
				<nav class="navbar navbar-default  navbar-fixed-top" role="navigation" >
				  <!-- Brand and toggle get grouped for better mobile display -->
				  <div class="navbar-header">
				    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				      <span class="sr-only">Toggle navigation</span>
				      <span class="icon-bar"></span>
				      <span class="icon-bar"></span>
				      <span class="icon-bar"></span>
				    </button>
				    <a class="navbar-brand" style="padding-left: 30px;" href="#">Brand</a>
				  </div>
				
				  <!-- Collect the nav links, forms, and other content for toggling -->
				  <div class="collapse navbar-collapse navbar-ex1-collapse">
				    <ul class="nav navbar-nav">
				      <li class="active"><a href="${prc }/blog_mainPage">首页</a></li>
				      <li><a href="${prc }/user/user_mainPage">我的博客</a></li>
<!--  暂时不做分类		
				      <li class="dropdown">
				        <a href="#" class="dropdown-toggle" data-toggle="dropdown">分类 <b class="caret"></b></a>
				        <ul class="dropdown-menu">
				          <li><a href="#">Action</a></li>
				          <li><a href="#">Another action</a></li>
				          <li><a href="#">Something else here</a></li>
				          <li class="divider"></li>
				          <li><a href="#">Separated link</a></li>
				          <li class="divider"></li>
				          <li><a href="#">One more separated link</a></li>
				        </ul>
				      </li> -->
				    </ul>
				    <!-- 搜索框 -->
				    <form class="navbar-form navbar-left" role="search" action="${prc }/search/search_searchResult">
				      <div class="form-group">
				        <input type="text" class="form-control" placeholder="请输入搜索内容" name="searchContent">
				      </div>
				      <button type="submit" class="btn btn-default">
				      	<span class="glyphicon glyphicon-search"></span>
				      </button>
				    </form>
				    <ul class="nav navbar-nav navbar-right">
				      <li id="isLog"></li>
				      <li id="operate" style="padding-right:20px">
				        <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
				        <ul class="dropdown-menu">
				          <li><a href="#">Action</a></li>
				          <li><a href="#">Another action</a></li>
				          <li><a href="#">Something else here</a></li>
				          <li class="divider"></li>
				          <li><a href="#">Separated link</a></li>
				        </ul> -->
				      </li>
				    </ul>
				  </div><!-- /.navbar-collapse -->
				</nav>
			</div>
			
						<div class="col-sm-6">
				<div class="row">
					<ul class="list-group">
						<li class="list-group-item"><h4 style="padding-left: 200px;"><label class="label label-danger">最热文章图片推荐</label>	</h4></li>
							<div id="carousel-example-generic" class="carousel slide pull-left " style="width: 100%;height: 400px;">
							  <!-- Indicators -->
							  <ol class="carousel-indicators">
							    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
							    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
							    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
							  </ol>
							
							  <!-- Wrapper for slides -->
							  <div class="carousel-inner">
							    <div class="item active">
							      <a href="${prc }/article/article_showArticle?articleId=${articleId[0]}">
							      	<img src="${imgURL[0] }" alt="..." style="width: 600px;height: 400px;">
							      </a>
							      <div class="carousel-caption">
							      	<!-- 风景图1 -->
							      </div>
							    </div>
								
								<div class="item">
							      <a href="${prc }/article/article_showArticle?articleId=${articleId[1]}">
							      	<img src="${imgURL[1] }" alt="..." style="width: 600px;height: 400px;">
							      </a>
							      <div class="carousel-caption">
							      	<!-- 风景图2 -->
							      </div>
							    </div>
									
								<div class="item">
							      <a href="${prc }/article/article_showArticle?articleId=${articleId[2]}">
							      	<img src="${imgURL[2] }" alt="..." style="width: 600px;height: 400px;">
							      </a>
							      <div class="carousel-caption">
							      	<!-- 风景图3 -->
							      </div>
							    </div>
							    
							  </div>
							
							  <!-- Controls -->
							  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
							    <span class="glyphicon glyphicon-chevron-left"></span>
							  </a>
							  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
							    <span class="glyphicon glyphicon-chevron-right"></span>
							  </a>
							</div>
					</ul>
				</div>
			</div>
			<hr>
			<!-- 文章列表 -->
			<form action="${prc }/blog_mainPage?currentPage=${allArticlePager.currentPage}">
					<div class="col-md-10">
				<%-- 		<c:forEach items="${allArticlePager.currentRecord }" var="article" varStatus="status">
							<div class="blog-post">
								<h2>${article.articleName }</h2>
								<h4>作者<a href="${prc }/article/article_listArticle?userId=${users[status.index].userId}">${users[status.index].userName }</a>日期:${article.updateTime }</h4>
							</div>	
						</c:forEach> --%>
					<!-- 文章列表 -->
					<ul class="list-group" >
						<c:forEach items="${allArticlePager.currentRecord }" var="article" varStatus="status" >
							<li class="list-group-item">
								<img style="width: 60px;height: 60px;" class="img-circle" src="${imgPath }${users[status.index].headImgPath }" />
								<a href="${prc }/article/article_listArticle?userId=${users[status.index].userId}">${users[status.index].userName }</a>
								<span style="margin-left: 40px;padding-top: 5px;">${article.articleName }</span>
								<br />
								<span style="padding-left: 500px;">
									<label class="label label-danger">浏览量:</label>${article.articleView }
									<label class="label label-primary" style="margin-left: 10px;">时间：</label>${article.updateTime }
								</span>
						</li>
						</c:forEach>
					</ul>
					<!-- 文章列表结束 -->
					<br />
					<nav>
						<ul class="pagination">
							<li><a>总共【${allArticlePager.pageCount }】页</a></li>
							<li><a>总共【${allArticlePager.recordCount }】条记录</a></li>
							<li><a>当前【${allArticlePager.currentPage }】页</a></li>
							<li><a>|</a></li>
							<li><a href="${prc }/blog_mainPage?currentPage=1">首页</a></li>
							<c:choose>
						  	<c:when test="${allArticlePager.currentPage>1}">
						  		<li><a href="${prc }/blog_mainPage?currentPage=${allArticlePager.currentPage-1}" title="上一页">&laquo;</a></li>
						  	</c:when>
						  	<c:otherwise>
						  		<li><a href="${prc }/blog_mainPage?currentPage=1" title="上一页">&laquo;</a></li>
						  	</c:otherwise>
						  </c:choose>
						  
						  <c:choose>
								<c:when test="${allArticlePager.currentPage<allArticlePager.pageCount }">
								
									<li><a href="${prc }/blog_mainPage?currentPage=${allArticlePager.currentPage+1}"  title="下一页">&raquo;</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${prc }/blog_mainPage?currentPage=${allArticlePager.pageCount}"  title="下一页">&raquo;</a></li>
								</c:otherwise>
							</c:choose>
						  <li><a href="${prc }/blog_mainPage?currentPage=${allArticlePager.pageCount}">尾页</a></li>
						</ul>
					</nav>
				</div>
				</form>
			<!-- 文章列表结束 -->
		</div>
	</body>
</html>