<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.*" %>
<%@page import="java.util.*" %>
<%@page import="onekr.commonservice.biz.Biz" %>
<%@page import="onekr.commonservice.biz.ConfigOwner" %>
<%@include file="../common/includes.jsp"%>
<%
Map<String, Config> configs = (Map<String, Config>) request.getAttribute("configs");
request.setCharacterEncoding("UTF-8");
List<String[]> configlist = new ArrayList<String[]>();
configlist.add(new String[]{ConfigOwner.SYSTEM_EMAIL_SERVER.name(),"邮件服务器地址"});
configlist.add(new String[]{ConfigOwner.SYSTEM_EMAIL_PORT.name(),"端口"});
configlist.add(new String[]{ConfigOwner.SYSTEM_EMAIL_USERNAME.name(),"用户名"});
configlist.add(new String[]{ConfigOwner.SYSTEM_EMAIL_PASSWORD.name(),"密码"});
configlist.add(new String[]{ConfigOwner.SYSTEM_EMAIL_DEFAULTENCODING.name(),"编码"});
%>
<h3>邮件服务器</h3>
<hr class="head-hr">

<form class="form-horizontal" id="main-form">
<%
for (String[] arr : configlist) {
%>
	<div class="control-group">
		<label class="control-label" for="arr[0]"><%=arr[1]%></label>
		<div class="controls">
			<input type="text" id="<%=arr[0]%>" name="arr[0]" value="<%=((Config)configs.get(arr[0])).getValue()%>"/>
		</div>
	</div>
<%}%>
	<div class="control-group">
		<div class="controls">
			<button type="button" class="btn btn-primary" onclick="ns.formSubmit()">保存</button>
		</div>
	</div>
</form>

<script>
var ns = ns || {};
ns.formSubmit = function() {
	if (!$('#main-form').valid()) {
		return false;
	}
	var params = new Array();
	
	<%
	for (String[] arr : configlist) {
	%>
	params.push({
		biz:'<%=Biz.SYSTEM.name()%>',
		owner:'<%=arr[0]%>',
		value: $('#<%=arr[0]%>').val()
	});
	<%}%>
	
	$.ajax({
		url : "console/config/doSaveConfig",
		type : 'post',
        dataType : 'json',
		data : {configs: $.toJSON(params)},
		success : function(data) {
			alert(data.message);
		}
	});
};
$('#main-form').validate({
	rules: {
		/* name: {
			required: true,
			maxlength:255
		} */
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


