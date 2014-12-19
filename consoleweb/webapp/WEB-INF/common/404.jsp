<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.framework.exception.AppException"%>
<%@page import="onekr.framework.result.Result"%>
<%@include file="../common/includes.jsp" %>
<%
Result result = (Result)request.getAttribute("result");
String message = "";
if (result != null) {
	message = result.getMessage();
} %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>页面未找到</title>
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
			<h3>哎呦，页面未找到！</h3>
			<hr>
			<p><%=message %></p>
			<hr>
			<div class="pull-right">
				<a href="javascript:;" onclick="history.go(-1)">返回</a>
			</div>
			<footer>
			  <p>&copy; www.onekr.com 2013 - <%= onekr.framework.utils.DateUtil.getYear(new java.util.Date()) %></p>
			</footer>
		</div>
	</div>
</div>
<jsp:include page="analytics.jsp"></jsp:include>
</body>
</html>


