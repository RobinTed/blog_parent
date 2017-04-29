<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jQuery/jquery-1.8.0.min.js" ></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js" ></script>
			<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" />
<title>Insert title here</title>
</head>
<script type="text/javascript">
$(function(){
	$("#insertArticle").click(function(){
		$.ajax({
		    url: '${pageContext.request.contextPath}/user/write_upload',
		    type: 'POST',
		    cache: false,
		    data: new FormData($('#imgForm')[0]),
		    processData: false,
		    contentType: false
		}).done(function(res) {
		}).fail(function(res) {});
	})
})
</script>
<body>
<form id="uploadForm" enctype="multipart/form-data">
    <input id="file" type="file" name="file"/>
    <button id="upload" type="button">upload</button>
</form>	

<form role="form" id="imgForm"  enctype="multipart/form-data" >
							<div class="form-group">
						<div class="input-group col-sm-10">
						  <input type="file" class="form-control input-group-addon" style="display: none;" id="articleImg" name="articleImg">
						  <span class="input-group-addon btn btn-success" onclick="$('input[id=articleImg]').click();">浏览</span>
						  <input type="text" class="form-control" id="imgName" readonly />
						  <span class="input-group-btn"><span class="btn btn-success" id="insertArticle">点击插入</span></span>
							<input type="hidden" value="lala" name="test" /> 
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
</body>
</html>