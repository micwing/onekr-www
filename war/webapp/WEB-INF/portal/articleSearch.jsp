<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.biz.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp" %>
        <div id="wrap">

            <!--################ HEADER START ################-->

            <header class="page-title">
                <div class="container">
                    <div class="pagination" style="text-align: right;width: 380px;float: right !important;margin: 10px 0 0 0">
	                <jsp:include page="../util/paging.jsp">
						<jsp:param name="_paging_base_url" value="/article?"/>
						<jsp:param name="_paging_size" value="10"/>
						<jsp:param name="_paging_range" value="3"/>
					</jsp:include>
                    </div>
                    <input type="text" class="input-large search-query pull-right margintop10" id="searchInput" placeholder="回车提交搜文章" value="${keys}">
                    <script>
					$('#searchInput').keyup(function(event) {
						 if(event.keyCode == 13){
					         location.href="/article/search/"+$(this).val();
					     }
					});
					</script>
                    <h2>搜索</h2>
                    
                </div>
            </header>
            
            <c:if test="${!empty articles}">
            <c:forEach items="${articles}" var="article" varStatus="st">
            <section class="blog">
                <div class="container">
                    <div class="row">
                        <div class="span4">
                            <div class="post-image" style="width: 100%">
                                <img src="${empty article.imageUrl ? '/assets/images/project1.jpg' : article.imageUrl}" alt="">
                            </div>
                        </div>
                        <div class="span8">
                            <div class="blog-post-title">
                                <h3><a href="/article/detail/${article.id}">${fn:replace(
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
                            <div class="single-blog-meta">
                                <p class="author">
								<c:if test="${empty article.author}">作者 <a href="/about"><%= GlobalConstants.ADMIN_USER_NAME %></a></c:if>
								<c:if test="${!empty article.author}">来自 <a href="${article.fromUrl}" target="_blank">${fn:replace(
									fn:replace(
						 				fn:replace(
							 				fn:replace(
								 				article.author,
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
				                )}</a></c:if>
                                </p>

                                <div class="socials">
                                </div>
                            </div>

                            <div class="post-content">

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
                                <a href="/article/detail/${article.id}" class="pull-right">详情 <i class="icon-chevron-right"></i></a>

                            </div>
                        </div>


                    </div>
                </div>
            </section>   
            </c:forEach>
			</c:if>
			<c:if test="${empty articles}">
				<div class="container">
                    <div class="row">
                        <div class="span12">
                            <div class="blog-post-title">
                                <h3>很遗憾，没有找到相关文章!</h3>
                            </div>
                        </div>

                    </div>
                </div>
			</c:if>
			
			<section>
				<div class="container">
		        	<div class="pagination" style="text-align: right;">
	                <jsp:include page="../util/paging.jsp">
						<jsp:param name="_paging_base_url" value="/article?"/>
						<jsp:param name="_paging_size" value="10"/>
						<jsp:param name="_paging_range" value="3"/>
					</jsp:include>
                    </div>
				</div>
			</section>
        </div>