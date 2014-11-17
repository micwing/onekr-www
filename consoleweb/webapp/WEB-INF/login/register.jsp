<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<base href="<%=basePath%>" />

<!-- Le styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.min.css" rel="stylesheet">

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
	<h3>注册</h3>
	<hr>
	<form class="form-horizontal form-signin" id="main-form">
		<div class="control-group">
			<label class="control-label" for="inputEmail">用户名</label>
			<div class="controls">
				<input type="text" id="username" value="" name="username">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputEmail">邮箱</label>
			<div class="controls">
				<input type="text" id="email" value="" name="email">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputEmail">密码</label>
			<div class="controls">
				<input type="password" id="password" value="" name="password">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputEmail">重复密码</label>
			<div class="controls">
				<input type="password" id="password" value="" name="password2">
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-large btn-primary" type="button" id="register-btn">注册</button>
			</div>
		</div>
	
	</form>
	<hr>
	<div class="pull-right">
		<a href="/">返回首页</a> | <a href="login">用户登录</a>
	</div>

</div>

<script src="assets/js/jquery.js"></script>
<script src="assets/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="assets/js/validate/more_rules.js" type="text/javascript"></script>
<script src="assets/js/validate/message_cn.js" type="text/javascript"></script>

<script type="text/javascript">
var ns = ns || {};
$('#main-form').validate({
	rules : {
		username : {
			required : true,
			maxlength : 20,
			chrnum_ : true,
			remote: {
			   type: "post",
			   url: "login/registerNameAvailable",
			   data: {
			  	username: function() {
					 return $("#username").val();
				  }
			   },
			   dataType: "json"
			}
		},
		email : {
			required : true,
			maxlength : 20,
			email : true
		},
		password : {
			required : true,
			minlength : 6,
			maxlength : 20
		},
		password2 : {
			required : true,
			minlength : 6,
			maxlength : 20,
			equalTo : '#password'
		}
	},
	messages:{    
		username:{
	 	remote:"用户名已存在，请换一个"
	  } 
  	},
  	errorClass : "help-block",
	errorElement : "span",
	highlight : function(element, errorClass, validClass) {
		$(element).parents('.control-group').removeClass('success');
		$(element).parents('.control-group').addClass('error');
	},
	unhighlight : function(element, errorClass, validClass) {
		$(element).parents('.control-group').removeClass('error');
		$(element).parents('.control-group').addClass('success');
	}
});
ns.doRegister = function(btnId) {
	$(btnId).attr('disabled', true);
	if (!$('#main-form').valid()) {
		$(btnId).attr('disabled', false);
		return;
	}
	$.ajax({
		url : "login/doRegister",
		type : 'post',
			dataType : 'json',
		data : $('#main-form').serialize(),
		success : function(data) {
			if (data.code != 0) {
						$('#msg').html('<span style="color:red">'+data.message+'</span>');
						$('#password').focus();
						alert('注册成功，请立即登录！');
						location.href='login';
			} else {
				$('#msg').html('<span style="color:green">登录成功</span>');
				location.href = 'card/index';
			}
			$(btnId).attr('disabled', false);
		}
	});
};
$('#register-btn').click(function() {
	ns.doRegister('#register-btn');
});
</script>
  
</body>
</html>


