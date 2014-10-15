<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.*" %>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp"%>
<h4>
执行${script.scriptType.label}脚本
</h4>
<form id="script-form" class="form-horizontal" method="post" action="">
	<input type="hidden" id="scriptId" value="${script.id}"/>
	<div class="control-group">
		<label class="control-label" for="">标题</label>
		<div class="controls">${script.name}</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">脚本语言</label>
		<div class="controls">${script.scriptLanguage.label}</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">脚本类型</label>
		<div class="controls">${script.scriptType.label}</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">来自</label>
		<div class="controls">
			<input type="text" id="author" value="${script.name}"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="">URL</label>
		<div class="controls">
		    <ul class="nav nav-tabs" id="get-url">
			    <li class="active"><a href="#givingUrl" data-toggle="tab">指定URL</a></li>
			    <li><a href="#scriptUrl" data-toggle="tab">通过预置url脚本</a></li>
		    </ul>
		    <div class="tab-content">
				<div class="tab-pane active" id="givingUrl">
					<textarea id="url-textarea" rows="5" cols="5" class="span6" placeholder="url"></textarea>
				</div>
				<div class="tab-pane" id="scriptUrl">
					<label>预置url脚本Id</label>
					<div class="input-append">
						<input type="text" id="preScriptId" placeholder="preScriptId" value=""/>
						<a href="#myModal" role="button" data-toggle="modal"><span class="add-on"><i class="icon-search"></i></span></a>
					</div>
					<label>入口页面url</label><input type="text" id="preUrl" class="input-block-level" placeholder="preUrl" value=""/>
				</div>
			</div>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<input type="button" id="execute-btn" class="btn btn-primary" onclick="ns.doExceuteScript()" value="执行"></input>
		</div>
	</div>
</form>
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">选择url脚本</h3>
	</div>
	<div class="modal-body">
		<table class="table table-hover table-bordered table-striped">
			<tr><th style="width: 20px"></th><th>脚本名称</th></tr>
			<c:forEach items="${urlScripts}" var="us" varStatus="st">
				<tr><td style="width: 20px"><input type="radio" name="us" value="${us.id}"/></td><td>${us.name}</td></tr>
			</c:forEach>
		</table>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		<button class="btn btn-primary" onclick="ns.selectUrlScript()">确定</button>
	</div>
</div>
<script type="text/javascript">
var ns = ns || {};
ns.selectUrlScript = function() {
	var id = $('#myModal input:radio:checked').val();
	$('#preScriptId').val(id);
	$('#myModal').modal('hide');
};
ns.doExceuteScript = function() {
	var scriptId = $('#scriptId').val();
	var arr = $('#url-textarea').val().split('\n');
	var author = $('#author').val();
	var preScriptId = $('#preScriptId').val();
	var preUrl = $('#preUrl').val();
	
	var params = null;
	var hrf = $('#get-url .active a').attr('href');
	if (hrf == '#givingUrl') {
		params = {urls:$.toJSON(arr), author:author, scriptId:scriptId};
	} else if (hrf == '#scriptUrl') {
		params = {author:author, scriptId:scriptId, preScriptId:preScriptId, preUrl:preUrl};
	}
	$.ajax({
		url : "/console/script/doExceuteArticleScript",
		type : 'post',
        dataType : 'json',
		data : params,
		success : function(data) {
			alert(data.message);
			location.href="/console/script/executeScript/${script.id}";
		}
	});
};
</script>
<hr>
<h4>执行历史</h4>
<table>

</table>