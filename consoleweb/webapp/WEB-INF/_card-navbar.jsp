<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<div class="well">
	<ul class="nav nav-list">
		<li class="nav-header">请柬</li>
		<li class="${fn:startsWith(requestServletPath, '/console/card/info/makecodeinput/')?'active':''}">
			<a href="console/card/info/makecodeinput"><i class="icon-plus"></i> 新增请柬</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/card/info/list/')?'active':''}">
			<a href="console/card/info/list"><i class="icon-tasks"></i> 请柬列表</a>
		</li>
		<li class="nav-header">订单</li>
		<li>
			<a href="console/card/order/list"><i class="icon-tasks"></i> 在线订单</a>
		</li>
	</ul>
</div>
<shiro:hasRole name="ADMINISTRATOR">
<div class="well">
	<ul class="nav nav-list">
		<li class="nav-header">制作码</li>
		<li class="${fn:startsWith(requestServletPath, '/console/card/makecode/generate/')?'active':''}">
			<a href="console/card/makecode/generate"><i class="icon-certificate"></i> 申请制作码</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/card/makecode/list/')?'active':''}">
			<a href="console/card/makecode/list"><i class="icon-list-alt"></i> 制作码列表</a>
		</li>
	</ul>
</div>
</shiro:hasRole>
<jsp:include page="portalweb/_sidebar.jsp"></jsp:include>