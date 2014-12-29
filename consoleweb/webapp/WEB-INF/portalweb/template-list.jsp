<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.Template"%>
<%@include file="../common/includes.jsp" %>
<div class="row">
	<%
	Template[] all = Template.values();
	
	for (int i = 0; i < all.length; i++) {
		%>
		<div class="span3" style="text-align: center;margin-bottom: 30px;background-color: #FFFFF0">
			<div class="thumbnail">
				<a target="_blank" href="portal/frame/<%=all[i].getExampleOrderId()%>">
				<img class="img-rounded" alt="<%=all[i].getLabel()%>" src="assets/images/templatelist/<%=all[i].name()%>.jpg">
				</a>
				<div class="caption">
					<h4><a target="_blank" href="portal/frame/<%=all[i].getExampleOrderId()%>"><%=all[i].getLabel()%></a></h4>
					<p><a target="_blank" href="portal/buymakecode">价格：<span style="color: red;font-weight: bold;"><%=all[i].getPrice()%></span> 元</a></p>
				</div>
			</div>
		</div>
		<%
	}
	%>
</div>
