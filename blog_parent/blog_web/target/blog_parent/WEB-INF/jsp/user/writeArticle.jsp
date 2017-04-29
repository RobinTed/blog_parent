<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
			<script type="text/javascript" src="${prc }/js/jQuery/jquery-3.1.1.min.js" ></script>
			<script type="text/javascript" src="${prc }/xheditor_code/xheditor/jquery-1.8.0.min.js" ></script>
			<script type="text/javascript" src="${prc }/bootstrap/js/bootstrap.min.js" ></script>
			<link rel="stylesheet" href="${prc }/bootstrap/css/bootstrap.min.css" />
			<script type="text/javascript" src="${prc }/xheditor_code/xheditor/xheditor-1.2.1.min.js" ></script>
			<script type="text/javascript" charset="UTF-8" src="${prc }/xheditor_code/xheditor/xheditor_lang/zh-cn.js" ></script>
		<style type="text/css">
	<!-- /* 增加插入代码工具图标 */
	.btnCode {
		background: transparent url('${prc}/xheditor_code/img/code.png') no-repeat 0px 0px;
		background-position: 3px -2px;
	}
	-->
	</style>
<script type="text/javascript">

	$(function(){
		/* 去掉大小，bootstrap不支持 */
		$('#articleText').xheditor({tools:'Cut,Copy,Paste,Pastetext,|,Blocktag,Fontface,Bold,Italic,Underline,Strikethrough,|,FontColor,BackColor,|,SelectAll,Removeformat,Align,List,Outdent,Indent,Link,Unlink,Anchor,Img,Flash,Media,Hr,Emot,Table,Source,Preview,Print,Fullscreen,About'});
		
		   var plugins={
				Code:{c:'btnCode',t:'插入代码',h:1,e:function(){
					var _this=this;
					var htmlCode="<div>编程语言<select id='xheCodeType'>";
						htmlCode+="<option value='html'>HTML/XML</option>";
						htmlCode+="<option value='js'>Javascript</option>";
						htmlCode+="<option value='css'>CSS</option>";
						htmlCode+="<option value='php'>PHP</option>";
						htmlCode+="<option value='java'>Java</option>";
						htmlCode+="<option value='py'>Python</option>";
						htmlCode+="<option value='pl'>Perl</option>";
						htmlCode+="<option value='rb'>Ruby</option>";
						htmlCode+="<option value='cs'>C#</option>";
						htmlCode+="<option value='c'>C++/C</option>";
						htmlCode+="<option value='vb'>VB/ASP</option>";
						htmlCode+="<option value=''>其它</option>";
						htmlCode+="</select></div><div>";
						htmlCode+="<textarea id='xheCodeValue' wrap='soft' spellcheck='false' style='width:300px;height:100px;' />";
						htmlCode+="</div><div style='text-align:right;'><input type='button' id='xheSave' value='确定' /></div>";			
					var jCode=$(htmlCode),jType=$('#xheCodeType',jCode),jValue=$('#xheCodeValue',jCode),jSave=$('#xheSave',jCode);
					jSave.click(function(){
						_this.loadBookmark();
						_this.pasteHTML('<pre class="brush: '+jType.val()+'">'+_this.domEncode(jValue.val())+'</pre>&nbsp;');
						_this.hidePanel();
						return false;	
					});
					_this.saveBookmark();
					_this.showDialog(jCode);
				}},
				
			};
			$('#articleText').xheditor({
					plugins:plugins,  //使用我们定义的插件  
					loadCSS:'<style>pre{margin-left:2em;border-left:3px solid #CCC;padding:0 1em;}</style>',
			});
	});
	
	//动态插入图片
	$(function(){
		var editor;
		editor = $('#articleText').xheditor();
		/* alert(editor); */
		/* editor.pasteText('34'); */
		$("#insertArticle").click(function(){
			$.ajax({
			    url: '${pageContext.request.contextPath}/user/write_upload',
			    type: 'POST',
			    cache: false, 
			    data: new FormData($('#imgForm')[0]),
			    processData: false,
			    contentType: false,
			    success:function(data){
			    	/* alert(data) */
			    	/* alert("${articleImgPath}"); */
			    	if(data == "0"){
			    		$("#imgMsg").html('<font style="color:red">请选择一张图片</font>');
			    	}else if(data=="2"){
			    		$("#imgMsg").html('<font style="color:red">文件格式不是图片格式</font>');
			    	}else if(data=="-1"){
			    		$("#imgMsg").html('<font style="color:red">图片大小不能超过2M</font>');
			    	}else{
			    		 /* alert(data)  */
			    		/* alert(editor) */
			    		/* editor.pasteHTML("324"); */
			    		 editor.pasteHTML("<img style='width:60%;height:60%;' src="+data+">");	    		 
			    	}
			    },
			    error:function(){
			    	alert("失败，文件大小不得超过2M")
			    }
			}).done(function(res) {
			}).fail(function(res) {
			});
		})
	});
	
	$(function(){
		$("#publish").click(function(){
			/* alert($("#articleText").val()); */
		/* 	$.ajax({
			    url: '${pageContext.request.contextPath}/user/write_publish',
			    type: 'POST',
			    cache: false, 
			    data:{articleName:$("#articleName").val(),articleText:$("#articleText").val()}, 注意是冒号！！！
			    processData: false,
			    contentType: false,
			}); */
			 $.post("${prc}/user/write_publish",{articleName:$("#articleName").val(),articleText:$("#articleText").val()},function(data){
				/* alert(data); */
				if(data == "1"){
					location.href="${prc}/user/user_mainPage";
				}
			 }); 
		})
	})
</script>
<title>发表文章</title>
</head>
<body>
	<div class="container-fluid">
			<div class="row">
				<ul class="nav nav-pills navbar-inverse" style="padding-left: 150px;">
					<li class="active"><a href="${prc }/blog_mainPage">博客首页</a></li>
					<li ><a href="${prc }/user/user_mainPage">个人主页</a></li>
					<li><a href="#">文章列表</a></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<ul style="list-style-type: none;margin-top: 20px;">
					<li style="display: inline;"><img src="${loginUser.headImgPath }" class="img-circle" style="width: 100px;height: 100px;"/></li>
					<li style="display: inline;margin-left: 20px;"><a href="${prc }/user/user_mainPage">${loginUser.userName }</a></li>
				</ul>
			</div>
			<hr />
			<div class="row">
				<form role="form" id="articleForm">
					<div class="form-group">
						<h3><label class="label label-primary">文章标题</label></h3>
						<hr />
						<input type="text" class="form-control" placeholder="请输入标题" style="width: 700px;" id="articleName" name="articleName"/>
						<h3><label class="label label-info">文章内容</label></h3>
						<hr />
						<textarea class="form-control col-sm-8" rows="20" style="width: 950px;" id="articleText" name="articleText"></textarea>
					</div>
				</form>
				<div class="col-sm-6">
					<h4><label class="label label-default">上传图片</label></h4>
					<h5><label class="label label-info">图片大小不得超过2m</label></h5>
						<form role="form" id="imgForm"  enctype="multipart/form-data" >
							<div class="form-group">
						<div class="input-group col-sm-10">
						  <input type="file" class="form-control input-group-addon" style="display: none;" id="articleImg" name="articleImg">
						  <span class="input-group-addon btn btn-success" onclick="$('input[id=articleImg]').click();">浏览</span>
						  <input type="text" class="form-control" id="imgName" readonly />
						  <span class="input-group-btn"><span class="btn btn-success" id="insertArticle">点击插入</span></span>
							<span class="input-group-addon" id="imgMsg"></span>
						<!-- 	<input type="hidden" value="lala" name="test" />     测试用的--> 
						</div>
						</div>
						</form>
						<script>
							$(function(){
								$('input[id=articleImg]').change(function() {  
								/*alert($(this).val())*/
								$('#imgName').val($(this).val());  
								});
							})
						</script>
						<hr />
				<div>
					<button class="btn btn-success" style="margin-left: 500px;margin-bottom: 40px;" id="publish">发布</button>
				</div>
				<hr />
				</div>
				
			</div>
		</div>
		<div>
			<label style="width: 100%;height: 80px;background-color: #000000;margin-bottom: 0px;">
				<font style="padding-left: 1100px;padding-top:20px;color: white;">&copy;2016-2017       版权所有，翻版必究</font>
			</label>
		</div>
</body>
</html>