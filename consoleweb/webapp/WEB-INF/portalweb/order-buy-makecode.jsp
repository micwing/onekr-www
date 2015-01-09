<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.Template" %>
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
					<form class="form-horizontal" action="console/card/order/doSubmitAlipay" method="post">
					<div class="control-group">
						<label class="control-label" for="">模板</label>
						<div class="controls">
							<select id="template-select" name="template">
								<option value="0">请选择模板</option>
								<%
								for (Template t : Template.values()) {
									%>
									<option price="<%=t.getPrice()%>" value="<%=t.name()%>"><%=t.getLabel()%></option>
									<%
								}
								%>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="">价格</label>
						<div class="controls">
							<label class="checkbox"><span id="price">0</span>元</label>
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
							<button type="submit" class="btn btn-large btn-primary" id="main-btn" disabled="disabled">立即购买<br>并付款</button>
							</shiro:user>
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
<script type="text/javascript">
$('#template-select option').click(function() {
	var pri = $(this).attr('price');
	$('#price').text(pri);
	if (pri > 0) {
		$('#main-btn').attr('disabled', false);
	} else {
		$('#main-btn').attr('disabled', true);
	}
});
$(function() {
	var nme = '${template}';
	if (nme) {
		$('#template-select option[value='+nme+']').attr('selected', true).trigger('click');
	}
});
</script>