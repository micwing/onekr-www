<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<div class="nav-collapse collapse">
	<ul class="nav">
		<li><a href="portal">首页</a></li>
		<%--  
		<li class="dropdown ${fn:startsWith(requestServletPath, '/onekr-basic/') ? ' active' : ''}">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">onekr-basic<b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="/product/introduce">软件介绍</a></li>
                <li><a href="http://git.oschina.net/micwing/onekr-basic/repository/archive?ref=master">软件下载</a></li>
                <li><a href="http://git.oschina.net/micwing/onekr-basic.git" target="_blank">Fork it on Git</a></li>
            </ul>
        </li> --%>
        
        <%-- <li ${fn:startsWith(requestServletPath, '/article/') ? 'class=active' : ''}><a href="article">文章</a></li> --%>
        
        <%-- <li class="dropdown ${fn:startsWith(requestServletPath, '/domain/') ? 'active' : ''}">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">域名<b class="caret"></b></a>
            <ul class="dropdown-menu">
            	<li><a href="/domain/group">组合查询</a></li>
                <li><a href="/domain/suffix">多后缀查询</a></li>
                <li><a href="/domain/whois">whois查询</a></li>
                <li><a href="/domain/expired">过期域名查询</a></li>
            </ul>
        </li> --%>
          
        <li ${fn:startsWith(requestServletPath, '/portal/buymakecode/') ? 'class=active' : ''}><a href="portal/buymakecode">购买制作码</a></li>
        
        <li ${fn:startsWith(requestServletPath, '/portal/article/') ? 'class=active' : ''}><a href="portal/article">请柬教程</a></li>
          
		<li class="${(
		fn:startsWith(requestServletPath, '/console/card/') ||
		fn:startsWith(requestServletPath, '/console/identity/')
		)?'active':''}"><a href="console/card/info/list">我的请柬</a>
		</li>
		<shiro:hasRole name="ADMINISTRATOR">
		<li class="${fn:startsWith(requestServletPath, '/console/system/')?'active':''}"><a href="console/system/config">系统设置</a></li>
		</shiro:hasRole>
	</ul>
	<ul class="nav pull-right">
		<shiro:user>
			<li><a href="console/identity/account/accountInfo"><i class="icon-user"></i> <shiro:principal /></a></li>
			<li class="divider-vertical"></li>
			<li><a href="login/doSignout">退出</a></li>
		</shiro:user>
		<shiro:guest> 
			<li><a href="login">登录</a></li>
			<li class="divider-vertical"></li>
			<li><a href="login/register">注册</a></li>
		</shiro:guest>
	</ul>
</div>