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
			<c:if test="${!empty page.content}">
			<c:forEach items="${page.content}" var="card" varStatus="st">
			<div class="row-fluid">
				<div class="span3">
					<c:if test="${empty cardPhotoMap[card.id].photo.storePath}">
					<img class="img-rounded img-polaroid" src="assets/images/noimage.jpg" style="width: 170px;height: 220px"/>
					</c:if>
					<c:if test="${!empty cardPhotoMap[card.id].photo.storePath}">
					<img class="img-rounded img-polaroid" src="attached${fn:replace(cardPhotoMap[card.id].photo.storePath, '\\', '/')}" style="width: 170px;height: 220px"/>
					</c:if>
				</div>
				<div class="span9">
					<table class="table">
						<tr><td style="width: 80px">标题：</td><td>${card.title}</td></tr>
						<tr><td>类型：</td>
							<td><span class="label">${card.cardType.label}</span>&nbsp;&nbsp;
								模板：<span class="label">${card.templetId}</span>&nbsp;&nbsp;
								状态：<span class="label ${card.status=='NORMAL'?'label-success':'label-important'}">${card.status.label}</span></td></tr>
						<tr><td>典礼时间：</td><td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${card.partyTime}" type="both"/></td></tr>
						<tr><td>操作：</td><td>
						<a
						href="console/card/info/modify/${card.id}">修改信息</a> <a
						href="console/card/photo/cardphoto/${card.id}">管理照片</a> <a
						href="console/card/map/cardmap/${card.id}">设置地图</a> <a
						href="console/card/music/cardmusic/${card.id}">选择音乐</a> <a
						href="console/card/2dcode/index/${card.id}">扫描二维码</a> <br> <a
						href="javascript:alert('敬请期待');">管理现场照片</a> <a
						href="javascript:alert('敬请期待');">管理请柬留言</a>
						</td></tr>
						<tr><td colspan="2">
						电脑端预览请柬：<a href="portal/frame/${card.id}" target="_blank"><%=siteRootUrl%>/portal/frame/${card.id}</a><br>
						手机端访问地址：<a href="console/card/2dcode/index/${card.id}"><%=siteRootUrl%>/card/cover/${card.id}</a>
						</td></tr>
					</table>
				</div>
				
			</div>
			</c:forEach>
			</c:if>
		<c:if test="${empty page.content}">
			<span style="font-style: italic;">空</span>
		</c:if>
		<jsp:include page="../util/paging.jsp">
			<jsp:param name="_paging_base_url" value="console/card/info/list?cardType=WED_CARD&status=NORMAL" />
			<jsp:param name="_paging_size" value="20" />
			<jsp:param name="_paging_range" value="3" />
		</jsp:include>
	</div>
</div>




