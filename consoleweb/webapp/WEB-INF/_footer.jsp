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
	                <li><a href="portal/templatelist">模板列表</a></li>
	                <li><a href="portal/buymakecode">购买制作码</a></li>
	                <li><a href="console/card/info/list">我的请柬</a></li>
	                <li><a href="console/identity/account/accountInfo">我的账户</a></li>
	                <li><a href="http://onekr.taobao.com" target="_blank">访问淘宝店</a></li>
	                <li>
	                <a href="#weixindianModal" role="button" data-toggle="modal">访问微信店</a>
					<div id="weixindianModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">访问 一氪软件工作室 微信店</h3>
						</div>
						<div class="modal-body" style="text-align: center">
							<p>请使用手机微信扫描该二维码</p>
							<p><img class="img-polaroid" alt="" src="assets/images/weixindian.png"/></p>
							<!-- <p><a href="http://wap.koudaitong.com/v2/showcase/homepage?kdt_id=651908" target="_blank">PC查看</a></p> -->
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
						</div>
					</div>
	                </li>
	                <li>
						<a href="mailto:onekrstudio@163.com">发送邮件</a>
					</li>
					<li>
						<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=4990617&site=qq&menu=yes">QQ咨询</a>
					</li>
                    <li><a href="/portal/about">关于一氪</a></li>
                </ul>
            </div>
            <div class="span6">
            	<h2>站长社交</h2>
            	<iframe width="100%" height="250" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=250&fansRow=1&ptype=1&speed=0&skin=4&isTitle=1&noborder=1&isWeibo=0&isFans=1&uid=1374641762&verifier=022f8fe1&dpc=1"></iframe>
            </div>
        </div>
    </div>
</footer>