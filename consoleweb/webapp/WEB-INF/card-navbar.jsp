<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li class="nav-header">请柬</li>
		<li>
			<a href="card/info/makecodeinput"><i class="icon-plus"></i> 新增请柬</a>
		</li>
		<li>
			<a href="card/info/list"><i class="icon-tasks"></i> 请柬列表</a>
		</li>
		<li>
			<a href="card/info/pausedlist"><i class="icon-remove"></i> 已停用</a>
		</li>
		
		<shiro:hasRole name="ADMINISTRATOR">
		<li class="nav-header">制作码</li>
		<li>
			<a href="card/makecode/generate"><i class="icon-certificate"></i> 申请制作码</a>
		</li>
		<li>
			<a href="card/makecode/list"><i class="icon-list-alt"></i> 制作码列表</a>
		</li>
		</shiro:hasRole>
		
		<li class="nav-header">账号</li>
		<li>
			<a href="identity/account/accountInfo"><i class="icon-user"></i> 账户信息</a>
		</li>
		<li>
			<a href="identity/account/modifyPassword"><i class="icon-edit"></i> 修改密码</a>
		</li>
	</ul>
</div>