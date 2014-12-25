<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp"%>
<footer>
	<div class="container">
	    <div class="row">
	        <div class="span4">
	            <h2>一氪的人生</h2>
	            <!-- <img src="/assets/images/logo.png" > -->
	            <p>人生有两条路<br />一条用心走，叫做梦想<br />一条用脚走，叫做现实<br />心走得太慢，现实会苍白<br />脚走得太慢，梦不会高飞<br />人生的精彩，总是心走得很好，而脚步刚好能跟上<br />掌控好自己的心，让她走正、走好<br />加快自己的步伐，让所有梦想生出美丽的翅膀</p>
	            <p>&nbsp;</p>
	            <p>
	                &copy; <a href="http://www.onekr.com">ONEKR</a> 2013 - <%= onekr.framework.utils.DateUtil.getYear(new java.util.Date()) %>
	            </p>
	        </div>
	        <div class="span2">
	            <h2>链接 </h2>
	            <ul class="unstyled">
            	<li><a href="/">首页</a></li>
	                <%-- <li><a href="/product/introduce">onekr-basic</a></li> --%>
	                <%-- <li><a href="/article">文章</a></li> --%>
	                <%-- <li><a href="/domain/group">组合查询</a></li>
	                <li><a href="/domain/suffix">多后缀查询</a></li>
	                <li><a href="/domain/whois">whois查询</a></li> --%>
	                <%-- <li><a href="/domain/expired">过期域名查询</a></li> --%>
	                <%-- <li><a href="/contact">留言</a></li> --%>
                    <li><a href="/portal/about">关于</a></li>
                </ul>
            </div>
            <div class="span6">
            	<h2>站长社交</h2>
            	<iframe width="100%" height="250" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=250&fansRow=1&ptype=1&speed=0&skin=4&isTitle=1&noborder=1&isWeibo=0&isFans=1&uid=1374641762&verifier=022f8fe1&dpc=1"></iframe>
            </div>
        </div>
    </div>
</footer>