<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.*" %>
<%@page import="java.util.*" %>
<%@page import="onekr.commonservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
String searcherName = (String) request.getAttribute("searcherName");
String whoisSearcherName = (String) request.getAttribute("whoisSearcherName");
%>
<h4>域名配置</h4>
<form class="form-horizontal" id="modifyForm">
	<div class="control-group">
		<label class="control-label" for="domainQuery">域名判断查询器</label>
		<div class="controls">
			<select id="domainQuery">
				<option value="<%= GlobalConstants.DOMAIN_PANDA_DOMAIN_SEACHER%>"
				<%= GlobalConstants.DOMAIN_PANDA_DOMAIN_SEACHER.equals(searcherName) ? "selected" : "" %>
				>panda.www.net.cn(http接口)</option>
				<option value="<%= GlobalConstants.DOMAIN_QIUYUMI_DOMAIN_SEACHER%>"
				<%= GlobalConstants.DOMAIN_QIUYUMI_DOMAIN_SEACHER.equals(searcherName) ? "selected" : "" %>
				>秋玉米(http网页)</option>
				<option value="<%= GlobalConstants.DOMAIN_WWW_WHOIS_COM_SEACHER%>"
				<%= GlobalConstants.DOMAIN_WWW_WHOIS_COM_SEACHER.equals(searcherName) ? "selected" : "" %>
				>www.whois.com(http网页)</option>
				<option value="<%= GlobalConstants.DOMAIN_INTERNIC_SEACHER%>"
				<%= GlobalConstants.DOMAIN_INTERNIC_SEACHER.equals(searcherName) ? "selected" : "" %>
				>internic.net(socket接口)</option>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="domainWhoisQuery">域名whois查询器</label>
		<div class="controls">
			<select id="domainWhoisQuery">
				<option value="<%= GlobalConstants.DOMAIN_PANDA_DOMAIN_SEACHER%>"
				<%= GlobalConstants.DOMAIN_PANDA_DOMAIN_SEACHER.equals(whoisSearcherName) ? "selected" : "" %>
				>panda.www.net.cn(http接口)</option>
				<option value="<%= GlobalConstants.DOMAIN_QIUYUMI_DOMAIN_SEACHER%>"
				<%= GlobalConstants.DOMAIN_QIUYUMI_DOMAIN_SEACHER.equals(whoisSearcherName) ? "selected" : "" %>
				>秋玉米(http网页)</option>
				<option value="<%= GlobalConstants.DOMAIN_WWW_WHOIS_COM_SEACHER%>"
				<%= GlobalConstants.DOMAIN_WWW_WHOIS_COM_SEACHER.equals(whoisSearcherName) ? "selected" : "" %>
				>www.whois.com(http网页)</option>
				<option value="<%= GlobalConstants.DOMAIN_INTERNIC_SEACHER%>"
				<%= GlobalConstants.DOMAIN_INTERNIC_SEACHER.equals(whoisSearcherName) ? "selected" : "" %>
				>internic.net(socket接口)</option>
			</select>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button type="button" class="btn" onclick="ns.formSubmit()">保存</button>
		</div>
	</div>
</form>

<script>
var ns = ns || {};
ns.formSubmit = function() {
	if (!$('#modifyForm').valid()) {
		return false;
	}
	var params = new Array();
	params.push({
		biz:'<%=GlobalConstants.BIZ_SYSTEM%>',
		owner:'<%=GlobalConstants.OWNER_DOMAIN_SEARCHER_NAME%>',
		value: $('#domainQuery').val()
	});
	params.push({
		biz:'<%=GlobalConstants.BIZ_SYSTEM%>',
		owner:'<%=GlobalConstants.OWNER_DOMAIN_WHOIS_SEARCHER_NAME%>',
		value: $('#domainWhoisQuery').val()
	});
	$.ajax({
		url : "/console/config/doSaveConfig",
		type : 'post',
        dataType : 'json',
		data : {configs: $.toJSON(params)},
		success : function(data) {
			alert(data.message);
			location.href="/console/domain/config";
		}
	});
};
$('#modifyForm').validate({
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


