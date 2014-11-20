<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<div>
	<div style="height: 10px;"></div>
	<div>
		<p style="text-align: center">
			<a href="${card.mapUrl}"><img class="mapimg"
				src="${card.mapPicUrl}" style="width: 90%" /></a>
		</p>
		<p style="text-align: center; line-heigt: 30px;">
			${card.restaurant}<a href="${card.mapUrl}"><img
				src="assets/images/yjdh.png" /></a>
		</p>
	</div>
	<div style="height: 10px;"></div>
</div>