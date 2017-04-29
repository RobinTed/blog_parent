<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<script type="text/javascript" src="${prc }/js/jQuery/jquery-3.1.1.min.js" ></script>
<script type="text/javascript" src="${prc }/js/jquery.validate.js"></script>
<script type="text/javascript" src="${prc }/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${prc }/bootstrap/css/bootstrap.min.css">
<title>登录</title>
<script type="text/javascript">
$(function(){
//	alert("1")
	$(".carousel").carousel({
		interval:2000
	})
})
$(function(){
//	alert($("#register"))
		$("#username_msg").html("");
		$("#password_msg").html("");
	$("#login").validate({
		rules:{
			username:"required",
			password:"required",
		},
		messages:{
			username:"<font color='red'>用户名不能为空</font>",
			password:"<font color='red'>密码不能为空</font>",
		}
	});
	
	/* 异步校验用户名和密码 */
	$("#confirmBtn").click(function(){
		var $userName=$("#userName").val();
		var $password = $("#password").val();
 	 	/* alert($userName)  */ 
		$.get("${prc}/login/login_login.action","userName="+$userName+"&password="+$password,function(data){
			if(data.status==1){
				/* alert(data); */
				$("#myBtn").click();
				$("#myModalLabel").html("<font style='color:green'>登陆成功</font>");
				countTime(8);
			}else if(data.status == 0){
				$("#myBtn").click();
				$("#myModalLabel").html("<font style='color:#DAA520'>账户尚未激活</font>");
				$("#login_msg").html("您的账户尚未激活，请联系管理员激活，或者重新注册");
			}else{
				$("#myBtn").click();
				$("#myModalLabel").html("<font style='color:red'>登陆错误</font>");
				$("#login_msg").html("您输入的用户名或者密码错误");
			}
		},"json")
	});
	
	});
	
	function countTime(time){
				setInterval(function(){
					if(time>0){
						time--;
						$("#login_msg").html(time+"秒后进入<a href='${prc}/user/user_mainPage'>个人主页</a>");
					}else{
						window.location.href="${prc}/user/user_mainPage?currentPage=1"
					}
				},1000)
			}
</script>
</head>
<body>
<div class="row">
				<ul class="nav nav-pills navbar-inverse" style="padding-left: 150px;">
					<li class="active"><a href="${prc }/blog_mainPage">博客首页</a></li>
					<li ><a href="${prc }/user/user_mainPage">个人主页</a></li>
					<li ><a href="${prc }/login/login_registerPage">注册</a></li>
				</ul>
			</div>
	<div class="container">
			<div style="height: 50px;"></div>
			<div class="row">
				<div id="carousel-example-generic" class="carousel slide pull-left " style="width: 600px;">
				  <!-- Indicators -->
				  <ol class="carousel-indicators">
				    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
				  </ol>
				
				  <!-- Wrapper for slides -->
				  <div class="carousel-inner">
				    <div class="item active">
				      <img src="${prc }/img/风景图1.jpg" alt="..." style="width: 600px;height: 550px;">
				      <div class="carousel-caption">
				      	风景图1
				      </div>
				    </div>
					
					<div class="item">
				      <img src="${prc }/img/风景图2.jpg" alt="..." style="width: 600px;height: 550px;">
				      <div class="carousel-caption">
				      	风景图2
				      </div>
				    </div>
						
					<div class="item">
				      <img src="${prc }/img/风景图3.jpg" alt="..." style="width: 600px;height: 550px;">
				      <div class="carousel-caption">
				      	风景图3
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
				<%-- <form class="form-horizontal" id="login" action="${prc }/login/login_login.action" method="post"> --%>
					<table width="470px" height="400px" class="pull-right" style="table-layout: auto;">
						<tr>
						<td align="center" colspan="5">
							<h2><span class="label label-primary">登录</span></h2>
						</td>
						</tr>
						<tr>
							<td>
								<div class="input-group">
								  <span class="input-group-addon">用户名：</span>
								  <input type="text" class="form-control" name="userName" placeholder="Username" id="userName">
								</div>
							</td>
							<td style="width: 100px;"><span id="username_msg"></span></td>
						</tr>
							<tr>
							<td>
								<div class="input-group">
								  <span class="input-group-addon">密码：</span>
								  <input type="password" name="password" class="form-control" placeholder="password" id="password">
								</div>
								<td style="width: 100px;"><span id="password_msg"></span></td>
							</td>
						</tr>
						<tr>
							<td align="center">
								<!-- <input type="submit" class="btn btn-success" value="确认"/> -->
								<button class="btn btn-success" id="confirmBtn">确认</button>
							</td>
						</tr>
				</table>
				<!-- </form> -->
				<%-- <a href="${prc }/login/login_registerPage">注册</a> --%>
			
			<!-- 模态窗口部分 -->
					<input type="hidden" class="btn btn-success" data-toggle="modal" data-target="#myModal" id="myBtn"/>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
						</h4>
					</div>
					<div class="modal-body">
						提示信息:<br />
						<span id="login_msg"></span>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
					</div>
				</div><!-- /.modal-content -->
	</div>
		</div>
			
			</div>			
		</div>
</body>
</html>