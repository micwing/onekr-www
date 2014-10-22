<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<div class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> 
			<a class="brand" href="/">ONEKR│一氪工作室</a>
			
			<div class="nav-collapse">
				<ul class="nav pull-right">
		            <li ${fn:startsWith(requestServletPath, '/home/') ? 'class=active' : ''}><a href="home">首页</a></li>
		
		           <%--  <li class="dropdown ${fn:startsWith(requestServletPath, '/onekr-basic/') ? ' active' : ''}">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">onekr-basic<b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                    <li><a href="/product/introduce">软件介绍</a></li>
		                    <li><a href="http://git.oschina.net/micwing/onekr-basic/repository/archive?ref=master">软件下载</a></li>
		                    <li><a href="http://git.oschina.net/micwing/onekr-basic.git" target="_blank">Fork it on Git</a></li>
		                </ul>
		            </li> --%>
		            
		            <%-- <li ${fn:startsWith(requestServletPath, '/article/') ? 'class=active' : ''}><a href="article">文章</a></li> --%>
		            
		            <li class="dropdown ${fn:startsWith(requestServletPath, '/domain/') ? 'active' : ''}">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">域名<b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                	<li><a href="/domain/group">组合查询</a></li>
		                    <li><a href="/domain/suffix">多后缀查询</a></li>
		                    <li><a href="/domain/whois">whois查询</a></li>
		                    <%-- <li><a href="/domain/expired">过期域名查询</a></li> --%>
		                </ul>
		            </li>
		            
		            <%-- <li ${fn:startsWith(requestServletPath, '/contact/') ? 'class=active' : ''}><a href="contact">留言</a></li> --%>
		            
		            <li><a href="https://github.com/micwing/onekr-www" target="_blank">FORK</a></li>
		            
		            <li><a href="http://wiki.onekr.com" target="_blank">WIKI</a></li>
		            
		            <li ${fn:startsWith(requestServletPath, '/about/') ? 'class=active' : ''}><a href="about">关于</a></li>
		            
		        </ul>
		        
	        	<form class="navbar-search pull-right" action="/domain/whois" method="get">
					<input type="text" class="search-query span2" placeholder="查域名" id="searchInput" name="domain" style="margin-right: 15px;">
	        	</form>
        	</div>
		</div>
	</div>
</div>







