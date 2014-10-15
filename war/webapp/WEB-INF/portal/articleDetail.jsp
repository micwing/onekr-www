<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp" %>

        <div id="wrap">

            <!--################ HEADER START ################-->

            <header class="page-title">
                <div class="container">
                	<input type="text" class="input-large search-query pull-right margintop10" placeholder="搜本站">
                    <h2>阅读文章</h2>
                </div>
            </header>
            <section class="single-blog">
                <div class="container">
                    <div class="row">

                        <div class="span8">

                            <div class="blog-post-title">
                                <h3>${article.title}</h3>
                            </div>

                            <div class="single-blog-meta">
                                <p class="author">
                                <c:if test="${empty article.author}">作者 <a href="/about"><%= GlobalConstants.ADMIN_USER_NAME %></a></c:if>
								<c:if test="${!empty article.author}">来自 <a href="${article.fromUrl}" target="_blank">${article.author}</a></c:if>
								</p>

                                <div class="socials">
                                	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${article.createAt}" type="both"/>
                                </div>
                            </div>

                            <div class="post-content">
                            	${article.content}
                            </div>
							<div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a><a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a><a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a><a title="分享到人人网" href="#" class="bds_renren" data-cmd="renren"></a><a title="分享到网易微博" href="#" class="bds_t163" data-cmd="t163"></a></div>
							<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","t163"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","t163"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=86835285.js?cdnversion='+~(-new Date()/36e5)];</script>
                        </div>


                        <div class="span4">  
                            <div class="sidebar">
                                <div class="widget">
                                    <h4>Polls</h4>
                                    <h5>Poll: Where do you usually browse</h5>

                                    <strong>Mac</strong><span class="pull-right">40%</span>
                                    <div class="progress progress-info active">
                                        <div class="bar" style="width: 40%;"></div>
                                    </div>
                                    <strong>iPad/iPhone</strong><span class="pull-right">10%</span>
                                    <div class="progress progress-striped active">
                                        <div class="bar" style="width: 10%;"></div>
                                    </div>
                                    <strong>Android</strong><span class="pull-right">5%</span>
                                    <div class="progress progress-success active">
                                        <div class="bar" style="width: 5%;"></div>
                                    </div>
                                    <strong>Others</strong><span class="pull-right">15%</span>
                                    <div class="progress progress-warning active">
                                        <div class="bar" style="width: 15%;"></div>
                                    </div>
                                    <p>
                                        <a href="#" class="btn btn-normal">Vote</a>
                                        <a href="#" class="pull-right">View detailed results</a>
                                    </p>



                                </div>

                                <div class="widget">
                                    <h4>订阅</h4>

                                    <form accept-charset="UTF-8" action="" method="post">

                                        <input class="span3" name="username" placeholder="Email" type="text">
                                        <br />
                                        <button class="btn btn-normal" type="submit">提交</button>
                                    </form>



                                </div>

                                <div class="widget">
                                    <h4>关于</h4>
                                    <p>Nulla facilisi. Sed justo dui, id erat. Morbi auctor adipiscing tempor. Phasellus condimentum rutrum aliquet. Quisque eu consectetur erat. Proin rutrum, erat eget posuere semper, <em>arcu mauris posuere tortor</em>,velit at <a href="#">magna sollicitudin cursus</a> ac ultrices magna. Aliquam consequat, purus vitae auctor ullamcorper, sem velit convallis quam, a pharetra justo nunc et mauris. </p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </section>
            
			<section class="single-blog rev last">

                <div class="container">
                    <div class="row">
                        <div class="span9">

                            <div class="comments">
                                <h2>文章的评论</h2>
                                <c:if test="${empty comments}">
                                	<div class="comment">
                                		<div class="row">
                                			<div class="span12">
                                			暂无评论
                                			</div>
                                		</div>
                                	</div>
                                </c:if>
                                <c:if test="${!empty comments}">
                                	<c:forEach items="${comments}" var="comment" varStatus="st">
	                                <div class="comment">
	                                    <div class="row">
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
                </div>
            </section>
            
            <section class="single-blog comments-section">

                <div class="container">
                    <div class="row">
                        <div class="span8">
                            <h2>写评论</h2>
                            <br />
                            <form class="form-comments" _lpchecked="1" action="/article/comment/${article.id}" method="post">

                                <fieldset>

                                    <div class="row">
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
                                    
                                    <div class="row">
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

            </section>
            
        </div>