<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<div class="nav-collapse collapse">
	<ul class="nav">
		<li><a href="portal">首页</a></li>
        
        <li class="${fn:startsWith(requestServletPath, '/portal/templatelist/') ? 'active' : ''}"><a href="portal/templatelist">模板列表</a></li>
        
        <li class="${fn:startsWith(requestServletPath, '/portal/buymakecode/') ? 'active' : ''}"><a href="portal/buymakecode">购买制作码</a></li>
        
		<li class="${fn:startsWith(requestServletPath, '/portal/article/') ? 'active' : ''}"><a href="portal/article">帮助中心</a></li>
		
	</ul>
	<ul class="nav pull-right">
		<shiro:user>
			<li class="${fn:startsWith(requestServletPath, '/console/identity/')?'active':''}" title="账户信息"><a href="console/identity/account/accountInfo"><i class="icon-user"></i><span style="margin-left: 5px"><shiro:principal /></span></a></li>
			<li class="${fn:startsWith(requestServletPath, '/console/card/')?'active':''}"><a href="console/card/info/list">我的请柬</a></li>
			<shiro:hasRole name="ADMINISTRATOR">
			<li class="${fn:startsWith(requestServletPath, '/console/system/')?'active':''}"><a href="console/system/config">系统设置</a></li>
			</shiro:hasRole>
			<li class="divider-vertical"></li>
			<li><a href="login/doSignout"><i class="icon-off"></i><span style="margin-left: 5px">退出</span></a></li>
		</shiro:user>
		<shiro:guest> 
			<li><a href="login">登录</a></li>
			<li class="divider-vertical"></li>
			<li><a href="login/register">注册</a></li>
		</shiro:guest>
	</ul>
</div>