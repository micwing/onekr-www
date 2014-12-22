<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<div class="row">
	
	<div class="span9">
		<div class="well" style="background-color: transparent;">
			<div class="row">
				<div class="span4">
					<img class="img-polaroid" alt="" src="assets/images/sky1.jpg">
				</div>
				<div class="span4">
					<h3>婚礼请柬制作码</h3>
					<hr>
					<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="">价格</label>
						<div class="controls">
							<label class="checkbox">10元</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="">配送</label>
						<div class="controls">
							<label class="checkbox">免运费</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="">数量</label>
						<div class="controls">
							<select class="span1">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select> 件
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for=""></label>
						<div class="controls">
							<button type="button" class="btn btn-large btn-primary">立即购买</button>
						</div>
					</div>
					</form>
					<p>支付&nbsp;&nbsp;&nbsp;&nbsp;支付宝</p>
					<p>提醒&nbsp;&nbsp;&nbsp;&nbsp;此商品为定制，不支持7天无理由退货</p>
				</div>
			</div>
			<div class="row">
				<div class="span8">
				<div><jsp:include page="../common/baidushare.jsp"></jsp:include></div>
				</div>
			</div>
		</div>
		<div class="well" style="background-color: transparent;">
			图文详情
		</div>
		<div class="well" style="background-color: transparent;">
			<a href="/" style="margin-left: 0px;margin-right: 20px">第一次购买制作码？购买教程</a>|
			<a href="/" style="margin-left: 20px;margin-right: 20px">如何制作电子请柬？制作教程</a>|
			<a href="/" style="margin-left: 20px;margin-right: 20px">如何查看电子请柬？使用教程</a>|
			<a href="/" style="margin-left: 20px;margin-right: 20px">第一次访问本站？关于一氪</a>
		</div>
	</div>
	
	<div class="span3">
		<div class="well" style="background-color: transparent;">
			<ul class="nav nav-list">
				<li class="nav-header">制作码</li>
				<li>
					<a href="console/card/info/makecodeinput"><i class="icon-plus"></i> 零售制作码</a>
				</li>
				<li>
					<a href="console/card/info/makecodeinput"><i class="icon-plus"></i> 批发制作码</a>
				</li>
			</ul>
		</div>
	</div>
</div>