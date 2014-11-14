<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE html> 
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>404</title>
</head>

<body>
<h1>404</h1>
<br>
<% 
String message = (String) request.getAttribute("message");
if (message != null) {
%>
<%=message %>
<% } %>
<a href="javascript:;" onclick="history.go(-1)">back</a>
</body>
</html>
