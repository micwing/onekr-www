<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType"%>
<%@page import="java.util.*"%>
<%@include file="../common/includes.jsp"%>
<%
Map<String, String> express = new LinkedHashMap<String, String>();
express.put("爱情", "aiqing.png");
express.put("蛋糕", "dangao.png");
express.put("发财", "facai.png");
express.put("给力", "geili.gif");
express.put("恭喜", "gongxi.png");
express.put("鼓掌", "guzhang.gif");
express.put("坏笑", "huaixiao.gif");
express.put("婚纱", "hunsha.png");
express.put("婚鞋", "hunxie.png");
express.put("脚印", "jiaoyin.png");
express.put("礼花", "lihua.png");
express.put("玫瑰", "meigui.png");
express.put("闪电", "shandian.png");
express.put("帅", "shuai.png");
express.put("喜庆", "xiqing.png");
express.put("携手", "xieshou.gif");
express.put("心形", "xinxing.png");
express.put("心意", "xinyi.png");
express.put("信", "xin.gif");
express.put("钻戒", "zuanjie.png");
express.put("棒棒糖", "bangbangtang.png");
express.put("气球", "qiqiu.png");
express.put("飞机", "feiji.png");
express.put("钞票", "chaopiao.png");
%>
<h3>管理请柬留言
<span class="pull-right">
	<a class="btn btn-large" href="console/card/info/list">返回列表</a>
</span>
</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<table class="table table-bordered">
			<tr>
				<th style="width: 80px">姓名</th>
				<th style="width: 80px">出席人数</th>
				<th>祝福内容</th>
				<th style="width: 80px">创建时间</th>
				<th style="width: 50px">操作</th>
			</tr>
			<c:if test="${!empty comments}">
			<c:forEach items="${comments}" var="comment" varStatus="st">
				<tr>
					<td>${comment.userName}</td>
					<td>${comment.json}</td>
					<td class="comment-content">${comment.content}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${comment.createAt}" type="both"/></td>
					<td>
						<a href="javascript:;" onclick="doDelete('${comment.id}')">删除</a>
					</td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
		<c:if test="${empty page.content}">
			<span style="font-style: italic;">无</span>
		</c:if>
	</div>
</div>
<script type="text/javascript">
function doDelete(commentId) {
	if (!confirm('确定要删除该留言吗？')) {
		return;
	}
	$.ajax({
           type: "post",//使用get方法访问后台
           dataType: "json",//返回json格式的数据
           url: "console/card/comment/doDelete",//要访问的后台地址
           data: {
           	'commentId' : commentId,
           	'cardId': '${cardId}'
           },
           success: function(result){
           	if (result.code == 0) {
           		alert('删除成功！');
            	location.href='${ctx}/console/card/comment/list/${cardId}';
           	} else {
           		alert('操作失败！');
           	}
           }
	});
}
function replayExpress(html) {
	<% for (Map.Entry<String, String> entry : express.entrySet()) { %>
	html = html.replace(/\[<%=entry.getKey()%>\]/g, '<img src="assets/images/expression/<%=entry.getValue()%>" alt="<%=entry.getKey()%>" />');
	<%}%>
	return html;
}
$(function() {
	$('.comment-content').each(function() {
		var cnt = $(this).html();
		$(this).html(replayExpress(cnt));
	});
});
</script>



