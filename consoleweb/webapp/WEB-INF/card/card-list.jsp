<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType"%>
<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer" %>
<%@include file="../common/includes.jsp"%>
<% 
String siteRootUrl = CustomizedPropertyPlaceholderConfigurer.getContextProperty("site.root.url");
%>
<h3>请柬列表</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<%-- <div class="row-fluid">
	<div class="span12">
		<form class="form-search">
			<select id="queryCartType">
				<%
					for (CardType ct : CardType.values()) {
				%>
				<option value="<%=ct.name()%>"><%=ct.getLabel()%></option>
				<%
					}
				%>
			</select>
			<button type="submit" class="btn">Search</button>
		</form>
	</div>
</div> --%>
<div class="row-fluid">
	<div class="span12">
		<table class="table table-bordered">
			<tr>
				<th>ID</th>
				<th>请柬类型</th>
				<th>标题</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:if test="${empty page.content}">
				<tr>
					<td colspan="0"><span style="font-style: italic;">空</span></td>
				</tr>
			</c:if>
			<c:if test="${!empty page.content}">
			<c:forEach items="${page.content}" var="card" varStatus="st">
				<tr>
					<td>${card.id}</td>
					<td>${card.cardType.label}</td>
					<td>${card.title}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${card.createAt}" type="both"/></td>
					<td><a
						href="console/card/info/modify/${card.id}">信息</a> <a
						href="console/card/photo/cardphoto/${card.id}">照片</a> <a
						href="console/card/map/cardmap/${card.id}">地图</a> <a
						href="console/card/music/cardmusic/${card.id}">音乐</a> <a
						href="console/card/2dcode/index/${card.id}">二维码</a>
						</td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
		<jsp:include page="../util/paging.jsp">
			<jsp:param name="_paging_base_url" value="console/card/info/list?cardType=WED_CARD&status=NORMAL" />
			<jsp:param name="_paging_size" value="20" />
			<jsp:param name="_paging_range" value="3" />
		</jsp:include>
	</div>
</div>




