<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<base href="<%=basePath%>" />

<!-- Le styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="assets/css/site.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="assets/ico/favicon.png">
</head>
<body>
<div class="container">
	<div class="row">
		<div class="span6 offset3">
			<h3>登录</h3>
			<hr>
			<form class="form-horizontal form-signin" id="main-form">
				<div class="control-group">
					<label class="control-label" for="username">用户名 / 邮箱</label>
					<div class="controls">
						<input type="text" id="username" value="admin" name="username">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">密码</label>
					<div class="controls">
						<input type="password" id="password" value="1" name="password">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remember">记住我</label>
					<div class="controls">
						<input type="checkbox" value="remember-me" id="remember" name="remember">
					</div>
				</div>
				<div id="verifycode-div" style="display: none">
					<div class="control-group">
						<label class="control-label" for="verifycode"></label>
						<div class="controls">
							<img id="verifycode-img" style="cursor: pointer;width: 108px;height: 30px;" src="<%=basePath%>verifyCode" alt="验证码"  onclick="this.src=this.src+'?'+Math.random()">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="">验证码</label>
						<div class="controls">
							<input type="text" id="verifycode" name="verifycode" value="" placeholder="验证码" class="login" style="width: 80px;" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button class="btn btn-large btn-primary" type="button" id="main-btn">登录</button>
						<span id="msg"></span>
					</div>
				</div>
			</form>
			<hr>
			<div class="pull-right">
				<a href="/">返回首页</a> | <a href="login/register">新用户注册</a> | <a href="login/findpassword">找回密码</a>
			</div>
		</div>
	</div>
</div>
  
<script src="assets/js/jquery.js"></script>
<script src="assets/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="assets/js/validate/more_rules.js" type="text/javascript"></script>
<script src="assets/js/validate/message_cn.js" type="text/javascript"></script>

<script type="text/javascript">
var ns = ns || {};
var validator = $('#main-form').validate({
	rules : {
		username : {
			required : true
		},
		password : {
			required : true
		},
		verifycode : {
			required : true
		}
	},
  	errorClass : "help-block",
	errorElement : "span",
	highlight : function(element, errorClass, validClass) {
		$(element).parents('.control-group').removeClass('success');
		$(element).parents('.control-group').addClass('error');
		$('#msg').html('');
	},
	unhighlight : function(element, errorClass, validClass) {
		$(element).parents('.control-group').removeClass('error');
		$(element).parents('.control-group').addClass('success');
		$('#msg').html('');
	}
});
ns.doLogin = function(btnId) {
	$(btnId).attr('disabled', true);
	if (!$('#main-form').valid()) {
		$(btnId).attr('disabled', false);
		return;
	}
	$.ajax({
		url : "login/doSignin",
		type : 'post',
		dataType : 'json',
		data : $('#main-form').serialize(),
		success : function(data) {
			if (data.code == 0) {
				$('#msg').html('<span style="color:green">登录成功，正在跳转...</span>');
				location.href = '${ctx}/card/info';
				return;
			}
			
			ns.getNoValidateTimes();
			$('#verifycode-img').trigger('click');
			$('#verifycode').val('');
			
			if(data.code == 7) {
				validator.showErrors({
					"verifycode": "验证码错误，请重新输入！"
				});
				$('#verifycode').focus();
			} else if(data.code == 12) {
				validator.showErrors({
					"username": "",
					"password": data.message
				});
				$('#password').focus();
			}
			$(btnId).attr('disabled', false);
		}
	});
	
};
ns.getNoValidateTimes = function() {
	$.ajax({
		url : "login/getNoValidateTimes",
		type : 'post',
			dataType : 'json',
		data : $('#main-form').serialize(),
		success : function(data) {
			if (data.code == 0) {
				if (data.value >= 3) {
					$('#verifycode-div').show();
				}
			}
		}
	});
};
$(function() {
	ns.getNoValidateTimes();
	$('#main-btn').click(function() {
		ns.doLogin('#main-btn');
	});
});
</script>
</body>
</html>


