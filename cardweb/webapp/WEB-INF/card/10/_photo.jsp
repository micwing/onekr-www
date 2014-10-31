<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<link rel="stylesheet" type="text/css"
	href="assets/js/photoswipe/photoswipe.css" />
<script type="text/javascript"
	src="assets/js/photoswipe/code.photoswipe.jquery-3.0.5.min.js"></script>
<style>
.clr {
	width: 100%;
	clear: both;
	float: none !important
}
</style>

<div class="gallery" id="gallery">
	<ul class="collections">

		<c:forEach items="${photos}" var="fileStore" varStatus="st">
			<li><a
				href="${ctx}${fn:replace(fileStore.storePath, '\\', '/')}"><img
					src="${ctx}${fn:replace(fileStore.storePath, '\\', '/')}" alt=""></a>
			</li>
			<c:if test="${ (st.index > 0) && (st.index%3==2) }">
				<div class="clr"></div>
			</c:if>
		</c:forEach>
	</ul>
</div>
