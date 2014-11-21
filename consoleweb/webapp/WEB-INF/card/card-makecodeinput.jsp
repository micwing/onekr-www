<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType" %>
<%@include file="../common/includes.jsp"%>
<h3>输入制作码</h3>
<hr class="head-hr">

<div class="alert">
	<strong>温馨提示</strong><br>
	您需要输入一个32位的制作码才能开始制作请柬<br>
	制作码可以访问<a href="http://onekr.taobao.com" target="_blank">一氪工作室淘宝店</a>购买，或者联系<a target=blank href="http://wpa.qq.com/msgrd?v=3&uin=4990617&site=qq&menu=yes">客服QQ</a>购买
</div>

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<form class="form-search" action="" method="get">
			<div class="input-prepend">
				<span class="add-on">制作码</span>
				<input type="text" class="input-xlarge" id="makecode">
			</div>
			<button type="button" class="btn btn-primary" onclick="doUseCode()">开始制作</button>
		</form>
	</div>
</div>
<script type="text/javascript">
function doUseCode() {
	if ($("#makecode").val() == '') {
		alert("请输入制作码！");
		return false;
	}
	if (!confirm('确定要使用该制作码吗？')) {
		return;
	}
	location.href="${ctx}/card/info/add?makecode="+$("#makecode").val();
}
</script>

