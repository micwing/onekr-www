<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.data.domain.Page"%>
<c:if test="${page != null}">
	<%
		String pagingUrl = request.getParameter("_paging_base_url");
			int pagingSize = Integer.parseInt(request
					.getParameter("_paging_size"));
			int pagingRange = Integer.parseInt(request
					.getParameter("_paging_range"));
			Page pag = (Page) request.getAttribute("page");
			int number = pag.getNumber() + 1;
			int totalPages = pag.getTotalPages();
			int min = number - pagingRange;
			int max = number + pagingRange;
			if (number <= pagingRange) {
				min = 1;
				max = (max + ((pagingRange + 1) - number)) >= totalPages ? totalPages
						: (max + ((pagingRange + 1) - number));
			}
			if (number > totalPages - pagingRange) {
				max = totalPages;
				min = (min - (number - (totalPages - pagingRange))) <= 0 ? 1
						: (min - (number - (totalPages - pagingRange)));
			}
	%>
	<div class="pagination pull-right">
		<ul>
			<c:if test="${!page.firstPage}">
				<li title="首页"><a
					href="<%=pagingUrl%>&page.page=1&page.size=<%=pagingSize%>">&lt;&lt;</a></li>
				<%-- <li title="上一页"><a href="<%=pagingUrl%>&page.page=${page.number}&page.size=<%=pagingSize%>">&lt;上一页</a></li> --%>
			</c:if>
			<%-- <c:if test="${page.firstPage}">
				<li title="首页"><span>首页</span></li>
				<li title="上一页"><span>&lt;上一页</span></li>
			</c:if> --%>
			<c:forEach begin="<%=min%>" end="<%=max%>" step="1" var="i">
				<c:if test="${i != (page.number+1)}">
					<li title="第${i}页"><a
						href="<%=pagingUrl%>&page.page=${i}&page.size=<%=pagingSize%>">${i}</a></li>
				</c:if>
				<c:if test="${i == (page.number+1)}">
					<li title="第${i}页"><span style="font-weight: bold;">${i}</span></li>
				</c:if>
			</c:forEach>
			<c:if test="${!page.lastPage}">
				<%-- <li title="下一页"><a href="<%=pagingUrl%>&page.page=${page.number+2}&page.size=<%=pagingSize%>">下一页&gt;</a></li> --%>
				<li title="尾页"><a
					href="<%=pagingUrl%>&page.page=${page.totalPages}&page.size=<%=pagingSize%>">&gt;&gt;</a></li>
			</c:if>
			<%-- <c:if test="${page.lastPage}">
				<li title="下一页"><span>下一页&gt;</span></li>
				<li title="尾页"><span>尾页</span></li>
			</c:if> --%>
		</ul>
	</div>
</c:if>