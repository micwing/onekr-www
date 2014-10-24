<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" isErrorPage="true"%>
<%@page import="onekr.framework.exception.AppException"%>
<%@page import="onekr.framework.result.Result"%>
<!DOCTYPE html> 
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>出错啦！</title>
</head>

<body>
<h1>出错啦！</h1>
<br>
<%
Result result = (Result)request.getAttribute("result");
String message = "";
if (result != null) {
	message = result.getMessage();
} %>
<%=message %>
<a href="javascript:;" onclick="history.go(-1)">返回</a>
</body>
</html>
