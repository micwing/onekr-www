<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@page import="onekr.commonservice.model.FileStore" %>
<%@page import="onekr.framework.utils.FileUtil" %>
<%@page import="com.alibaba.fastjson.JSONObject" %>
<%@page import="onekr.cardservice.card.intf.CardFileBiz" %>
<%@include file="../common/includes.jsp"%>
<h3>请柬音乐
<span class="pull-right">
    <span class="btn-group">
		<a class="btn" href="card/map/cardmap/${card.id}">上一步设置地图</a>
    </span>
</span>
</h3>
<hr class="head-hr">

<div class="row-fluid">
	<div class="span12">
		<form class="form-horizontal" action="card/music/doUploadFile"
			method="post" enctype="multipart/form-data" id="file-form">
			<div class="control-group">
				<label class="control-label" for="file">选择音乐文件</label>
				<div class="controls">
					<input type="hidden" name="cardId" value="${card.id}" /> <input
						type="file" id="file" name="file" multiple value="选择文件"
						placeholder="file" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input class="btn btn-primary" type="submit" id="uploadButton" value="上传音乐" />
				</div>
			</div>
		</form>
	</div>
</div>

<div class="row-fluid">
	<div class="span12">
		<table class="table">
		<% List<FileStore> list = (List<FileStore>) request.getAttribute("list");
		   for (FileStore fs : list) {
		%>
			<tr>
				<td><%=fs.getOriginalName() %></td>
				<td><%=FileUtil.formetFileSize(fs.getSize()) %></td>
				<td>
				<% 
				JSONObject json = JSONObject.parseObject(fs.getJson());
				if (json != null && json.getBooleanValue(CardFileBiz.CARD_MUSIC_JSON_ATTR_KEY_USE) ) {
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
