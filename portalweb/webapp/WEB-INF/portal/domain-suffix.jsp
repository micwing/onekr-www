<%@page import="onekr.portalservice.utils.GlobalConstants"%>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.domain.dto.DomainDto" %>
<%@page import="java.util.*" %>
<%@page import="org.springframework.util.CollectionUtils" %>
<%@include file="../common/includes.jsp" %>
<%
String[] allSuffix = GlobalConstants.DOMAIN_ALL_SUFFIX;

List<DomainDto> domainDtoList = (List<DomainDto>) request.getAttribute("domainDtoList");
Set<String> domainDtoSet = new HashSet<String>();
if (!CollectionUtils.isEmpty(domainDtoList)) {
	for (DomainDto dto : domainDtoList) {
		domainDtoSet.add(dto.getSuffix());
	}
}
%>
<style>
#suffixbox {
	margin-left: 20px;
}
#suffixbox .suffixli {
	float: left;
    margin: 5px;
    padding: 5px;
    text-align: left;
    width: 100px;
    list-style: none outside none;
    border: 0 none;
}
#suffixbox .suffixli input {
    margin-bottom: 3px;
    vertical-align: bottom;
}
#suffixbox .suffixli label {
    text-align: left;
    display: inline;
    margin-left: 3px;
}
</style>

            <header>
                <div class="container">
                    <h2>多后缀查询</h2>
                </div>
            </header>
            <section style="min-height:  600px">
                <div class="container">
                    <div class="row">

                        <div class="span12">
                        	<form class="form-search" action="/domain/suffix" method="get" id="suffix-form">
                        	<div class="row">
	                       		<div class="span4" style="text-align: right;line-height: 30px">www.</div>
	                       		<div class="span8" style="margin-left:2px">
                       				<input type="text" class="input-medium" id="name" name="name" value="${name}"/>
                       				<button class="btn btn-primary" type="submit">查询</button>
                      				<button id="all-suffix-query" class="btn btn-primary" type="button">一键查询全部后缀</button>
	                       		</div>
                       		</div>
                       		<div class="row">
                       			<div class="span1"></div>
	                       		<div class="span10">
	                       			<div id="suffixbox">
	                       				<%for (String suffix : allSuffix) {%>
										<li class="suffixli"><input name="suffix" type="checkbox" value="<%=suffix%>" id="<%=suffix%>suffix" <%=domainDtoSet.contains(suffix)?"checked":"" %>/><label for="<%=suffix%>suffix">.<%=suffix%></label></li>
	                       				<%}%>
									</div>
	                       		</div>
	                       		<div class="span1">
	                       		</div>
                       		</div>
                       		</form>
	                       	
	                    </div>
	                </div>
	                
	                <hr>
	                <jsp:include page="domain-ico-querylist.jsp"></jsp:include>
					
                </div>
            </section>

<script>
$('#all-suffix-query').click(function() {
	$('.suffixli input').attr('checked', true);
	$('#suffix-form').submit();
});
</script>