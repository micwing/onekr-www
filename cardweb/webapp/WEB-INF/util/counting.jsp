<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%
	String input_id = request.getParameter("input_id");
	
	String input_name = request.getParameter("input_name");
	input_name = StringUtils.isEmpty(input_name) ? "" : input_name;
	
	String init_value = request.getParameter("init_value");
	init_value = StringUtils.isEmpty(init_value) ? "1" : init_value;
	
	String min = request.getParameter("min");
	min = StringUtils.isEmpty(min) ? "1" : min;
	
	String max = request.getParameter("max");
	max = StringUtils.isEmpty(max) ? "999" : max;
	
	String step = request.getParameter("step");
	step = StringUtils.isEmpty(step) ? "1" : step;
%>
<div class="input-prepend input-append">
	<span class="add-on" onclick="$$.countingReduce('<%=input_id%>', <%=step%>, <%=min%>);" style="cursor: pointer;-moz-user-select: none;-khtml-user-select: none;user-select: none;">-</span>
	<input type="text" id="<%=input_id%>" name="<%=input_name%>" style="width: 24px;text-align: center;" value="<%=init_value%>" onchange="$$.countingCheckNum('<%=input_id%>', '<%=init_value%>');">
	<span class="add-on" onclick="$$.countingAdd('<%=input_id%>', <%=step%>, <%=max%>);   " style="cursor: pointer;-moz-user-select: none;-khtml-user-select: none;user-select: none;">+</span>
</div>