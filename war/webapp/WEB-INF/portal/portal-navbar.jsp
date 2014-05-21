<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<div class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="/">ONEKR│一刻工作室</a>
			
			<ul class="nav pull-right">
	            <li ${fn:startsWith(requestServletPath, '/home/') ? 'class=active' : ''}><a href="home">首页</a></li>
	
	           <%--  <li class="dropdown${fn:startsWith(requestServletPath, '/onekr-basic/') ? ' active' : ''}">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown">onekr-basic<b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                    <li><a href="/product/introduce">软件介绍</a></li>
	                    <li><a href="http://git.oschina.net/micwing/onekr-basic/repository/archive?ref=master">软件下载</a></li>
	                    <li><a href="http://git.oschina.net/micwing/onekr-basic.git" target="_blank">Fork it on Git</a></li>
	                </ul>
	            </li> --%>
	            
	            <%-- <li ${fn:startsWith(requestServletPath, '/article/') ? 'class=active' : ''}><a href="article">文章</a></li> --%>
	            
	            <li class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown">教程<b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                	<li><a href="http://bootstrap2.onekr.com" target="_blank">Bootstrap v2</a></li>
	                </ul>
	            </li>
	            
	            <li class="dropdown ${fn:startsWith(requestServletPath, '/domain/') ? 'active' : ''}">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown">域名<b class="caret"></b></a>
	                <ul class="dropdown-menu">
	                	<li><a href="/domain/group">组合查询</a></li>
	                    <li><a href="/domain/suffix">多后缀查询</a></li>
	                    <li><a href="/domain/whois">whois查询</a></li>
	                </ul>
	            </li>
	            
	            <%-- <li ${fn:startsWith(requestServletPath, '/contact/') ? 'class=active' : ''}><a href="contact">留言</a></li> --%>
	            
	            <li ${fn:startsWith(requestServletPath, '/about/') ? 'class=active' : ''}><a href="about">关于</a></li>
	            
	        </ul>
	        
        	<form class="form-search form-inline pull-right" action="/domain/whois" method="get" style="margin: 5px 15px 0 15px;">
			<input type="text" class="input-medium search-query span2" placeholder="查域名" id="searchInput" name="domain">
        	</form>
		</div>
	</div>
</div>







