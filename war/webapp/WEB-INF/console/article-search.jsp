<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.*" %>
<%@page import="java.util.*" %>
<%@page import="onekr.commonservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
Map<String,Config> configs = (Map<String,Config>) request.getAttribute("configs");
%>
<script src="/assets/js/ace-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<style>
.ace_editor {
	position: relative !important;
	border: 1px solid lightgray;
    height: 300px;
}
</style>
<h4>文章设置</h4>
<form class="form-horizontal" id="modifyForm">
	<div class="control-group">
		<label class="control-label" for="file">更新文章索引</label>
		<div class="controls">
			<button type="button" class="btn btn-primary" onclick="ns.doUpdateIndex()">立刻更新</button>
		</div>
	</div>
</form>

<script>

var ns = ns || {};
ns.doUpdateIndex = function() {
	if (!window.confirm('确定要更新吗?')) {
		return;	
	}
	$.ajax({
		url : "/console/article/doIndex",
		type : 'post',
        dataType : 'json',
		data : {},
		success : function(data) {
			alert(data.message);
		}
	});
};
</script>


