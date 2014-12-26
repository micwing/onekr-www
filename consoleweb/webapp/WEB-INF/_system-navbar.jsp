<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp" %>
<div class="well">
	<ul class="nav nav-list">
		<shiro:hasRole name="ADMINISTRATOR">
		<li class="nav-header">系统配置</li>
		<li>
			<a href="console/system/config/normalConfig"><i class="icon-cog"></i> 常规配置</a>
		</li>
		<li>
			<a href="console/system/config/emailConfig"><i class="icon-envelope"></i> 邮件服务器</a>
		</li>
		
		<li class="nav-header">文件管理</li>
		<li>
			<a href="console/system/music/list"><i class="icon-music"></i> 系统音乐</a>
		</li>
		
		<li class="nav-header">配置</li>
		<li class="${fn:startsWith(requestServletPath, '/console/config/baseConfig/')?'active':''}">
			<a href="console/config/baseConfig"><i class="icon-cog"></i> 基础配置</a>
		</li>
		
		<li class="nav-header">文章</li>
		<li class="${fn:startsWith(requestServletPath, '/console/article/add/')?'active':''}">
			<a href="console/article/add"><i class="icon-pencil"></i> 新文章</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/article/list/')?'active':''}">
			<a href="console/article/list"><i class="icon-list-alt"></i> 文章列表</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/script/list/')?'active':''}">
			<a href="console/script/list"><i class="icon-leaf"></i> 执行脚本</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/article/articleConfig/')?'active':''}">
			<a href="console/article/articleConfig"><i class="icon-retweet"></i> 文章设置</a>
		</li>
		
		<li class="nav-header">文件管理</li>
		<li class="${fn:startsWith(requestServletPath, '/console/file/add/')?'active':''}">
			<a href="console/file/add"><i class="icon-arrow-up"></i> 上传文件</a>
		</li>
		<li class="${fn:startsWith(requestServletPath, '/console/file/list/')?'active':''}">
			<a href="console/file/list"><i class="icon-file"></i> 文件列表</a>
		</li>
		
		<li class="nav-header">域名</li>
		<li class="${fn:startsWith(requestServletPath, '/console/domain/config/')?'active':''}">
			<a href="console/domain/config"><i class="icon-globe"></i> 域名配置</a>
		</li>
		
		<li class="nav-header">留言</li>
		<li class="${fn:startsWith(requestServletPath, '/console/leaveComment/list/')?'active':''}">
			<a href="console/leaveComment/list"><i class="icon-envelope"></i> 留言列表</a>
		</li>
		</shiro:hasRole>
	</ul>
</div>
