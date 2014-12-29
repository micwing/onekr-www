<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@include file="../common/includes.jsp" %>
<div class="row">
	<div class="span12">
		<div class="well">
			<h3>购物车</h3>
			<div class="well">
				
				<table class="table">
					<thead>
						<tr>
							<th style="width: 60px;">商品名称</th>
							<th></th>
							<th>商品单价</th>
							<th>购买数量</th>
							<th>商品总价</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="text-align: left;width: 60px;height: 60px">
							<img class="img-polaroid" alt="" src="assets/images/hlqjads/hlqj.jpg" style="width: 50px">
							</td>
							<td style="text-align: left;">
							<a href="portal/buymakecode" target="_blank">婚礼请柬制作码</a>
							</td>
							<td>10 元</td>
							<td>1 件</td>
							<td>10 * 1 = 10 元</td>
						</tr>
						<tr>
							<td colspan="0">
								<div class="pull-right">
									<h3>合计：<span style="color: red">10</span> 元
									<shiro:guest>
										<a href="login" class="btn btn-large btn-primary">请先登录</a>
									</shiro:guest>
									<shiro:user>
										<form action="console/order/doSubmitAlipay" method="post">
										<button type="submit" class="btn btn-large btn-primary">立即购买</button>
										</form>
									</shiro:user>
									</h3>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div>
			
		</div>
	</div>
	
</div>
