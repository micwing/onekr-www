<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType"%>
<%@include file="../common/includes.jsp"%>
<h3>已停用</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
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
</div>
<div class="row-fluid">
	<div class="span12">
		<table class="table table-bordered">
			<tr>
				<th>ID</th>
				<th>请柬类型</th>
				<th>模版</th>
				<th>标题</th>
				<th>新郎</th>
				<th>新娘</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.content}" var="card" varStatus="st">
				<tr>
					<td>${card.id}</td>
					<td>${card.cardType}</td>
					<td>${card.templetId}</td>
					<td>${card.title}</td>
					<td>${card.people1Name}</td>
					<td>${card.people2Name}</td>
					<td>${catd.remark}</td>
					<td>
						 <a href="card/music/manager/${card.id}">展示</a> <a
						href="card/info/modify/${card.id}">信息</a> <a
						href="card/photo/cardphoto/${card.id}">照片</a> <br> <a
						href="card/map/cardmap/${card.id}">地图</a> <a
						href="card/music/cardmusic/${card.id}">音乐</a> <a
						href="card/music/manager/${card.id}">二维码</a></td>
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




