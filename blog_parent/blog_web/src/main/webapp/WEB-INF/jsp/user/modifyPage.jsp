<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="../base.jsp" %>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户信息修改页面</title>
		<meta name="viewport" content="width=device-width,initial-scale=1" />
    	<link rel="stylesheet" href="${prc }/bootstrap/css/bootstrap.min.css" media="screen"/>
    	<link rel="stylesheet" href="${prc }/css/bootstrap-datetimepicker.min.css" media="screen"/>
    	<script type="text/javascript" src="${prc }/js/jQuery/jquery-3.1.1.min.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${prc }/js/jQuery/jquery.form.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${prc }/js/datetimepicker/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${prc }/js/datetimepicker/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${prc }/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	</head>
			<script type="text/javascript">
			$(function(){
				$('.form_date').datetimepicker({
		        language:  'fr',
		        format: "yyyy-mm-dd",
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				language: 'zh-CN',
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
			});
			
			$(function(){
				var $show = $("#showHead");
				var $head = document.getElementById("headImg");
				if(typeof FileReader == "undefined"){
					alert("该浏览器不支持FileReader接口")
				}
				$("#headPre").click(function(){
					var $head = document.getElementById("headImg").files[0];
				    if(!/image\/\w+/.test($head.type)){
				        alert("请选择图片格式！");
				        return false;
				    }
					var reader = new FileReader();
					reader.readAsDataURL($head);
					reader.onload = function(e){
						/*$("#showHead").html('<img src="' + this.result +'" alt="" />')*/
						$("#showHead").html('<img src="' + this.result +'" class="img-circle" style="width: 150px;height: 150px;" />')
					}
				})
			})
			
			$(function(){
				var $show = $("#showBg");
				var $bg = document.getElementById("bgImg");
				if(typeof FileReader == "undefined"){
					alert("该浏览器不支持FileReader接口")
				}
				$("#bgPre").click(function(){
					var $bg = document.getElementById("bgImg").files[0];
				    if(!/image\/\w+/.test($bg.type)){
				        alert("请选择图片格式！");
				        return false;
				    }
					var reader = new FileReader();
					reader.readAsDataURL($bg);
					reader.onload = function(e){
						/*$("#showHead").html('<img src="' + this.result +'" alt="" />')*/
						$("#showBg").html('<img src="' + this.result +'" class="img-rounded" style="width: 250px;height: 200px;" >')
					}
				})
			})
		</script>
	<body>
		<!--头部开始-->
		<div id="header" style="background-image: url('${loginUser.bgImgPath}');background-color: rgba(2,2,2,0.3); background-position-x: center;background-position-y: center;background-size: 100% 100%;background-repeat: no-repeat;">
			<div class="overlay">
				<div class="row">
					<div class="col-md-4 logo-div">
						<div class="log-inner text-center">
							<div class="logo-name">
								<a href="${prc }/user/user_mainPage">
									<img src="${loginUser.headImgPath }" class="img-circle" style="width: 150px;height: 150px;"/>
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-8 header-text-top" id="about">
						<h1>${loginUser.blogName }</h1>
							${loginUser.blogIntro }
						再来一堆描述。。。。。。。。。。。。。。。。。。<br />
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
			    <!--  
			    	待添加链接。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
			    -->
			    <li><a href="#">我的文章</a></li>
			    <!-- 搜索 
			    	待添加action。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
			    -->
			    <form class="navbar-form navbar-left" role="search" action="#">
				      <div class="form-group">
				        <input type="text" class="form-control" placeholder="请输入搜索内容" >
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
		<!--正文开始-->
		<div class="container" style="padding-top: 30px;">
			<div class="row">
				<div class="col-md-8">
					<form class="form-horizontal" role="form" enctype="multipart/form-data" action="${prc }/user/modify_update" method="post">
					<input type="hidden" name="userId" value="${loginUser.userId }" /> 
						<div class="form-group">
							<label class="col-sm-2 control-label">用户名：</label>
							<div class="col-sm-10">
								<p class="form-control-static"><strong style="color: red;">${loginUser.userName }</strong></p>
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">密码：</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password" name="password" value="${loginUser.password }" />
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">邮箱：</label>
							<div class="col-sm-10">
								<p class="form-control-static"><strong style="color: black;">${loginUser.email }</strong></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">手机号码：</label>
							<div class="col-sm-10">
								<input type="tel" class="form-control" id="phoneNum" name="phoneNum" value="${loginUser.phoneNum }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">QQ：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="QQ" name="QQ" value="${loginUser.QQ }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">地址：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="address" name="address" value="${loginUser.address }" />
							</div>
						</div>
						<div class="form-group">
							<label for="dtp_input2" class="col-sm-2 control-label">生日:</label>
				                <div class="input-group date form_date col-sm-10" data-date="" data-date-format="yyyy MM dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="14" type="datetime" readonly  id="birth" value="${loginUser.birth }">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
							<input type="hidden" id="dtp_input2" value=""  name="birth"/><br/>
						</div>
						<div class="form-group">
							<label for="header" class="col-sm-2 control-label">博客名称:</label>
				                <div class="col-sm-10">
								<input type="text" class="form-control" id="blogName" name="blogName" value="${loginUser.blogName }" />
							</div>
						</div>
						<div class="form-group">
							<label for="header" class="col-sm-2 control-label">博客介绍:</label>
				                <div class="col-sm-10" >
				                <textarea cols="3" class="form-control" id="blogIntro" name="blogIntro" >${loginUser.blogIntro }</textarea>	
							</div>
						</div>
						
						<div class="form-group">
							<label for="header" class="col-sm-2 control-label">自我介绍:</label>
				                <div class="col-sm-10" >
				                <textarea cols="3" class="form-control" id="intro" name="intro" >${loginUser.intro }</textarea>	
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">头像</label>
								<div class="input-group col-sm-10">
								  <input type="file" class="form-control input-group-addon" style="display: none;" id="headImg" name="headImg">
								  <span class="input-group-addon btn btn-success" onclick="$('input[id=headImg]').click();">浏览</span>
								  <input type="text" class="form-control" id="imgName" readonly />
								  <span class="input-group-btn"><span class="btn btn-success" id="headPre">点击预览</span></span>
								</div>
						</div>
						<script>
							$(function(){
								$('input[id=headImg]').change(function() {  
								/*alert($(this).val())*/
								$('#imgName').val($(this).val());  
								});
							})
						</script>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">背景</label>
								<div class="input-group col-sm-10">
								  <input type="file" class="form-control input-group-addon" style="display: none;" id="bgImg" name="bgImg">
								  <span class="input-group-addon btn btn-success" onclick="$('input[id=bgImg]').click();">浏览</span>
								  <input type="text" class="form-control" id="bgName" readonly />
								  <span class="input-group-btn"><span class="btn btn-success" id="bgPre">点击预览</span></span>
								</div>
						</div>
						<script>
							$(function(){
								$('input[id=bgImg]').change(function() {  
								/*alert($(this).val())*/
								$('#bgName').val($(this).val());  
								});
							})
						</script>
						<div class="form-group">
							<label class="col-sm-2 control-label">性别：</label>
							<div class="input-group">
								<label class="checkbox-inline">
									<input type="radio" name="sex" id="boy" value="男" checked="checked"/>男
								</label>
								<label class="checkbox-inline">
									<input type="radio" id="girl" name="sex" value="女"/>女
								</label>	
							</div>
						</div>
						<div class="form-group" style="margin-top: 20px;">
							<input type="submit" class="btn btn-success" value="确认修改" style="margin-left: 300px;"/>
							<input type="reset" class="btn btn-danger" value="重置" />
						</div>
					</form>
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-3" style="padding-top: 0px;">
					<div class="row">
						<ul class="list-group">
							<li class="list-group-item"><strong>预览</strong></li>
							<li class="list-group-item">
								头像	
							</li>
							<li class="list-group-item" id="showHead"><img src="${loginUser.headImgPath }" class="img-circle" style="width: 150px;height: 150px;" ></li>
							<li class="list-group-item">
								背景	
							</li>
							<li class="list-group-item" id="showBg"><img src="${loginUser.bgImgPath }" class="img-rounded" style="width: 250px;height: 200px;" ></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>