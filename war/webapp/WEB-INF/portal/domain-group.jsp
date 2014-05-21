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

            <header>
                <div class="container">
                    <h2>组合查询</h2>
                </div>
            </header>
            <section style="min-height:  600px">
                <div class="container">
                	<div class="row">
                		<div class="span2">
                		</div>
                		<div class="span10">
		            		<form class="form-search" action="/domain/group" method="get" id="suffix-form">
		           				<input type="text" class="input-medium" id="first" name="first" value="${first}"/>
		           				
		           				<span>+</span>
		           				
		           				<select class="input-medium" id="atype" name="atype">
		           					<option value="PINYINZIDIAN" ${atype=='PINYINZIDIAN'?'checked':''}>拼音字典</option>
		           					<%-- <option value="PINYINPINGYIN" ${atype=='PINYINPINGYIN'?'checked':''}>双拼音拼音字典</option> --%>
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
		           				
		           				<input type="text" class="input-medium" id="second" name="second" value="${second}"/>
		           				
		           				<select class="input-medium" id="suffix" name="suffix">
		           				<%for (String suf : GlobalConstants.DOMAIN_ALL_SUFFIX) {%>
		           					<option value="<%=suf %>" <%=suffix.equals(suf)?"selected":"" %>>.<%=suf %></option>
		           				<%} %>
		           				</select>
		           				
		          				<button type="submit" class="btn btn-primary" id="suffix-query">查询</button>
		           			</form>
                		</div>
                	</div>
	                
	                <hr>
	                <jsp:include page="domain-ico-querylist.jsp"></jsp:include>
					
                </div>
            </section>

