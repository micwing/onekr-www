<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.portalservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp" %>
<div class="row">
	
	<div class="span9">
		<div class="well">
			<div class="row-fluid">

				<div class="span12">

                    <div class="row-fluid">
                        <h3>${article.title}</h3>
                    </div>
                    
                    <div class="row-fluid">
                    	<h6 class="pull-right">
                    	浏览${article.totalViewCount}次
                    	</h6>
                    </div>

                    <div class="row-fluid">
                    	${article.content}
                    </div>
                    
                    <div class="row-fluid" style="margin-top: 40px">
                    	<div class="span6">
                    	<jsp:include page="../common/baidushare.jsp"></jsp:include>
                    	</div>
                    	<div class="span6">
                    	<h6 class="pull-right">
                    	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${article.createAt}" type="both"/>
                    	</h6>
                    	</div>
                    </div>
                    
                </div>


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