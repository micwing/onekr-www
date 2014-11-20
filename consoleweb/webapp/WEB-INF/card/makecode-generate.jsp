<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType"%>
<%@include file="../common/includes.jsp"%>
<h3>申请制作码</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<form class="form-search" action="" method="post">
			<input type="text" class="input-medium search-query" placeholder="请输入申请人" id="maker">
			<button type="button" class="btn btn-primary" onclick="doGenerateCode()" id="main-btn">申请制作码</button>
		</form>
	</div>
</div>
<script type="text/javascript">
function doGenerateCode() {
	$('#main-btn').attr('disabled', true);
	if ($("#maker").val() == '') {
		alert("请输入申请人");
		$('#main-btn').attr('disabled', false);
		return false;
	}
	if (!confirm('确定要申请制作码吗？')) {
		$('#main-btn').attr('disabled', false);
		return;
	}
	$.ajax({
           type: "post",//使用get方法访问后台
           dataType: "json",//返回json格式的数据
           url: "card/makecode/doGenerate",//要访问的后台地址
           data: {
           	'maker' : $("#maker").val()
           },
           success: function(result){
           	if (result.code == 0) {
            	alert('申请成功！\n制作码为:'+result.value);
            	location.href='card/makecode/list';
           	} else {
           		alert('操作失败！');
           		$('#main-btn').attr('disabled', false);
           	}
           }
	});
}
</script>


