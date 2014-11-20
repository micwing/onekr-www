<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@page import="onekr.commonservice.model.FileStore" %>
<%@page import="onekr.framework.utils.FileUtil" %>
<%@page import="onekr.cardservice.model.Card" %>
<%@page import="onekr.cardservice.card.intf.CardMusicFileBiz" %>
<%@include file="../common/includes.jsp"%>
<h3>请柬音乐
<span class="pull-right">
    <span class="btn-group">
		<a class="btn" href="card/map/cardmap/${card.id}">上一步设置地图</a>
    </span>
    <span class="btn-group">
		<a class="btn" href="card/2dcode/index/${card.id}">下一步查看二维码</a>
    </span>
</span>
</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<form class="form-horizontal" action="card/music/doUploadFile"
			method="post" enctype="multipart/form-data" id="file-form">
			<div class="control-group">
				<label class="control-label" for="file">选择音乐文件</label>
				<div class="controls">
					<input type="hidden" name="cardId" value="${card.id}" /> <input
						type="file" id="file" name="file" multiple value="选择文件"
						accept="audio/*"
						placeholder="file" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input class="btn btn-primary btn-large" type="button" id="uploadButton" value="上传音乐" />
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
	$('#uploadButton').attr('disabled', true);
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
				<td>
				<%=fs.getOwner().equals(CardMusicFileBiz.SYSTEM_MUSIC_FILE_STORE_OWNER) ? "<span class='label'>系统音乐</span>" : "<span class='label label-info'>我的音乐</span>"%>
				<%=fs.getOriginalName()%></td>
				<td><%=FileUtil.formetFileSize(fs.getSize())%></td>
				<td>
				<%
				if (card.getMusicFileStore() != null && fs.getId().equals(card.getMusicFileStore().getId())) {
				%>
				<span class="label label-success">已选中</span>
				<% } else { %>
				<a href="card/music/doSetMusic?cardId=${card.id}&fileStoreId=<%=fs.getId() %>">选择</a>
				<% } %>
				</td>
				<td>
				<a href="card/music/doDeleteMusic?cardId=${card.id}&fileStoreId=<%=fs.getId() %>">删除</a>
				</td>
			</tr>
		<% } %>
		</table>
	</div>
</div>
