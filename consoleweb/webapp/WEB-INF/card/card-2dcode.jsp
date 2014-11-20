<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer" %>
<%@include file="../common/includes.jsp"%>
<% 
String siteRootUrl = CustomizedPropertyPlaceholderConfigurer.getContextProperty("site.root.url");
%>
<script src="assets/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<h3>二维码
<c:if test="${!empty card}">
	<span class="pull-right"><a class="btn" href="card/music/cardmusic/${card.id}">上一步选择音乐</a></span>
</c:if>
</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<img alt="" src="<%=basePath%>qr2dCode?m=<%=siteRootUrl%>/card/cover/${card.id}&w=300&h=300" /><br>
		用微信扫描该二维码，在手机上查看效果<br>
		通过浏览器预览请点击<a href="<%=siteRootUrl%>/card/cover/${card.id}" target="_blank"><%=siteRootUrl%>/card/cover/${card.id}</a>
	</div>
</div>



