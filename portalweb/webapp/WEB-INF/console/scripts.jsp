<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.portalservice.model.onekr.commonservice.model.Article" %>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp"%>

<script type="text/javascript" src="/assets/js/syntaxhighlighter/scripts/shCore.js"></script>
<script type="text/javascript" src="/assets/js/syntaxhighlighter/scripts/shBrushGroovy.js"></script>
<link type="text/css" rel="stylesheet" href="/assets/js/syntaxhighlighter/styles/shCore.css"/>
<link type="text/css" rel="stylesheet" href="/assets/js/syntaxhighlighter/styles/shThemeDefault.css"/>
<script type="text/javascript">
SyntaxHighlighter.config.clipboardSwf = '/assets/js/syntaxhighlighter/scripts/clipboard.swf';
SyntaxHighlighter.config.strings.copyToClipboardConfirmation = "已复制到剪贴板！";
</script>

<h4>
<a class="btn" href="/console/script/createScript">新增脚本</a>
</h4>
	<table class="table table-hover">
		<tr>
			<th>标题</th>
			<th>脚本语言</th>
			<th>脚本类型</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${scripts}" var="script" varStatus="st">
		<tr>
			<td>${script.name}</td>
			<td>${script.scriptLanguage.label}</td>
			<td>${script.scriptType.label}</td>
			<td>
			<div class="btn-group">
				<a class="btn" href="/console/script/modifyScript/${script.id}"><i class="icon-eye-open"></i> 修改</a>
				<a class="btn" href="/console/script/executeScript/${script.id}"><i class="icon-eye-open"></i> 执行</a>
				<a class="btn btn-danger" href="/console/script/doDelete/${script.id}" onclick="return window.confirm('确定要删除吗?');"><i class="icon-remove"></i> 删除</a>
			</div>
			</td>
		</tr>
		</c:forEach>
	</table>
	<!-- <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">执行 脚本</h3>
		</div>
		<div class="modal-body">
			<form id="script-form" class="form-horizontal" method="post" action="">
				<input type="hidden" id="scriptId"/>
				<div class="control-group">
					<label class="control-label" for="">标题</label>
					<div class="controls">
						<span id="scriptName"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="">脚步语言</label>
					<div class="controls">
						<span id="scriptType"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="">内容</label>
					<div class="controls" id="code-controls">
				</div>
				</div>
				
				<div id="execute-div">
				<div class="control-group">
					<label class="control-label" for="">URL</label>
					<div class="controls">
						<textarea id="url-textarea" rows="" cols="" placeholder="url">http://blog.csdn.net/micwing/article/details/5261644</textarea>
					</div>
				</div>
				</div>
				
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button id="execute-btn" class="btn btn-primary" onclick="ns.doExceuteArticleScript()">执行</button>
		</div>
	</div>
	<script type="text/javascript">
	var ns = ns || {};
	ns.viewScript = function(scriptId) {
		ns.setScript(scriptId);
		$('#execute-div,#execute-btn').hide();
	    $('#myModal').modal('show');
	};
	ns.executeScript = function(scriptId) {
		ns.setScript(scriptId);
		$('#execute-div,#execute-btn').show();
		$('#myModal').modal('show');
	};
	ns.setScript = function(scriptId) {
		$('#scriptId').val(scriptId);
		$('#scriptName').text('');
		$('#scriptType').text('');
		$('#code-controls').html('');
		$.ajax({
			url : "/console/article/findActionScriptById",
			type : 'post',
	        dataType : 'json',
			data : {scriptId:scriptId},
			success : function(data) {
				var script = data.value;
				$('#scriptName').text(script.name);
				$('#scriptType').text(script.scriptType);
				$('#code-controls').html('<pre id="code" name="code" class="brush: groovy; gutter: true; toolbar:false;"></pre>');
				$('#code').text(script.content);
				
				SyntaxHighlighter.highlight();
			}
		});
	};
	ns.doExceuteArticleScript = function() {
		var scriptId = $('#scriptId').val();
		var arr = new Array();
		arr.push($('#url-textarea').val());
		$.ajax({
			url : "/console/article/doExceuteArticleScript",
			type : 'post',
	        dataType : 'json',
			data : {urls:$.toJSON(arr), scriptId:scriptId},
			success : function(data) {
				alert(data.message);
				location.href="/console/article/scripts";
			}
		});
	};
	</script>
	 -->
	



