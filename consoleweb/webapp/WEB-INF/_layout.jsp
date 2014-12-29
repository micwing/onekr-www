<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>一氪软件工作室</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<base href="<%=basePath%>" />

<!-- Le styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="assets/css/site.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="assets/js/html5shiv.js"></script>
<script src="assets/js/respond.min.js"></script>
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
<!--[if lt IE 8]>
<div class="container">
<div class="alert alert-error">
您使用的IE浏览器版本太低，建议升级到IE9，或者改用谷歌Chrome、火狐Firefox等浏览器访问本页面！
</div>
</div>
<![endif]-->
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</a>
			
			<a class="brand" href="${ctx}">一氪软件工作室</a>
			
			<tiles:insertAttribute name="topmenu" />
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="span3">
			<tiles:insertAttribute name="navbar" />
		</div>
		<div class="span9">
			<div class="well">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>
</div>
<tiles:insertAttribute name="footer" />
<jsp:include page="common/analytics.jsp"></jsp:include>
</body>
</html>





