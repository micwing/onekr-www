<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <base href="<%=basePath%>" />

    <!-- Le styles -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
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

      <form class="form-signin">
        <label id="msg">登录</label>
        <input type="text" class="input-block-level" placeholder="用户名" id="username" value="admin">
        <input type="password" class="input-block-level" placeholder="密码" id="password" value="1">
        <label class="checkbox">
          <input type="checkbox" value="remember-me" id="remember"> 记住我
        </label>
        <button class="btn btn-large btn-primary" type="button" id="login-btn">登录</button>
        <hr>
        <div class="pull-right">
        	<a href="/">返回首页</a>
        </div>
      </form>

    </div> <!-- /container -->
    <script src="assets/js/jquery.js"></script>
	<script type="text/javascript">
    var ns = ns || {};
    ns.checkInput = function() {
    	if ($('#username').val() == '') {
    		$('#msg').html('<span style="color:red">请输入用户名！</span>');
    		$('#username').focus();
    		return false;
    	}
    	if ($('#password').val() == '') {
    		$('#msg').html('<span style="color:red">请输入密码！</span>');
    		$('#password').focus();
    		return false;
    	}
    	return true;
    };
    ns.doSignin = function(btnId) {
    	$(btnId).attr('disabled', true);
    	if (!ns.checkInput()) {
    		$(btnId).attr('disabled', false);
    		return;
    	}
    	$.ajax({
			url : "login/doSignin",
			type : 'post',
	        dataType : 'json',
			data : {username:$('#username').val(), password:$('#password').val(), remember:$('#remember:checked').val()},
			success : function(data) {
				if (data.code != 0) {
    				$('#msg').html('<span style="color:red">'+data.message+'</span>');
    				$('#password').focus();
				} else {
					$('#msg').html('<span style="color:green">登录成功</span>');
					location.href = 'identity/dashboard';
				}
				$(btnId).attr('disabled', false);
			}
		});
    };
    $('#login-btn').click(function() {
    	ns.doSignin('#login-btn');
    });
    </script>
    
  </body>
</html>


