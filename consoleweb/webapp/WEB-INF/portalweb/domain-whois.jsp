<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.portalservice.domain.dto.DomainDto" %>
<%@include file="../common/includes.jsp" %>
        <div id="wrap">

            <header>
                <div class="container">
                    <h2>域名whois信息查询</h2>
                </div>
            </header>
            <section style="min-height:  600px">
                <div class="container">
                    <div class="row">

                        <div class="span12">
                        	<div class="row">
	                       		<div class="span4" style="text-align: right;line-height: 30px">www.</div>
	                       		<div class="span8" style="margin-left:2px">
	                       			<form class="form-search" action="/domain/whois" method="get">
	                       				<input class="input-medium" id="domain" name="domain" type="text" value="${dto.domain}"/> 
	                       				<button class="btn btn-primary" id="domainQuery" type="submit">查询</button>
                       				</form>
	                       		</div>
                       		</div>

							<hr>
                            <div class="row">
                            	<div class="span12">
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
					
                </div>
            </section>

        </div>
