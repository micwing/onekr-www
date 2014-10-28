<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.*" %>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
Script script = (Script) request.getAttribute("script");
%>
<script src="assets/js/ace-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<style>
.ace_editor {
	position: relative !important;
	border: 1px solid lightgray;
    height: 300px;
    width: 96%;
}
</style>
<h4>编辑脚本</h4>
<form id="script-form" method="post" action="console/script/doSave" onsubmit="ns.formSubmit()">
	<input type="hidden" name="id" value="${script == null ? '' : script.id}"/>
	<label>脚步语言</label>
	<select name="scriptLanguage">
		<%  
		ScriptLanguage[] scriptLanguages = ScriptLanguage.values(); 
		for (ScriptLanguage sl : scriptLanguages) {
		%>
		<option value="<%=sl.toString()%>"
			<% if (script != null && script.getScriptLanguage().ordinal() == sl.ordinal()) { %>
				selected="selected"
			<% } %>
			><%=sl.getLabel() %></option>
		<% } %>
	</select>
	<label>脚步类型</label>
	<select name="scriptType">
		<%  
		ScriptType[] scriptTypes = ScriptType.values(); 
		for (ScriptType st : scriptTypes) {
		%>
		<option value="<%=st.toString()%>"
			<% if (script != null && script.getScriptType().ordinal() == st.ordinal()) { %>
				selected="selected"
			<% } %>
			><%=st.getLabel() %></option>
		<% } %>
	</select>
	<label>标题</label>
	<input type="text" id="name" name="name" placeholder="name" class="input-block-level"
		value="<%= (script != null) ? htmlspecialchars(script.getName()) : ""%>" />
	<label>脚本内容</label>
	<pre id="editor"
		><%= (script != null) ? htmlspecialchars(script.getContent()) : ""%></pre>
	<input type="hidden" id="content" name="content"/>
	<br>
	<button type="submit" class="btn btn-primary">保存</button> <button type="button" class="btn" onclick="ns.cancel()">取消</button>
</form>
<script>
var editor = ace.edit("editor");
editor.setTheme("ace/theme/eclipse");
editor.getSession().setMode("ace/mode/groovy");
editor.setOption("maxLines", 50);
editor.setOption("minLines", 20);
document.getElementById('editor').style.fontSize='14px';

var ns = ns || {};
ns.formSubmit = function() {
	if (!$('#script-form').valid()) {
		return false;
	}
	$('#content').val(editor.getValue());
};
ns.cancel = function() {
	if (!window.confirm('确定要取消吗?')) {
		return;
	}
	history.go(-1);
};
$('#script-form').validate({
	rules: {
		name: {
			required: true,
			maxlength:255
		}
	},
	errorClass: "help-inline",
	errorElement: "span",
	highlight:function(element, errorClass, validClass) {
		//$(element).parents('.control-group').removeClass('success');
		//$(element).parents('.control-group').addClass('error');
	},
	unhighlight: function(element, errorClass, validClass) {
		//$(element).parents('.control-group').removeClass('error');
		//$(element).parents('.control-group').addClass('success');
	}
});
</script>
<hr>
<h4>article脚本示例</h4>
<pre
>class ArticleProvider {

    String getTitleExpress(){
       return "#article_details .link_title a";
    }

    String getSummaryExpress(){
       return "";
    }
    
    String getContentExpress(){
       return "#article_content";
    }
    
    String getDateExpress() {
       return "#article_details .article_manage .link_postdate";
    }
    
    String getDateFormatExpress() {
    	return "yyyy-MM-dd HH:mm";
    }
    
}</pre>
<h4>url脚本示例</h4>
<pre
>class UrlProvider {

    String getUrlExpress(){
       return "#article_list .link_title a";
    }
    
    String getPageExpress(){
       return "#papelist a";
    }

}</pre>
