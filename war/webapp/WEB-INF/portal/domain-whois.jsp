<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.biz.domain.dto.DomainDto" %>
<%@include file="../common/includes.jsp" %>
        <div id="wrap">

            <!--################ HEADER START ################-->

            <header class="page-title">
                <div class="container">
                    <h2>域名whois信息查询</h2>
                </div>
            </header>
            <section class="single-blog"  style="min-height:  600px">
                <div class="container">
                    <div class="row">

                        <div class="span12">
                        	<div class="row">
	                       		<div class="span4" style="text-align: right;line-height: 30px">www.</div>
	                       		<div class="span3" style="margin-left:2px">
	                       			<div class="input" style="text-align: left;">
	                       				<input id="domain" type="text" value="${dto.domain}"/> 
	                       			</div>
	                       		</div>
	                       		<div class="span5">
                       				<input id="domainQuery" class="btn" type="button" value="查询" style="height: 30px;line-height:30px" />
	                       		</div>
	                       		<hr>
                       		</div>

                            <div class="post-content">
                            	<c:if test="${!empty dto}">
	                            	<p>
	                            		<c:if test="${dto.available == null}">
	                            			无法查询 ${dto.domain} 的whois信息，可能是暂不支持的域名后缀或者您输入的域名格式不正确 
	                            		</c:if>
	                            		<c:if test="${dto.available != null}">
			                            	<c:if test="${dto.available}">
												${dto.domain} 未注册
											</c:if>
			                            	<c:if test="${!dto.available}">
												<a href="http://${dto.domain}" target="_blank">${dto.domain}</a> 已被注册
			                            	</c:if>
	                            		</c:if>
	                            	</p>
	                            	<p>${dto.whoisInfo}</p>
                            	</c:if>
                                
                            </div>
                            
                        </div>


                    </div>
					
                </div>
            </section>

        </div>
<script>
$('#domain').keyup(function(e) {
	if (e.keyCode == 13) {
		$('#domainQuery').trigger('click');
	}
});
$('#domainQuery').click(function() {
	$('#domain').val($.trim($('#domain').val()));
	var v = $('#domain').val();
	if (v == '') {
		return;
	}
	if (!/[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/.test(v)) {
		return;
	}
	location.href='/domain/whois?domain='+$('#domain').val();
});
</script>