<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.Template"%>
<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer" %>
<%@include file="../common/includes.jsp" %>

<div class="row">
	<div class="span3" style="text-align: center;margin-bottom: 30px;background-color: #FFFFF0">
		<div class="thumbnail">
			<img alt="" src="<%=basePath%>qr2dCode?m=<%=basePath%>/card/templatelist&w=200&h=200" />
			<div class="caption">
				<p>建议您通过<span style="font-weight: bold;color: #6B8E23">手机微信</span><br>扫一扫上面的 二维码<br>在手机上浏览全部模板</p>
			</div>
		</div>
	</div>
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
					<p><a target="_blank" href="portal/buymakecode?template=<%=all[i].name()%>">价格：<span style="color: red;font-weight: bold;"><%=all[i].getPrice()%></span> 元</a></p>
				</div>
			</div>
		</div>
		<%
	}
	%>
</div>
