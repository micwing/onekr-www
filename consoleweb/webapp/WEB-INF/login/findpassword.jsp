<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>找回密码</title>
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
			<h3>找回密码</h3>
			<hr>
			<form class="form-horizontal form-signin" id="main-form">
				<div class="control-group">
					<label class="control-label" for="username">用户名</label>
					<div class="controls">
						<input type="text" id="username" value="" name="username">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="email">邮箱</label>
					<div class="controls">
						<input type="text" id="email" value="" name="email">
					</div>
				</div>
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
				<div class="control-group">
					<div class="controls">
						<button class="btn btn-large btn-primary" type="button" id="main-btn">确定</button>
					</div>
				</div>
			</form>
			<hr>
			<div class="pull-right">
				<a href="/">返回首页</a> | <a href="login">用户登录</a> | <a href="login/register">新用户注册</a>
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
		email : {
			required : true,
			email : true
		},
		verifycode : {
			required : true
		}
	},
  	errorClass : "help-block",
	errorElement : "div",
	highlight : function(element, errorClass, validClass) {
		$(element).parents('.control-group').removeClass('success');
		$(element).parents('.control-group').addClass('error');
	},
	unhighlight : function(element, errorClass, validClass) {
		$(element).parents('.control-group').removeClass('error');
		$(element).parents('.control-group').addClass('success');
	}
});
ns.doFindPassword = function(btnId) {
	$(btnId).attr('disabled', true);
	if (!$('#main-form').valid()) {
		$(btnId).attr('disabled', false);
		return;
	}
	$.ajax({
		url : "login/doFindPassword",
		type : 'post',
			dataType : 'json',
		data : $('#main-form').serialize(),
		success : function(data) {
			if (data.code == 0) {
				alert('重置成功，请到您到邮箱中查收重置邮件！');
				return;
			}
			
			$('#verifycode-img').trigger('click');
			$('#verifycode').val('');
			
			if(data.code == 7) {
				validator.showErrors({
					"verifycode": "验证码错误，请重新输入！"
				});
				$('#verifycode').focus();
			} else if(data.code == 50) {
				validator.showErrors({
					"email": "未找到用户或者用户名未设置邮箱！"
				});
				$('#email').focus();
			} else if(data.code == 5) {
				validator.showErrors({
					"email": "用户名与邮箱不匹配！"
				});
				$('#email').focus();
			} else {
				alert('操作失败，请重试！');
			}
			$(btnId).attr('disabled', false);
		}
	});
};
$('#main-btn').click(function() {
	ns.doFindPassword('#main-btn');
	$('#username').focus();
});
</script>
<jsp:include page="../common/analytics.jsp"></jsp:include>
</body>
</html>


