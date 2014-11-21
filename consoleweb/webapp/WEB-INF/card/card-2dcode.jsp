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
		<h5>用微信扫描该二维码，在手机上查看请柬效果</h5><br>
		提示：如果修改了请柬信息、照片、模板等，只要在手机上刷新即可查看效果，不需要重新扫描二维码。
	</div>
</div>



