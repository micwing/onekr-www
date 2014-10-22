<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<div class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="#">ONEKR CONSOLE</a>
			<%-- <div class="nav-collapse collapse">
				<ul class="nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li class="nav-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
			 --%>
			<ul class="nav pull-right">
				<li><a href="/console/account/accountInfo"><shiro:principal /></a></li>
				<li><a href="/" target="_blank">查看前台</a></li>
				<li><a href="https://login.secureserver.net" target="_blank">系统邮箱</a></li>
				<li><a href="/login/doSignout">退出</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>


