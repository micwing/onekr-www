<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%
	String input_id = request.getParameter("input_id");
	input_id = StringUtils.isEmpty(input_id) ? "input_id" : input_id;
	
	String input_name = request.getParameter("input_name");
	input_name = StringUtils.isEmpty(input_name) ? "" : input_name;
	
	String input_class_pre = request.getParameter("input_class_pre");
	input_class_pre = StringUtils.isEmpty(input_class_pre) ? "" : input_class_pre;
	
	String init_value = request.getParameter("init_value");
	init_value = StringUtils.isEmpty(init_value) ? "0" : init_value;
%>
<div>
	<select id="<%=input_id%>" name="<%=input_name%>"></select>
</div>
<script type="text/javascript">
var CSLR = {};

CSLR.flushProvince = function(code) {
	$$.ajax({
		url:'${ctx}/dashboard/standard/areaList/PROVINCE',
		success:function(list) {
			if (!$$.isEmpty(list)) {
				CSLR.draw(list, '_<%=input_id%>_province', code, function() {
					$('#_<%=input_id%>_province').change(function() {
						var c = $(this).val();
						if (c == -1) {
							CSLR.clear('_<%=input_id%>_city');
							CSLR.clear('<%=input_id%>');
						} else {
							CSLR.flushCity(c);
						}
					});
				});
			}
		}
	});
};
CSLR.flushCity = function(code) {
	$$.ajax({
		url:'${ctx}/dashboard/standard/areaList/CITY',
		success:function(list) {
			if (!$$.isEmpty(list)) {
				CSLR.draw(list, '_<%=input_id%>_city', code, function() {
					$('#_<%=input_id%>_city').change(function() {
						var c = $(this).val();
						if (c == -1) {
							CSLR.clear('<%=input_id%>');
						} else {
							CSLR.flushCounty(c);
						}
					});
				});
			}
		}
	});
};
CSLR.flushCounty = function(code) {
	$$.ajax({
		url:'${ctx}/dashboard/standard/areaList/COUNTY',
		success:function(list) {
			alert(list.length);
			if (!$$.isEmpty(list)) {
				CSLR.draw(list, '_<%=input_id%>_county', code);
			}
		}
	});
};

CSLR.clear = function(selectId, callback) {
	var html = '<option value="-1">请选择<option>';
	$('#'+selectId).html(html);
	if (callback != null)
		callback();
};
CSLR.draw = function(areaList, selectId, selectedCode, callback) {
	var html = '<option value="-1">请选择<option>';
	for(var i = 0; i < areaList.length; i++) {
		var area = areaList[i];
		var selectedInc = '';
		if (selectedCode != null) {
			if (area.code == selectedCode) {
				selectedInc = 'selected="selected"';
			}
		}
		html += '<option '+selectedInc+' value='+area.code+'>'+area.name+'</option>';
	}
	$('#'+selectId).html(html);
	if (callback != null)
		callback();
};

$(function() {
	CSLR.flushProvince();
});
</script>