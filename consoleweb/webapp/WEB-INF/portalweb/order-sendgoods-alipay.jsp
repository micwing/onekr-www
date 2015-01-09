<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@include file="../common/includes.jsp" %>
<div class="row">
	<div class="span12">
		<div class="well">
		正在发货...
	<%
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest((Map) request.getAttribute("map"),"post","确认");
		out.println(sHtmlText);
	%>
		</div>
		<div>
			
		</div>
	</div>
	
</div>
