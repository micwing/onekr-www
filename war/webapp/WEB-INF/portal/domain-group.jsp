<%@page import="onekr.biz.utils.GlobalConstants"%>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.biz.domain.dto.DomainDto" %>
<%@page import="java.util.*" %>
<%@page import="org.springframework.util.CollectionUtils" %>
<%@include file="../common/includes.jsp" %>
<%
List<DomainDto> domainDtoList = (List<DomainDto>) request.getAttribute("domainDtoList");
String suffix = (String) request.getAttribute("suffix");
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
                    <h2>组合查询</h2>
                </div>
            </header>
            <section class="single-blog"  style="min-height:  600px">
                <div class="container">
                    <div class="row">

                        <div class="span12">
                        	<form action="/domain/group" method="get" id="suffix-form">
                        	<div class="row">
	                       		<div class="span2"></div>
	                       		<div class="span8">
	                       				<input type="text" id="first" name="first" value="${first}" style="width: 100px;margin-bottom: 8px"/>
	                       				
	                       				<span>+</span>
	                       				
	                       				<select id="atype" name="atype" style="width: 100px;;margin-bottom: 8px">
	                       					<option value="PINYINZIDIAN" ${atype=='PINYINZIDIAN'?'checked':''}>拼音字典</option>
	                       					<option value="PINYINPINGYIN" ${atype=='PINYINPINGYIN'?'checked':''}>双拼音拼音字典</option>
	                       					<option value="1PINYIN" ${atype=='1PINYIN'?'selected':''}>1位拼音</option>
	                       					<option value="2PINYIN" ${atype=='2PINYIN'?'selected':''}>2位拼音</option>
	                       					<option value="3PINYIN" ${atype=='3PINYIN'?'selected':''}>3位拼音</option>
	                       					<option value="4PINYIN" ${atype=='4PINYIN'?'selected':''}>4位拼音</option>
	                       					<option value="5PINYIN" ${atype=='5PINYIN'?'selected':''}>5位拼音</option>
	                       					
	                       					<option value="1SHUZI" ${atype=='1SHUZI'?'selected':''}>1位数字</option>
	                       					<option value="2SHUZI" ${atype=='2SHUZI'?'selected':''}>2位数字</option>
	                       					<option value="3SHUZI" ${atype=='3SHUZI'?'selected':''}>3位数字</option>
	                       					
	                       					<option value="1ZIMU" ${atype=='1ZIMU'?'selected':''}>1位字母</option>
	                       					<option value="2ZIMU" ${atype=='2ZIMU'?'selected':''}>2位字母</option>
	                       					
	                       					<option value="YINGWENDANCI" ${atype=='YINGWENDANCI'?'selected':''}>英文单词</option>
	                       				</select>
	                       				
	                       				<span>+</span>
	                       				
	                       				<input type="text" id="second" name="second" value="${second}" style="width: 100px;;margin-bottom: 8px"/>
	                       				
	                       				<select id="suffix" name="suffix" style="width: 100px;;margin-bottom: 8px">
	                       				<%for (String suf : GlobalConstants.DOMAIN_ALL_SUFFIX) {%>
	                       					<option value="<%=suf %>" <%=suffix.equals(suf)?"selected":"" %>>.<%=suf %></option>
	                       				<%} %>
	                       				</select>
	                       				
                       					<input id="suffix-query" class="btn" type="button" value="查询" style="height: 30px;line-height:30px;"/>
	                       		</div>
	                       		<div class="span2">
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
$('#suffix-query').click(function() {
	$('#first').val($.trim($('#first').val()));
	$('#second').val($.trim($('#second').val()));
	$('#suffix-form').submit();
});
</script>