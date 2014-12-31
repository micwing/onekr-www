<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<div class="row">
	<div class="span9">
		<div class="well">
			<div class="row">
				<div class="span4">
					<img class="img-polaroid" alt="" src="assets/images/hlqjads/hlqj.jpg">
				</div>
				<div class="span4">
					<h3>婚礼请柬制作码</h3>
					<hr>
					<div class="form-horizontal">
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
							<label class="checkbox">1 件</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for=""></label>
						<div class="controls">
							<shiro:guest>
							<a href="login" class="btn btn-large btn-primary">请先登录</a>
							</shiro:guest>
							<shiro:user>
							<form action="console/order/doSubmitAlipay" method="post">
							<button type="submit" class="btn btn-large btn-primary">立即购买<br>并前往支付宝付款</button>
							</form>
							</shiro:user>
						</div>
					</div>
					</div>
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
		<div class="well">
			<div class="row-fluid">
				<div class="span12">
					<div>
						<h3>宝贝详情</h3>
					</div>
					<hr>
				</div>
				<div class="span12" style="text-align: center;">
					<div>
						<img alt="" src="assets/images/hlqjads/01.jpg">
					</div>
					<div>
						<img alt="" src="assets/images/hlqjads/02.jpg">
					</div>
					<div>
						<img alt="" src="assets/images/hlqjads/03.jpg">
					</div>
					<div>
						<img alt="" src="assets/images/hlqjads/04.jpg">
					</div>
					<div>
						<img alt="" src="assets/images/hlqjads/05.jpg">
					</div>
					<div>
						<img alt="" src="assets/images/hlqjads/06.jpg">
					</div>
					<div>
						<img alt="" src="assets/images/hlqjads/07.jpg">
					</div>
					<div>
						<img alt="" src="assets/images/hlqjads/08.jpg">
					</div>
					<div>
						<img alt="" src="assets/images/hlqjads/09.jpg">
					</div>
					<!-- <div>
						<img alt="" src="assets/images/hlqjads/10.jpg">
					</div> -->
					<div>
						<img alt="" src="assets/images/hlqjads/11.jpg">
					</div>
					<!-- <div>
						<img alt="" src="assets/images/hlqjads/12.jpg">
					</div> -->
					<div>
						<img alt="" src="assets/images/hlqjads/13.jpg">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="span3">
		<jsp:include page="_sidebar.jsp" ></jsp:include>
	</div>
</div>