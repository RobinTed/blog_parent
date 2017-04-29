<%@page import="java.util.Date"%>
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
<title>注册</title>
<script type="text/javascript">
$(function(){
//	alert("1")
	$(".carousel").carousel({
		interval:2000
	})
})
 function getVerify(obj) {
		obj.src = "${prc }/login/login_verifyImg?s="+new Date().getTime();
	} 
$(function(){
		$("#trr").hide();
//	alert($("#register"))
		$("#username_msg").html("");
		$("#password_msg").html("");
		$("#inputcode").html("");
		$("#code_msg").html("");
	$("#register").validate({
		rules:{
			username:"required",
			password:"required",
			email:{
				required:true,
				email:true
			},
			reenter:{
				equalTo:"[name='password']"
			},
			inputcode:{
				required:true,
			}
		},
		messages:{
			username:"<font color='red'>用户名不能为空</font>",
			password:"<font color='red'>密码不能为空</font>",
			email:{
				required:"<font color='red'>邮箱不能为空</font>",
				email:"<font color='red'>请输入正确的邮箱</font>"
			},
			reenter:{
				equalTo:"<font color='red'>两次输入的密码不一致</font>"
			},
			inputcode:{
				required:"<font color='red'>验证码不能为空</font>",
			}
		}
	});
	
	/*异步校验用户名是否重复  */
	$("input[name='userName']").blur(function(){
		var $value = $(this).val();
		/*  alert($value)  */
		$.get("${prc}/login/login_checkName","userName="+$value,function(data){
			if(data==1){
				$("#username_msg").html("<font color='red'>用户名已被注册</font>");
				$("#registerBtn").attr('disabled',true)
			}else if(data==0){
				$("#username_msg").html("<font color='green'>用户名可用</font>")
				$("#registerBtn").attr('disabled',false)
			}else if(data==-1){
				$("#username_msg").html("<font color='red'>用户名不能为空</font>")
			}
		});
	});
		
	/*异步校验验证码是否正确  */
	$("#inputcode").blur(function(){
		var $value = $(this).val();
		/* alert($value); */
		$.get("${prc}/login/login_checkCode","inputcode="+$value,function(data){
			/* alert(data); */
			if(data==1){
				$("#trr").show();
				$("#verify_msg").html("<font color='green'>验证码正确</font>")
			}else{
				$("#trr").hide();
				$("#verify_msg").html("<font color='red'>验证码错误</font>")
			}
		})
	});
	})
</script>
</head>
<body>
<div class="row">
				<ul class="nav nav-pills navbar-inverse" style="padding-left: 150px;">
					<li class="active"><a href="${prc }/blog_mainPage">博客首页</a></li>
					<li ><a href="${prc }/user/user_mainPage">个人主页</a></li>
					<li ><a href="${prc }/login/login_loginPage">登录</a></li>
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
				  <form class="form-horizontal" id="register" action="${prc }/login/login_register.action" method="post">  
					<table width="470px" height="400px" class="pull-right" style="table-layout: auto;">
						<tr>
						<td align="center" colspan="5">
							<h2><span class="label label-primary">注册</span></h2>
						</td>
						</tr>
						<tr>
							<td>
								<div class="input-group">
								  <span class="input-group-addon">用户名：</span>
								  <input type="text" class="form-control" name="userName" placeholder="Username" >
								</div>
							</td>
							<td style="width: 100px;"><span id="username_msg"></span></td>
						</tr>
							<tr>
							<td>
								<div class="input-group">
								  <span class="input-group-addon">邮箱：</span>
								  <input type="email" name="email" class="form-control" placeholder="Email" >
								</div>
								<td style="width: 100px;"><span id="email_msg"></span></td>
							</td>
						</tr>
							<tr>
							<td>
								<div class="input-group">
								  <span class="input-group-addon">密码：</span>
								  <input type="password" name="password" class="form-control" placeholder="password">
								</div>
								<td style="width: 100px;"><span id="password_msg"></span></td>
							</td>
						</tr>
							<tr>
							<td>
								<div class="input-group">
								  <span class="input-group-addon">确认密码：</span>
								  <input type="password" name="reenter" class="form-control" placeholder="reenter">
								</div>
								<td style="width: 100px;"><span id="reentry_msg"></span></td>
							</td>
						</tr>
						  <tr>
							<td>
								<div class="input-group">
									<!-- <span class="input-group-addon">输入验证码</span> -->
									<%-- <input type="image" src="${prc }/login/login_verifyImg" onclick="getVerify(this)" title="点击更换" /> --%>
								<%-- 	<button class="btn btn-default">
										<img alt="" src="${prc }/login/login_verifyImg" onclick="getVerify(this)" title="点击更换">
										<div style="width:图片的宽度; height:图片的高度; background:url(图片的地址);"></div>
									</button> --%>
									<%-- <button style="width:100px;height:50px;background: url(${prc }/login/login_verifyImg)" onclick="getVerify(this)" title="点击更换" class="btn btn-default"></button>
									<input type="text" class="form-control" placeholder="验证码"  name="inputcode" id="inputcode"/> --%>
									<%-- <span style="color: red" id="code_msg">${wrongCode }</span> --%>
								</div>
							</td>
							<!-- <td style="width=100px"><span  id="verify_msg"></span></td> -->
						</tr>   
						<tr id="trr">
							<td align="center"><input type="submit" id="submitForm" class="btn btn-success" value="确认"/></td>
							
						</tr>
				</table>
				 </form>  
				 
				 	<div class="input-group" style="width: 400px;padding-left: 100px;padding-top: -10px" >
			<span class="input-group-addon" style="margin-left: 1000px">输入验证码</span>
				<button class="btn btn-default">
										<img alt="" src="${prc }/login/login_verifyImg" onclick="getVerify(this)" title="点击更换">
										<div style="width:图片的宽度; height:图片的高度; background:url(图片的地址);"></div>
									</button>
			<input type="text" class="form-control" placeholder="验证码"  name="inputcode" id="inputcode"/>
			<span  style="color: red" id="code_msg">${wrongCode }</span>
			<span  id="verify_msg"></span>
		</div>
				 
				<%--  <span class="input-group-addon">输入验证码</span>
				<input type="image" src="${prc }/login/login_verifyImg" onclick="getVerify(this)" title="点击更换" />
									<input type="text" class="form-control" placeholder="验证码"  name="inputcode" id="inputcode"/> --%>
				<%-- <a href="${prc }/login/login_loginPage">登录</a> --%>
			</div>			
		</div>
</body>
</html>