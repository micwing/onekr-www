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
				<th>ID</th>
				<th>请柬类型</th>
				<th>模版</th>
				<th>标题</th>
				<th>新郎、新娘</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.content}" var="card" varStatus="st">
				<tr>
					<td>${card.id}</td>
					<td>${card.cardType.label}</td>
					<td>${card.templetId}</td>
					<td>${card.title}</td>
					<td>${card.people1Name}、${card.people2Name}</td>
					<td><a
						href="card/info/modify/${card.id}">修改</a>
						</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="../util/paging.jsp">
			<jsp:param name="_paging_base_url" value="card/info/pausedlist?cardType=WED_CARD&status=PAUSED" />
			<jsp:param name="_paging_size" value="20" />
			<jsp:param name="_paging_range" value="3" />
		</jsp:include>
	</div>
</div>




