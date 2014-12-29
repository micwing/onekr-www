<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.Template"%>
<%@include file="../common/includes.jsp" %>
<div class="row-fluid">
	<div id="myCarousel" class="carousel slide">
		<div class="carousel-inner">
			<div class="item active">
				<img class="img-rounded" alt="" src="assets/images/slider/01.jpg" style="width: 1170px">
				<div class="carousel-caption">
				<h4>简单几步，制作自己的微信婚礼请柬</h4>
				<p>填写资料、上传照片、酒店位置、设置音乐，只需要简单几步就可以制作出微信电子请柬，再扫一扫请柬的二维码，把电子请柬发送给好友们！</p>
				</div>
			</div>
			<div class="item">
				<img class="img-rounded" alt="" src="assets/images/slider/02.jpg" style="width: 1170px">
				<div class="carousel-caption">
				<h4>一辈子的约定，我就要高大上</h4>
				<p>各种请柬模板随意换，不仅手机微信可以看，手机浏览器、平板电脑、PC电脑也都可以！</p>
				</div>
			</div>
		</div>
		<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>
</div>
<script>
$('.carousel').carousel({
	interval: 10000
});
</script>

<div class="row-fluid">
	<div class="span12">
	<span style="font-size: 20px;font-weight: bold;">热门模板</span>
	<span class="pull-right"><a href="portal/templatelist">更多模板>></a></span>
	</div>
</div>

<div class="row">
	<%
	Template[] all = Template.values();
	
	for (int i = 0; i < all.length; i++) {
		if (!all[i].getIsHot()) {
			continue;
		}
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