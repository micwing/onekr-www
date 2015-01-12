<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp" %>
<div class="row">
	
	<div class="span9">
		<div class="well">
			<div class="row-fluid">
				<div class="span12">
				<div class="accordion" id="accordion2">
				<c:forEach items="${page.content}" var="article" varStatus="st">
					<div class="accordion-group">
						<div class="accordion-heading"><h3>
						<a class="accordion-toggle" href="portal/article/detail/${article.id}">
						${fn:replace(
							fn:replace(
				 				fn:replace(
					 				fn:replace(
						 				article.title,
						 				'&',
						 				'&amp;'
									),
					 				'<',
					 				'&lt;'
								),
				 				'>',
				 				'&gt;'
							),
							'"',
							'&quot;'
		                )}</a></h3>
						</div>
					</div>
				</c:forEach>
				</div>
					<section>
						<div class="pagination">
			                <jsp:include page="../util/paging.jsp">
								<jsp:param name="_paging_base_url" value="/portal/article"/>
								<jsp:param name="_paging_size" value="20"/>
								<jsp:param name="_paging_range" value="3"/>
							</jsp:include>
	                    </div>
					</section>
					
				</div>
			</div>
		</div>
	</div>
	
	<div class="span3">
		<jsp:include page="_sidebar.jsp" ></jsp:include>
	</div>
</div>
