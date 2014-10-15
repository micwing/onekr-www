<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.*" %>
<%@page import="onekr.commonservice.utils.GlobalConstants" %>
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
		<label class="control-label" for="">URL</label>
		<div class="controls">
			<input type="text" id="url" class="input-block-level" placeholder="url" value="http://blog.csdn.net/micwing"/>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<input type="button" id="execute-btn" class="btn btn-primary" onclick="ns.doExceuteScript()" value="执行"></input>
		</div>
	</div>
</form>

<script type="text/javascript">
var ns = ns || {};
ns.doExceuteScript = function() {
	var scriptId = $('#scriptId').val();
	var url = $('#url').val();
	$.ajax({
		url : "/console/script/doExceuteUrlScript",
		type : 'post',
        dataType : 'json',
		data : {url:url, scriptId:scriptId},
		success : function(data) {
			alert(data.message);
			if (data.value && data.value.length > 0) {
				for (var i = 0; i < data.value.length; i++) {
					$('#result-table').append(
						'<tr>'
						+'<td><a href="'+data.value[i]+'" target="_blank">' + data.value[i] +'</a></td>'
						+'</tr>'
					);
				}
			}
		}
	});
};
</script>
<hr>
<h4>执行结果</h4>
<table id="result-table" class="table">
</table>
<h4>执行历史</h4>
<table>

</table>