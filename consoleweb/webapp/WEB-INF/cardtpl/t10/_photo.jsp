<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>

<div class="gallery" id="gallery">
	<ul class="collections">

		<c:forEach items="${photos}" var="fileStore" varStatus="st">
			<li><a
				href="attached${fn:replace(fileStore.photo.storePath, '\\', '/')}"><img
					src="attached${fn:replace(fileStore.thumb.storePath, '\\', '/')}" alt=""></a>
			</li>
			<c:if test="${ (st.index > 0) && (st.index%3==2) }">
				<div class="clr"></div>
			</c:if>
		</c:forEach>
	</ul>
</div>
