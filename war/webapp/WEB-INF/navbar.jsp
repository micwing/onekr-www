<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp" %>
        <div class="navbar-wrapper" >
        	<!-- <div class="navbar navbar-static-top " id="navigation"> -->
            <div class="navbar navbar-static-top navbar-inverse" id="navigation">
                <div class="navbar-inner">
                    <div class="container">
                    	<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="brand" href="#">  <img src="/assets/images/logo.png" width="150" id="logokhan"></a>
                        <div class="nav-collapse collapse">
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
                                
                                <li ${fn:startsWith(requestServletPath, '/article/') ? 'class=active' : ''}><a href="article">文章</a></li>
                                
                                <li class="dropdown ${fn:startsWith(requestServletPath, '/domain/') ? 'active' : ''}">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">域名<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                    	<li><a href="/domain/group">组合查询</a></li>
                                        <li><a href="/domain/suffix">多后缀查询</a></li>
                                        <li><a href="/domain/whois">whois查询</a></li>
                                    </ul>
                                </li>
                                
                                <li ${fn:startsWith(requestServletPath, '/contact/') ? 'class=active' : ''}><a href="contact">留言</a></li>
                                
                                <li ${fn:startsWith(requestServletPath, '/about/') ? 'class=active' : ''}><a href="about">关于</a></li>
                                
                                <!-- <li class="dropdown">
                                    <a href="default.htm" class="dropdown-toggle" data-toggle="dropdown">登录<b class="caret"></b></a>
                                    <div class="dropdown-menu" style="padding: 15px;">
                                        <form action="#" method="post" accept-charset="UTF-8" class="form-menu">
                                            <input id="user_username"  type="text" name="user[username]" size="33" placeholder="Username" />
                                            <input id="user_password"  type="password" name="user[password]" size="33" placeholder="Password" />
                                            <label class="checkbox muted hidden-tablet"><input type="checkbox"> Remember Me</label>
                                            <input class="btn span3"  type="submit" name="commit" value="Sign In" />
                                        </form>
										<script type="text/javascript">
										
										</script>
                                    </div>
                                </li> -->

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>