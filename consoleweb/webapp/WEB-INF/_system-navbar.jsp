<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp" %>
<div class="well sidebar-nav">
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
		</shiro:hasRole>
	</ul>
</div>
