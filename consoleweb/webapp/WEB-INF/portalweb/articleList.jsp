<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp" %>
<div class="row">
	
	<div class="span9">
		<div class="well" style="background-color: transparent;">
			<div class="row-fluid">
				<div class="span12">
					<c:forEach items="${page.content}" var="article" varStatus="st">
		            <section class="blog">
		                    <div class="row-fluid">
		                        <div class="span4">
		                            <div class="post-image" style="width: 100%">
		                                <img src="${empty article.imageUrl ? '/assets/images/project1.jpg' : article.imageUrl}" alt="">
		                            </div>
		                        </div>
		                        <div class="span8">
		                            <div class="row-fluid">
		                                <h3><a href="/portal/article/detail/${article.id}">${fn:replace(
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
		                            <div class="row-fluid">
		                                
		                                <p class="span1" title="有${article.totalComment}个评论"><i class="icon-comment"></i> ${article.totalComment}</p>
		                                <p class="span1" title="浏览${article.totalViewCount}次"><i class="icon-eye-open"></i> ${article.totalViewCount}</p>
		
		                            </div>
		
		                            <div class="row-fluid">
		
		                                <p>${fn:replace(
											fn:replace(
								 				fn:replace(
									 				fn:replace(
										 				article.summary,
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
						                )}...</p>
		                                <a href="/portal/article/detail/${article.id}" class="pull-right">详情 <i class="icon-chevron-right"></i></a>
		
		                            </div>
		                        </div>
		
		
		                    </div>
		            </section>   
		            </c:forEach>
		
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
		<div class="well" style="background-color: transparent;">
			<ul class="nav nav-list">
				<li class="nav-header">教程</li>
				<li>
					<a href="console/card/info/makecodeinput"><i class="icon-plus"></i> 制作电子请柬</a>
				</li>
			</ul>
		</div>
	</div>
</div>







     