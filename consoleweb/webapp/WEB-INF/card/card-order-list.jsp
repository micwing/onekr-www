<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType"%>
<%@include file="../common/includes.jsp"%>
<h3>订单列表</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
	<c:if test="${!empty page.content}">
	<c:forEach items="${page.content}" var="order" varStatus="st">
		<label>商户订单号：${order.id}，状态：
		<c:if test="${empty order.tradeStatus}">
			<span class="label label-success">无</span>
		</c:if>
		<c:if test="${order.tradeStatus == ''}">
			<span class="label label-important">已用</span>
		</c:if>
		</label>
		<table class="table table-bordered">
			<tr>
				<td>支付宝订单号：${order.tradeNo}</td>
				<td>商品名称：<a href="${order.showUrl}">${order.subject}</a></td>
				<td>商品数量：${order.quantity}</td>
				<td>交易金额：${order.price}元</td>
			</tr>
			<tr>
				<td colspan="2">制作码：${order.remark}</td>
				<td colspan="2">创建时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.createAt}" type="both"/></td>
			</tr>
		</table>
	</c:forEach>
	<jsp:include page="../util/paging.jsp">
		<jsp:param name="_paging_base_url" value="console/card/makecode/list" />
		<jsp:param name="_paging_size" value="20" />
		<jsp:param name="_paging_range" value="3" />
	</jsp:include>
	</c:if>
	<c:if test="${empty page.content}">
	<span style="font-style: italic;">空</span>
	</c:if>
	</div>
</div>
<script type="text/javascript">
function doDeleteCode(code) {
	if (!confirm('确定要删除该制作码吗？')) {
		return;
	}
	if (!confirm('删除制作码时，使用制作码制作的请柬也会一起删除！确定要继续吗？')) {
		return;
	}
	$.ajax({
           type: "post",//使用get方法访问后台
           dataType: "json",//返回json格式的数据
           url: "console/card/makecode/doDelete",//要访问的后台地址
           data: {
           	'makecode' : code
           },
           success: function(result){
           	if (result.code == 0) {
           		alert('删除成功！');
            	location.href='${ctx}/console/card/makecode/list';
           	} else {
           		alert('操作失败！');
           	}
           }
	});
}
</script>



