<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType"%>
<%@include file="../common/includes.jsp"%>
<h3>制作码列表</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<table class="table table-bordered">
			<tr>
				<th>制作码</th>
				<th>申请者</th>
				<th>创建时间</th>
				<th>状态</th>
				<th>对应请柬</th>
				<th>制作时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.content}" var="makecode" varStatus="st">
				<tr>
					<td>${makecode.code}</td>
					<td>${makecode.maker}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${makecode.createAt}" type="both"/></td>
					<td>
						<c:if test="${empty makecode.card}">
							<span class="label label-success">未使用</span>
						</c:if>
						<c:if test="${!empty makecode.card}">
							<span class="label label-important">已使用</span>
						</c:if>
					</td>
					<td>
						<c:if test="${!empty makecode.card}">
							${makecode.card.title}
						</c:if>
					</td>
					<td>
						<c:if test="${!empty makecode.card}">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${makecode.makeAt}" type="both"/>
						</c:if>
					</td>
					<td>
						<a href="javascript:;" onclick="doDeleteCode('${makecode.code}')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="../util/paging.jsp">
			<jsp:param name="_paging_base_url" value="console/card/makecode/list" />
			<jsp:param name="_paging_size" value="20" />
			<jsp:param name="_paging_range" value="3" />
		</jsp:include>
	</div>
</div>
<script type="text/javascript">
function doDeleteCode(code) {
	if (!confirm('确定要删除该制作码吗？')) {
		return;
	}
	if (!confirm('删除制作码时，使用制作码制作的请柬也会一起删除！确定要继续吗？')) {
		return;
	}
	$.ajax({
           type: "post",//使用get方法访问后台
           dataType: "json",//返回json格式的数据
           url: "console/card/makecode/doDelete",//要访问的后台地址
           data: {
           	'makecode' : code
           },
           success: function(result){
           	if (result.code == 0) {
           		alert('删除成功！');
            	location.href='${ctx}/console/card/makecode/list';
           	} else {
           		alert('操作失败！');
           	}
           }
	});
}
</script>



