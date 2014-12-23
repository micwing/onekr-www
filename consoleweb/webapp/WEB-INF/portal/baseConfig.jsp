<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.*" %>
<%@page import="java.util.*" %>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
Map<String,Config> configs = (Map<String,Config>) request.getAttribute("configs");
%>
<script src="assets/js/ace-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
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
&lt;div class="item active">
	&lt;img alt="" src="assets/images/sky1.jpg" style="width: 1170px">
	&lt;div class="carousel-caption">
	&lt;h4>会设计的程序员和会编程的设计师&lt;/h4>
	&lt;p>优秀的设计师和程序员统治着网络–毕竟是他们创建的网站。而那些创建最好玩和最有用的网站的人，往往都是两种技能兼而有之，他们可以将两种完全不同的视觉语言和技术语言运用自如。&lt;/p>
	&lt;/div>
&lt;/div>
&lt;div class="item">
	&lt;img alt="" src="/assets/img/2.jpg" style="width: 1170px">
	&lt;div class="carousel-caption">
	&lt;h4>程序猿的工资级别，请对号入座&lt;/h4>
	&lt;p>码畜：年入低于3万；码奴：年入3万到6万；码农：年入6万到10万；IT民工：年入10万；IT工程师：年入20万；IT人才：年入50万；IT精英：年入百万......&lt;/p>
	&lt;/div>
&lt;/div>
&lt;div class="item">
	&lt;img alt="" src="/assets/img/3.jpg" style="width: 1170px">
	&lt;div class="carousel-caption">
	&lt;h4>别懒&lt;/h4>
	&lt;p>想想吧，因为我们的懒惰，总想着来日方长，做何事都能拖则拖，竟致那么多的计划、旅行、恋爱、对人生的探究未见实行！大难不至，我们就会什么也不做，我们会发现自己又回到日复一日的平庸生活，生活的欲望被消磨殆尽。&lt;/p>
	&lt;/div>
&lt;/div>
&lt;div class="item">
	&lt;img alt="" src="/assets/img/4.jpg" style="width: 1170px">
	&lt;div class="carousel-caption">
	&lt;h4>不争，不辩，不急，不燥&lt;/h4>
	&lt;p>淡泊就是对世间事洞明后的淡然，不争，不辩，不急，不燥，以平常心的心态去面对生活。&lt;/p>
	&lt;/div>
&lt;/div>
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
		url : "console/config/doSaveConfig",
		type : 'post',
        dataType : 'json',
		data : {configs: $.toJSON(params)},
		success : function(data) {
			alert(data.message);
			location.href="${ctx}/console/config/baseConfig";
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


