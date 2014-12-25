<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp" %>
<div class="row">
	
	<div class="span9">
		<div class="well">
			<div class="row-fluid">

                        <div class="span12">

                            <div class="blog-post-title">
                                <h3>${article.title}</h3>
                            </div>

                            <div class="single-blog-meta">
                                <p class="author">
                                <c:if test="${!empty article.author}">作者 ${article.author}</c:if>
								<c:if test="${!empty article.fromUrl}">来自 <a href="${article.fromUrl}" target="_blank">${article.fromUrl}</a></c:if>
								</p>

                            </div>

                            <div class="post-content">
                            	${article.content}
                            </div>
                            <div class="pull-right">
                            	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${article.createAt}" type="both"/>
                            </div>
                            
                            <jsp:include page="../common/baidushare.jsp"></jsp:include>
                        </div>


			</div>
			
			<div class="row-fluid">
                        <div class="span12">

                            <div class="comments">
                                <h3>评论</h3>
                                <c:if test="${empty comments}">
                                	<div class="comment">
                                		<div class="row-fluid">
                                			<div class="span12">
                                			暂无评论
                                			</div>
                                		</div>
                                	</div>
                                </c:if>
                                <c:if test="${!empty comments}">
                                	<c:forEach items="${comments}" var="comment" varStatus="st">
	                                <div class="comment">
	                                    <div class="row-fluid">
	                                        <div class="span2">
	                                            <img src="/assets/images/default-user.jpg" class="img-circle author_pic">
	
	                                            <div class="btn-toolbar">
	                                                <div class="btn-group margintop20">
	                                                    <a href="#" class="btn btn-comments "><i class="icon-white icon-thumbs-up"></i></a>
	                                                    <a href="#" class="btn btn-comments"><i class="icon-white icon-thumbs-down"></i></a>
	                                                </div>
	                                            </div>
	                                        </div>
	
	                                        <div class="span7">
	                                            <div class="name">
	                                                ${comment.userName}
	                                            </div>
	                                            <div class="date">
	                                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${comment.updateAt}" type="both"/>
	                                            </div>
	                                            <div class="response">
	                                                ${fn:replace(
	                                                	fn:replace(
		                                                	fn:replace(
			                                                	fn:replace(
				                                                	comment.content,
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
	                                                )}
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                                </c:forEach>
                                </c:if>
                            </div>

                        </div>
                    </div>

			<div class="row-fluid">
                        <div class="span8">
                            <h3>写评论</h3>
                            <br />
                            <form class="form-comments" _lpchecked="1" action="/article/comment/${article.id}" method="post">

                                <fieldset>

                                    <div class="row-fluid">
                                        <div class="span3">
                                            <label for="name"><span>名称:</span></label>
                                            <div class="input">
                                                <input id="name" name="userName" type="text" value="" class="input-xlarge">

                                            </div>
                                        </div>
                                        <div class="span1">
                                        </div>
                                        <div class="span4">

                                            <label for="name"><span>Email:</span></label>
                                            <div class="input">
                                                <input id="name" name="userEmail" type="text" value="" class="input-xlarge">

                                            </div>
                                        </div>
                                    </div>

                                    <div class="clearfix">
                                        <label for="message"><span>内容:</span></label>
                                        <div class="input">
                                            <textarea tabindex="3" class="input-xlarge" name="content" rows="7"></textarea>
                                        </div>
                                    </div>
                                    
                                    <div class="row-fluid">
                                        <div class="span8">
                                            <label for="name"><span>验证码:</span></label>
                                            <div class="input">
                                                <input name="paptcha" type="text" value="" class="input-xlarge" style="width: 175px">
                                                &nbsp;
                                                <img id="verifyCode" src="/verifyCode/verifyCode.jpg" alt="验证码" title="如果验证码图片无效或看不清楚，请点击换一个验证码。" style="cursor: pointer;height: 50px" onclick="this.src=this.src+'?'+Math.random()"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="actions">
                                        <button tabindex="3" type="submit" class="btn btn-landing ">提交</button>
                                    </div>
                                </fieldset>

                            </form>
                        </div>
                        <div class="span4"></div>
                    </div>
		</div>
	</div>
	
	<div class="span3">
		<div class="well">
			<ul class="nav nav-list">
				<li class="nav-header">教程</li>
				<li>
					<a href="console/card/info/makecodeinput"><i class="icon-plus"></i> 制作电子请柬</a>
				</li>
			</ul>
		</div>
	</div>
</div>


        <div id="wrap">

			<section class="single-blog rev last">

                <div class="container">
                    
                </div>
            </section>
            
            <section class="single-blog comments-section">

                <div class="container">
                    
                </div>

            </section>
            
        </div>