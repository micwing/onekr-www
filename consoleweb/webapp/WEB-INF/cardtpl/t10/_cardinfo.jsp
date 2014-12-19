<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.Card" %>
<%@include file="../../common/includes.jsp" %>
<%
String p1name = ((Card)request.getAttribute("card")).getPeople1Name();
if (p1name.length() == 2) {
	p1name = p1name.substring(0, 1) + "&nbsp;&nbsp;"+ p1name.substring(1,2);
}
String p2name = ((Card)request.getAttribute("card")).getPeople2Name();
if (p2name.length() == 2) {
	p2name = p2name.substring(0, 1) + "&nbsp;&nbsp;"+ p2name.substring(1,2);
}
%>
<div class="">
	<div class="mid">
		<div class="invitext">
			<p class="ip2">${card.beforeInfo}</p>

			<header>
				<div class="eight columns header">
					<span class="date"> <fmt:formatDate value="${card.partyTime}" type="date" pattern="yyyy年M月d日  EEEE"/></span>
					<div class="text clearfix">
						<h1 class="h-left">
							<div>
								<%=p1name %><span>新郎</span>
							</div>
						</h1>
						<h1 class="h-right">
							<div>
								<%=p2name %><span>新娘</span>
							</div>
						</h1>
					</div>
				</div>
			</header>


			<p class="ip2">${card.afterInfo}</p>

			<br>

			<c:if test="${!empty card.partyTime}">
			<p class="ip6" style="line-height: 12px;">
				时间：<span><fmt:formatDate value="${card.partyTime}" type="date" pattern="yyyy年M月d日 HH时mm分"/></span>
			</p>
			</c:if>

			<c:if test="${!empty card.restaurant}">
			<p class="ip5" style="line-height: 16px;">
				席设：<span>${card.restaurant}</span>
			</p>
			</c:if>
			<c:if test="${!empty card.address}">
			<p class="ip5" style="line-height: 16px;">
				地点：<span>${card.address}</span>
			</p>
			</c:if>
			<c:if test="${!empty card.remind}">
			<p class="ip4"
				style="font-size: 17px; line-height: 24px; margin-top: 20px;">
				交通路线:${card.traffic}<br /> 温馨提醒:${card.remind}<br /> <br />
			</p>
			</c:if>

		</div>
	</div>
	<footer> </footer>
</div>