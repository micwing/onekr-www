<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType"%>
<%@include file="../common/includes.jsp"%>
<h3>已停用</h3>
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
				<th>请柬类型</th>
				<th>标题</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:if test="${!empty page.content}">
				<c:forEach items="${page.content}" var="card" varStatus="st">
				<tr cardId="${card.id}">
					<td>${card.cardType.label}</td>
					<td>${card.title}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${card.createAt}" type="both"/></td>
					<td><a
						href="console/card/info/modify/${card.id}">修改</a>
						</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${empty page.content}">
			<span style="font-style: italic;">空</span>
		</c:if>
		<jsp:include page="../util/paging.jsp">
			<jsp:param name="_paging_base_url" value="console/card/info/pausedlist?cardType=WED_CARD&status=PAUSED" />
			<jsp:param name="_paging_size" value="20" />
			<jsp:param name="_paging_range" value="3" />
		</jsp:include>
	</div>
</div>




