<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer" %>
<%@include file="../common/includes.jsp"%>
<script src="assets/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<h3>扫描二维码
<span class="pull-right">
	<span class="btn-group">
		<a class="btn" href="console/card/info/modify/${card.id}">请柬信息</a>
		<a class="btn" href="console/card/photo/cardphoto/${card.id}">管理相册</a>
		<a class="btn" href="console/card/map/cardmap/${card.id}">设置地图</a>
		<a class="btn" href="console/card/music/cardmusic/${card.id}">选择音乐</a>
		<a class="btn btn-info" href="console/card/2dcode/index/${card.id}">扫描二维码</a>
	</span>
	<a class="btn btn-large" href="console/card/info/list">返回列表</a>
</span>
</h3>
<hr class="head-hr">

<div class="alert">
	<strong>温馨提示</strong><br>
	用<strong>微信</strong>扫描该二维码，在手机上查看请柬效果；<br>
	如果满意，请转发给亲友们；如果不满意可以返回修改；<br>
	修改时只要在手机微信中<strong>刷新</strong>即可查看效果，不用重新扫描二维码。<br>
</div>

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<img alt="" src="<%=basePath%>qr2dCode?m=<%=basePath%>/card/cover/${card.id}&w=300&h=300" /><br>
	</div>
</div>



