<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp" %>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li class="nav-header">中心</li>
		<li class="${fn:startsWith(requestServletPath, '/console/dashboard/')?'active':''}">
			<a href="/console/dashboard"><i class="icon-home"></i> 仪表盘</a>
		</li>
		
		<li class="nav-header">配置</li>
		<li class="${fn:startsWith(requestServletPath, '/console/config/baseConfig/')?'active':''}">
			<a href="/console/config/baseConfig"><i class="icon-cog"></i> 首页展示</a>
			<a href="/console/config/baseConfig"><i class="icon-cog"></i> 访问配置</a>
		</li>
		
		<li class="nav-header">请柬</li>
		<li class="${fn:startsWith(requestServletPath, '/console/article/add/')?'active':''}">
			<a href="/console/article/add"><i class="icon-pencil"></i> 新请柬</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/article/list/')?'active':''}">
			<a href="/console/article/list"><i class="icon-list-alt"></i> 请柬列表</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/script/list/')?'active':''}">
			<a href="/console/script/list"><i class="icon-leaf"></i> 垃圾箱</a>
		</li>
		
		<li class="nav-header">主题</li>
		<li class="${fn:startsWith(requestServletPath, '/console/file/add/')?'active':''}">
			<a href="/console/file/add"><i class="icon-arrow-up"></i> 主题列表</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/file/list/')?'active':''}">
			<a href="/console/file/list"><i class="icon-file"></i> 文件列表</a>
		</li>
		
		<li class="nav-header">门面</li>
		<li class="${fn:startsWith(requestServletPath, '/console/domain/config/')?'active':''}">
			<a href="/console/domain/config"><i class="icon-globe"></i> 域名配置</a>
		</li>
		
		<li class="nav-header">留言</li>
		<li class="${fn:startsWith(requestServletPath, '/console/leaveComment/list/')?'active':''}">
			<a href="/console/leaveComment/list"><i class="icon-envelope"></i> 留言列表</a>
		</li>
		
		<li class="divider">
		</li>
		
		<li class="nav-header">账号</li>
		<li class="${fn:startsWith(requestServletPath, '/console/account/accountInfo/')?'active':''}">
			<a href="/console/account/accountInfo"><i class="icon-user"></i> 账号信息</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/account/modifyPassword/')?'active':''}">
			<a href="/console/account/modifyPassword"><i class="icon-certificate"></i> 修改密码</a>
		</li>
	</ul>
</div><!--/.well -->
