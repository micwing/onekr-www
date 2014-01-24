<%@page import="onekr.biz.utils.GlobalConstants"%>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.biz.domain.dto.DomainDto" %>
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
        <div id="wrap">

            <!--################ HEADER START ################-->

            <header class="page-title">
                <div class="container">
                    <h2>多后缀查询</h2>
                </div>
            </header>
            <section class="single-blog"  style="min-height:  600px">
                <div class="container">
                    <div class="row">

                        <div class="span12">
                        	<form action="/domain/suffix" method="get" id="suffix-form">
                        	<div class="row">
	                       		<div class="span4" style="text-align: right;line-height: 30px">www.</div>
	                       		<div class="span3" style="margin-left:2px">
	                       			<div class="input" style="text-align: left;">
	                       				<input type="text" id="name" name="name" value="${name}"/>
	                       			</div>
	                       		</div>
	                       		<div class="span5">
                       				<input id="suffix-query" class="btn" type="button" value="查询" style="height: 30px;line-height:30px"/>
                       				<input id="all-suffix-query" class="btn" type="button" value="一键查询全部后缀" style="height: 30px;line-height:30px"/>
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
                       		
	                       	<hr>
	                       	
	                    </div>
	                </div>
	                
	                <jsp:include page="domain-ico-querylist.jsp"></jsp:include>
					
                </div>
            </section>

        </div>
<script>
$('#name').keyup(function(e) {
	if (e.keyCode == 13) {
		$('#suffix-query').trigger('click');
	}
});
$('#all-suffix-query').click(function() {
	$('.suffixli input').attr('checked', true);
	$('#suffix-query').trigger('click');
});
$('#suffix-query').click(function() {
	$('#name').val($.trim($('#name').val()));
	var v = $('#name').val();
	if (v == '') {
		return;
	}
	if (!/[A-Za-z0-9_]+/.test(v)) {
		return;
	}
	$('#suffix-form').submit();
});
</script>