<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType"%>
<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer" %>
<%@include file="../common/includes.jsp"%>
<h3>账户信息</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
	    <form class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="name">用户名</label>
				<div class="controls">
					<label class="checkbox">${user.name}</label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="email">邮箱</label>
				<div class="controls">
					<label class="checkbox">${user.email}</label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="createAt">注册时间</label>
				<div class="controls">
					<label class="checkbox"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${user.createAt}" type="both"/></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">用户组</label>
				<div class="controls">
					<label class="checkbox">${user.group}</label>
				</div>
			</div>
		</form>
	</div>
</div>




