<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li class="nav-header">index</li>
		<li
			class="${fn:startsWith(requestServletPath, '/card/index/')?'active':''}">
			<a href="/card/index"><i class="icon-home"></i> index</a>
		</li>
		<li class="nav-header">请柬</li>
		<li
			class="${fn:startsWith(requestServletPath, '/card/info/add/')?'active':''}">
			<a href="/card/info/add"><i class="icon-pencil"></i> 新增请柬</a>
		</li>
		<li
			class="${fn:startsWith(requestServletPath, '/card/info/list/')?'active':''}">
			<a href="/card/info/list"><i class="icon-list-alt"></i>
				请柬列表</a>
		</li>
		<li
			class="${fn:startsWith(requestServletPath, '/console/script/list/')?'active':''}">
			<a href="/console/script/list"><i class="icon-leaf"></i> 已停用</a>
		</li>
		
		<li class="nav-header">文件</li>
		<li
			class="${fn:startsWith(requestServletPath, '/console/file/add/')?'active':''}">
			<a href="/console/file/add"><i class="icon-arrow-up"></i> 照片文件</a>
		</li>
		<li
			class="${fn:startsWith(requestServletPath, '/console/file/list/')?'active':''}">
			<a href="/console/file/list"><i class="icon-file"></i> 音乐文件</a>
		</li>
		
		<li class="nav-header">主题</li>
		<li
			class="${fn:startsWith(requestServletPath, '/console/file/add/')?'active':''}">
			<a href="/console/file/add"><i class="icon-arrow-up"></i> 主题列表</a>
		</li>
		<li
			class="${fn:startsWith(requestServletPath, '/console/file/list/')?'active':''}">
			<a href="/console/file/list"><i class="icon-file"></i> 文件列表</a>
		</li>
	</ul>
</div>