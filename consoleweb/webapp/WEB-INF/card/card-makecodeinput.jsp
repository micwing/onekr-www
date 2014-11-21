<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType" %>
<%@include file="../common/includes.jsp"%>
<h3>申请制作码</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<form class="form-search" action="" method="get">
			<div class="input-prepend">
				<span class="add-on">请输入制作码</span>
				<input type="text" class="input-xlarge" id="makecode">
			</div>
			<button type="button" class="btn btn-primary" onclick="doUseCode()">确定</button>
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
	location.href="card/info/add?makecode="+$("#makecode").val();
}
</script>

