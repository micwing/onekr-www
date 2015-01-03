<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@page import="onekr.commonservice.model.FileStore" %>
<%@page import="onekr.framework.utils.FileUtil" %>
<%@page import="com.alibaba.fastjson.JSONObject" %>
<%@page import="onekr.cardservice.card.intf.CardPhotoFileBiz" %>
<%@page import="onekr.cardservice.model.Card" %>
<%@include file="../common/includes.jsp"%>
<h3>系统音乐</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<form class="form-horizontal" action="console/system/music/doUploadSystemMusic"
			method="post" enctype="multipart/form-data" id="file-form">
			<div class="control-group">
				<label class="control-label" for="file">选择音乐文件</label>
				<div class="controls">
					<input type="file" id="file" name="file" value="选择文件"
						placeholder="file" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input class="btn btn-primary" type="button" id="uploadButton" value="上传音乐" />
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
$('#uploadButton').click(function() {
	if ($('#file-form input[name=file]').val() == '') {
		alert('请选择音乐！');
		return;
	}
	$('#file-form').submit();
});
</script>

<div class="row-fluid">
	<div class="span12">
		<table class="table">
		<%
		Card card = (Card) request.getAttribute("card");
		List<FileStore> list = (List<FileStore>) request.getAttribute("list");
		for (FileStore fs : list) {
		%>
			<tr>
				<td><%=fs.getOriginalName()%></td>
				<td><%=FileUtil.formetFileSize(fs.getSize())%></td>
				<td>
				<a href="console/system/music/doDeleteMusic?fileStoreId=<%=fs.getId() %>">删除</a>
				</td>
			</tr>
		<% } %>
		</table>
	</div>
</div>
