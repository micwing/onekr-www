<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>管理中心</title>
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
<script src="assets/js/scrolltotop.js"></script>

<script src="assets/js/validate/jquery.validate.js" type="text/javascript"></script>
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
					
					<a class="brand" href="console/card/info/list">一氪工作室 | 管理中心</a>
					
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="${(
							fn:startsWith(requestServletPath, '/console/card/') ||
							fn:startsWith(requestServletPath, '/console/identity/')
							)?'active':''}"><a href="console/card/info/list">请柬管理</a></li>
							<shiro:hasRole name="ADMINISTRATOR">
							<li class="${fn:startsWith(requestServletPath, '/console/system/')?'active':''}"><a href="console/system/config">系统设置</a></li>
							</shiro:hasRole>
						</ul>
						<ul class="nav pull-right">
							<li class="divider-vertical"></li>
							<li class="dropdown">
		                        <a data-toggle="dropdown" class="dropdown-toggle" href="#"><shiro:principal /> <b class="caret"></b></a>
		                        <ul class="dropdown-menu">
		                          <li><a href="console/identity/account/accountInfo"><i class="icon-user"></i> 账户信息</a></li>
		                          <li><a href="console/identity/account/modifyPassword"><i class="icon-edit"></i> 修改密码</a></li>
		                          <li class="divider"></li>
		                          <li><a href="login/doSignout"><i class="icon-share-alt"></i> 安全退出</a></li>
		                        </ul>
	                      	</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="span3">
				<tiles:insertAttribute name="navbar" />
			</div>
			<div class="span9">
				<div class="well" style="background-color: transparent;">
					<tiles:insertAttribute name="content" />
				</div>
			</div>
		</div>

		<hr>
		
		<footer>
		  <p>&copy; www.onekr.com 2013 - <%= onekr.framework.utils.DateUtil.getYear(new java.util.Date()) %></p>
		</footer>

	</div>

</body>
</html>





