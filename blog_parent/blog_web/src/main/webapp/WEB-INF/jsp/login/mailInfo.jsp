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
<title>激活邮箱</title>
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
				<div class="col-sm-3">
						<div class="header__item header__item--ib header__item--mail"><div id="mail" class="mail headlink mail--state-163 mail--state-hover" monkey="mail"><a class="mail__link headlink__link s-wea" href="#" data-hook="trigger" alog-custom="ck_url:XzFafR-DNR-DcWCBnWwKwjnYwHKKnjbYnRfvnWIAnb7APj6dwWNKwDNAwRmlwbTqnic_ch-scWCBnWD3QWD4ri3kPjD8ric_cMNzUaclcMILIz4CmytknWn8mvqVgatBQaFYpyd-TLw9Ug0BrWDYrHnznjnzPW9q,ck:3643.432.88.1302.38.1172.107.488">邮箱</a><div class="mail__pop"><form id="mailForm" class="mail__form" method="post" action="https://reg.163.com/logins.jsp?df=hao123"><div class="mail__box mail__box--name"><label class="mail__placeholder" for="mailName" style="display: none;">邮箱帐号</label><input id="mailName" class="mail__input mail__input--name" autocomplete="off"></div><div class="mail__box mail__box--suffix" id="mailSuffixBox"><a class="mail__suffix" id="mailSuffix" hidefocus="true" href="javacript:;" alog-custom="ck_url:XzFafR-DNR-DcWCBnWwKwjnYwHKKnjbYnRfvnWIAnb7APj6dwWNKwDNAwRmlwbTqnic_ch-scWCBnWD3QWD4ri3kPjD8ric_cMNzUaclcMILIz4CmytknWn8mvqVgatBQaFYpyd-TLw9Ug0BrWDYrHnznjnzPW9q,ck:5758.512.50321.1459.84.1172.107.297"><span id="mailSuffixText">@126.com</span><i class="mail__icon mail__icon--more"></i></a><ul class="mail__list"><li><a class="mail__list-item mail__list-item-mail" href="https://reg.163.com/CheckUser.jsp?df=hao123" index="0">@163.com</a></li><li><a class="mail__list-item mail__list-item-mail" href="https://reg.163.com/logins.jsp?df=hao123" index="1" alog-custom="ck_url:XzFafR-DNR-DcWCBnWwKwjnYwHKKnjbYnRfvnWIAnb7APj6dwWNKwDNAwRmlwbTqnic_ch-scWCBnWD3QWD4ri3kPjD8ric_cMNzUaclcMILIz4CmytknWn8mvqVgatBQaFYpyd-TLw9Ug0BrWDYrHnznjnzPW9q,ck:7400.415.81.1458.128.1172.107.326">@126.com</a></li><li><a class="mail__list-item mail__list-item-mail" href="https://login.sina.com.cn/sso/login.php" index="2">@sina.com</a></li><li><a class="mail__list-item mail__list-item-mail" href="https://passport.sohu.com/act/loginjs" index="3">@sohu.com</a></li><li><a class="mail__list-item mail__list-item-mail" href="https://reg.163.com/logins.jsp?df=hao123" index="4">@yeah.net</a></li><li><a class="mail__list-item mail__list-item-mail" href="https://mail.10086.cn/Login/Login.ashx" index="5">@139.com</a></li><li><span class="mail__list-item mail__list-item-tip">以下为弹出登录</span></li><li><a class="mail__list-item mail__list-item-link" href="https://www.alipay.com/user/login.htm" index="0">支付宝</a></li><li><a class="mail__list-item mail__list-item-link" href="https://passport.baidu.com/v2/?login" index="1">登录百度</a></li><li><a class="mail__list-item mail__list-item-link" href="http://mail.qq.com" index="2" alog-custom="ck_url:XzFafR-DNR-DcWCBnWwKwjnYwHKKnjbYnRfvnWIAnb7APj6dwWNKwDNAwRmlwbTqnic_ch-scWCBnWD3QWD4ri3kPjD8ric_cMNzUaclcMILIz4CmytknWn8mvqVgatBQaFYpyd-TLw9Ug0BrWDYrHnznjnzPW9q,ck:726.462.111.1453.295.1172.107.338">QQ邮箱</a></li><li><a class="mail__list-item mail__list-item-link" href="http://qzone.qq.com/index.html" index="3">QQ空间</a></li><li><a class="mail__list-item mail__list-item-link" href="http://weibo.com/login.php" index="4">新浪微博</a></li><li><a class="mail__list-item mail__list-item-link" href="http://mail.google.com/mail/" index="5">gmail.com</a></li><li><a class="mail__list-item mail__list-item-link" href="http://www.hotmail.com" index="6">hotmail.com</a></li><li><a class="mail__list-item mail__list-item-link" href="http://passport.21cn.com" index="7">21cn.com</a></li><li><a class="mail__list-item mail__list-item-link" href="https://passport.alipay.com/login/login.htm?return_url=http%3A%2F%2Fmail.aliyun.com%2Falimail%2Fauth%2FcallbackForHavana%3Freurl%3D%252Falimail%252F&amp;fromSite=9" index="8">阿里云邮箱</a></li><li><a class="mail__list-item mail__list-item-link" href="http://mail.189.cn" index="9">189邮箱</a></li><li><a class="mail__list-item mail__list-item-link" href="http://www.kaixin001.com" index="10">开心网</a></li><li><a class="mail__list-item mail__list-item-link" href="http://passport.renren.com" index="11">人人网</a></li></ul></div><div class="mail__box mail__box--pwd"><label class="mail__placeholder" for="mailPwd" style="display: block;">邮箱密码</label><input id="mailPwd" class="mail__input mail__input--pwd" type="password" autocomplete="new-password"></div><div class="mail__box mail__box--btn"><input id="mailSubmit" class="mail__submit" type="submit" hidefocus="true" value="登录"><span id="mailCancel" class="mail__btn mail__cancel">取消</span></div><div id="mailParas" class="mail__paras" style="display:none"></div></form><div class="mail__proxy" data-hook="proxy"><input name="agree" class="mail__agree" id="mailAgree" type="checkbox" checked="checked"><label for="mail-agree">我阅读并同意</label><a class="mail__agree-link" href="http://www.hao123.com/mailproxy">使用协议</a></div></div></div></div>
				</div>
				<div class="col-sm-5">
			<h2>
				<label class="label label-info">
				注册成功
			</label>
			</h2>
			<h2>
				<label class="label label-warning">
					请到邮箱点击确认激活。激活链接4小时内有效，请尽快到邮箱查看。
				</label>
			</h2>
			<h2>
				<label class="label label-danger">
					如果没有请看垃圾邮件里面
				</label>
			</h2>
		</div>
			</div>
		</div>
</body>
</html>