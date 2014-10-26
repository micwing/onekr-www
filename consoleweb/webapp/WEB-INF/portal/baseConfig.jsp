<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.*" %>
<%@page import="java.util.*" %>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
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
<h4>基础配置</h4>
<form class="form-horizontal" id="modifyForm">
	<label>首页幻灯片</label>
	<pre id="editor"><%= (configs.containsKey(GlobalConstants.OWNER_HOME_SLIDER)) ? htmlspecialchars(configs.get(GlobalConstants.OWNER_HOME_SLIDER).getValue()) : ""%></pre>
	<input type="hidden" id="HOME_SLIDER"/>
	<button type="button" class="btn" onclick="ns.formSubmit()">保存</button>
</form>
<hr>
<h4>示例</h4>
<pre>
&lt;ul class="slides">
	&lt;li>
        &lt;div class="span7 animated fadeInLeftBig">
            &lt;img src="/assets/more/images/1121174058.png">
        &lt;/div>
        &lt;div class="span4 margintop10p animated fadeInDownBig">

            &lt;h3>&lt;i class="icon-tablet">&lt;/i>&lt;i class="icon-mobile-phone">&lt;/i>&lt;i class="icon-desktop">&lt;/i>&lt;br />onekr-basic让您快速搭建J2EE项目&lt;/h3>
        &lt;/div>
    &lt;/li>
    &lt;li>
        &lt;div class="span4 margintop10p animated fadeInDownBig">

            &lt;h3>&lt;i class="icon-tablet">&lt;/i>&lt;i class="icon-mobile-phone">&lt;/i>&lt;i class="icon-desktop">&lt;/i>&lt;br />Hi！ 您可以通过本站了解我和我的软件！&lt;/h3>
        &lt;/div>
        &lt;div class="span7 animated fadeInLeftBig">
            &lt;img src="/assets/more/images/1121174058.png">
        &lt;/div>
    &lt;/li>
	&lt;li>
        &lt;div class="span7 animated fadeInLeftBig">
            &lt;img src="/assets/images/responsive.png">
        &lt;/div>
        &lt;div class="span4 margintop10p animated fadeInDownBig">
            &lt;h3>&lt;i class="icon-tablet">&lt;/i>&lt;i class="icon-mobile-phone">&lt;/i>&lt;i class="icon-desktop">&lt;/i>&lt;br />本站可以在各种分辨率屏幕上浏览&lt;/h3>
        &lt;/div>
    &lt;/li>
&lt;/ul>
</pre>

<script>
var editor = ace.edit("editor");
editor.setTheme("ace/theme/eclipse");
editor.getSession().setMode("ace/mode/html");
editor.setOption("maxLines", 50);
editor.setOption("minLines", 20);
document.getElementById('editor').style.fontSize='14px';

var ns = ns || {};
ns.formSubmit = function() {
	if (!$('#modifyForm').valid()) {
		return false;
	}
	var params = new Array();
	params.push({
		biz:'<%=GlobalConstants.BIZ_SYSTEM%>',
		owner:'<%=GlobalConstants.OWNER_HOME_SLIDER%>',
		value:editor.getValue()
	});
	$.ajax({
		url : "/console/config/doSaveConfig",
		type : 'post',
        dataType : 'json',
		data : {configs: $.toJSON(params)},
		success : function(data) {
			alert(data.message);
			location.href="/console/config/baseConfig";
		}
	});
};
$('#modifyForm').validate({
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


