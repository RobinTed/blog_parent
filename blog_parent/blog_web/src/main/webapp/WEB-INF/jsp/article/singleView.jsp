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
<script type="text/javascript" src="${prc }/bootstrap/js/bootstrap.min.js" ></script>
<link rel="stylesheet" href="${prc }/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${prc }/css/user.css" />
<title>单个文章</title>
</head>
<body>
<div class="container-fluid">
			<div class="row">
				<ul class="nav nav-pills navbar-inverse" style="padding-left: 150px;">
					<li class="active"><a href="${prc }/blog_mainPage">博客首页</a></li>
					<li ><a href="${prc }/user/user_mainPage">个人主页</a></li>
					<li><a href="${prc }/article/article_listArticle?userId=${articleUser.userId}">文章列表</a></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="row">
						<ul class="list-group" style="padding-top: 30px;">
							<li class="list-group-item"><strong>${articleUser.userName }的个人信息</strong></li>
							<li class="list-group-item">
								<img src="${articleUser.headImgPath }" class="img-circle" style="width: 100px;height: 100px;"/>
								<a href="${prc }/user/user_mainPage">${articleUser.userName }</a>
							</li>
							<li class="list-group-item">
								<h4><label class="label label-success">性别：${articleUser.sex }</label></h4>
								<h4><label class="label label-warning">邮箱：${articleUser.email }</label></h4>
							</li>
							<li class="list-group-item">
								<h4><label class="label label-info">注册时间:${articleUser.regiTime }</label></h4>
								<h4><label class="label label-primary">发博量：${totalCount }</label></h4>
							</li>
							<li class="list-group-item"><strong>最新动态</strong></li>
							<li class="list-group-item">
								<!-- <a href="#">spring技术讲解</a> -->
								<c:forEach items="${newArticle }" var="article">
									<font style="color:red">*</font>  ${article.articleName } &nbsp;<br>
								</c:forEach>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-8">
					<div class="row">
						<ul class="list-group" style="margin-top: 30px;">
							<li class="list-group-item">
								<h4><label class="label label-success">文章标题:</label>${currentArticle.articleName }</h4>
							</li>
							<%-- <li class="list-group-item">
								<label class="label label-default">文章信息</label><br />
								最后更新时间：${currentArticle.updateTime }  &nbsp;&nbsp;浏览量：${currentArticle.articleView }  &nbsp;<label class="label label-warning">未经博主同意，不得转载</label>
							</li> --%>
							<li class="list-group-item">
								<h4><label class="label label-danger">文章内容</label><br /></h4>
									${currentArticle.articleText }	
							</li>
							<li class="list-group-item">
								<label class="label label-default">文章信息</label><br />
								最后更新时间：${currentArticle.updateTime }  &nbsp;&nbsp;浏览量：${currentArticle.articleView }  &nbsp;<label class="label label-warning">未经博主同意，不得转载</label>
								<!-- <h3 style="padding-left: 350px;"><label class="label label-success"><a href="#"><font style="color: white;">回复</font></a></label></h3> -->
							</li>
							<li class="list-group-item">
 								<h4><label class="label label-default"><font style="color: white;">^上一篇:</font></label>${preArticle.articleName }</h4>
								<h4><label class="label label-default"><font style="color: white;">v下一篇:</font></label>${nextArticle.articleName }</h4>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
</body>
</html>