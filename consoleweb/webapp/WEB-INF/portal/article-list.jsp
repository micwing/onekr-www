<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<h4>文章列表</h4>
	<table class="table table-hover">
		<tr>
			<th style="min-width: 80px">标题图片</th>
			<th>标题</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.content}" var="article" varStatus="st">
		<tr>
			<td><img alt="" src="${(!empty article.imageUrl) ? article.imageUrl : ''}" style="max-height: 30px"/></td>
			<td>${fn:replace(
					fn:replace(
		 				fn:replace(
			 				fn:replace(
				 				article.title,
				 				'&',
				 				'&amp;'
							),
			 				'<',
			 				'&lt;'
						),
		 				'>',
		 				'&gt;'
					),
					'"',
					'&quot;'
                )}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${article.createAt}" type="both"/></td>
			<td>
			<div class="btn-group">
				<a href="portal/article/detail/${article.id}" class="btn" target="_blank"><i class="icon-eye-open"></i> 查看</a>
				<a href="console/article/modify/${article.id}" class="btn" onclick=""><i class="icon-pencil"></i> 修改</a>
				<a href="console/article/doDelete/${article.id}" class="btn btn-danger" onclick="return window.confirm('确定要删除吗?');"><i class="icon-remove"></i> 删除</a>
			</div>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="pagination">
	<jsp:include page="../util/paging.jsp">
		<jsp:param name="_paging_base_url" value="console/article/list?"/>
		<jsp:param name="_paging_size" value="10"/>
		<jsp:param name="_paging_range" value="3"/>
	</jsp:include>
	</div>


