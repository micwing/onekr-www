<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<div>
	<div style="height: 10px;"></div>
	<div>
		<c:if test="${!empty card.mapPicUrl}">
		<p style="text-align: center">
			<a href="${card.mapUrl}"><img class="mapimg"
				src="${card.mapPicUrl}" style="width: 90%" /></a>
		</p>
		</c:if>
		<p style="text-align: center; line-heigt: 30px;">
			${card.restaurant}
			<c:if test="${!empty card.mapUrl}">
			<a href="${card.mapUrl}"><img
				src="assets/images/yjdh.png" /></a>
			</c:if>
		</p>
	</div>
	<div style="height: 10px;"></div>
</div>