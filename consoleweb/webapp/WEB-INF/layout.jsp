<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>控制台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<base href="<%=basePath%>" />

<!-- Le styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 20px;
	padding-bottom: 20px;
}
</style>
<link href="assets/css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="assets/js/html5shiv.js"></script>
    <![endif]-->

<script src="assets/js/jquery.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.json.js"></script>

<script src="assets/js/validate/jquery.validate.js"
	type="text/javascript"></script>
<script src="assets/js/validate/more_rules.js" type="text/javascript"></script>
<script src="assets/js/validate/message_cn.js" type="text/javascript"></script>

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="assets/ico/favicon.png">
</head>

<body>

	<div class="container">
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand" href="#">ONEKR CONSOLE</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="${fn:startsWith(requestServletPath, '/center/')?'active':''}"><a href="#">通知中心</a></li>
							<li class="${fn:startsWith(requestServletPath, '/console/')?'active':''}"><a href="console/dashboard">门户管理</a></li>
							<li class="${fn:startsWith(requestServletPath, '/card/')?'active':''}"><a href="card/index">请柬管理</a></li>
							<li class="${fn:startsWith(requestServletPath, '/identity')?'active':''}"><a href="#contact">用户管理</a></li>
						</ul>
						<ul class="nav pull-right">
							<li><a href="console/account/accountInfo"><shiro:principal /></a></li>
							<li><a href="/" target="_blank">查看前台</a></li>
							<li><a href="https://login.secureserver.net" target="_blank">系统邮箱</a></li>
							<li><a href="login/doSignout">退出</a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>

		<div class="row">
			<div class="span3">
				<tiles:insertAttribute name="navbar" />

			</div>
			<!--/span-->

			<div class="span9">
				<div class="well" style="background-color: transparent;">
					<tiles:insertAttribute name="content" />
				</div>
			</div>
			<!--/span-->
		</div>
		<!--/row-->

		<hr>
		
		<footer>
		  <p>&copy; ONEKR.com 2013 - <%= onekr.framework.utils.DateUtil.getYear(new java.util.Date()) %></p>
		</footer>

	</div>

</body>
</html>





