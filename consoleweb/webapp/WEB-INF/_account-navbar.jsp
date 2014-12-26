<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp" %>
<div class="well">
	<ul class="nav nav-list">
		<li class="nav-header">账户</li>
		<li class="${fn:startsWith(requestServletPath, '/console/identity/account/accountInfo/')?'active':''}">
			<a href="console/identity/account/accountInfo"><i class="icon-user"></i> 账户信息</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/identity/account/modifyPassword/')?'active':''}">
			<a href="console/identity/account/modifyPassword"><i class="icon-edit"></i> 修改密码</a>
		</li>
	</ul>
</div>
<jsp:include page="portalweb/_sidebar.jsp"></jsp:include>