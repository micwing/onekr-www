<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>用户中心</title>
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
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</a>
					
					<a class="brand" href="#">ONEKR | 用户中心</a>
					
					<div class="nav-collapse collapse">
						<ul class="nav pull-right">
							<li class="divider-vertical"></li>
							
							<li class="dropdown">
		                        <a data-toggle="dropdown" class="dropdown-toggle" href="#"><shiro:principal /> <b class="caret"></b></a>
		                        <ul class="dropdown-menu">
		                          <li><a href="identity/account">我的账号</a></li>
		                          <li><a href="login/doSignout">安全退出</a></li>
		                        </ul>
	                      	</li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>

		<div class="row">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">index</li>
						<li class="${fn:startsWith(requestServletPath, '/identity/dashboard/')?'active':''}">
							<a href="identity/dashboard"><i class="icon-pencil"></i> index</a>
						</li>
						<li class="nav-header">请柬</li>
						<li class="${fn:startsWith(requestServletPath, '/card/info/add/')?'active':''}">
							<a href="card/info/add"><i class="icon-pencil"></i> 新增请柬</a>
						</li>
						<li class="${fn:startsWith(requestServletPath, '/card/info/list/')?'active':''}">
							<a href="card/info/list"><i class="icon-list-alt"></i> 请柬列表</a>
						</li>
						
						<li class="nav-header">账号</li>
						<li class="${(requestServletPath == '/identity/account/')?'active':''}">
							<a href="identity/account"><i class="icon-user"></i> 账号信息</a>
						</li>
						<li class="${fn:startsWith(requestServletPath, '/identity/account/modifyPassword/')?'active':''}">
							<a href="identity/account/modifyPassword"><i class="icon-certificate"></i> 修改密码</a>
						</li>
					</ul>
				</div>

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





