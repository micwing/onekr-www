<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<link rel="stylesheet" href="/assets/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="/assets/js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="/assets/js/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/assets/js/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="/assets/js/kindeditor/plugins/code/prettify.js"></script>
<style>
.table-overflow table th,td {
	min-width: 100px;
}
</style>
<h4>留言列表</h4>
<div class="table-overflow" style="overflow:auto; width: 100%;">
	<table class="table table-hover">
		<tr>
			<th style="min-width: 30px;"></th>
			<th>名称</th>
			<th>邮箱</th>
			<th>留言时间</th>
			<th>内容</th>
			<th>GOOD/BAD</th>
			<th>回复人</th>
			<th>回复内容</th>
			<th>回复时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${comments}" var="comment" varStatus="st">
		<c:set value="${fn:replace(fn:replace(fn:replace(fn:replace(comment.content,'&','&amp;'),'<','&lt;'),'>','&gt;'),'\"','&quot;')}" 
		var="comment_content" />
		<tr>
			<td style="min-width: 30px;"><img src="/assets/images/default-user.jpg" class="img-circle author_pic" style="width: 30px"></td>
			<td>${comment.userName}</td>
			<td>${comment.email}</td>
			<td style="min-width: 200px;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${comment.createAt}" type="both"/></td>
			<td style="min-width: 400px;">
				<a href="#detailModal" data-toggle="modal" onclick="ns.detail(${comment.id})">
					${fn:substring(comment_content, 0, 25)}...</a></td>
			<td>${comment.good} / ${comment.bad}</td>
			<td><c:if test="${!empty comment.subComment}">${comment.subComment.userName}</c:if></td>
			<td style="min-width: 400px;"><c:if test="${!empty comment.subComment}">
				<a href="#detailModal" data-toggle="modal" onclick="ns.detail(${comment.id})">
					${fn:substring(fn:replace(fn:replace(fn:replace(fn:replace(comment.subComment.content,'&','&amp;'),'<','&lt;'),'>','&gt;'),'"','&quot;'), 0, 25)}</a></c:if>
	        </td>
			<td style="min-width: 200px;"><c:if test="${!empty comment.subComment}"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${comment.subComment.createAt}" type="both"/></c:if></td>
			<td style="min-width: 300px;">
				<a href="#detailModal" role="button" class="btn" data-toggle="modal" onclick="ns.detail(${comment.id})"><i class="icon-eye-open"></i> 修改</a>
				<button class="btn btn-danger" onclick="ns.doDelete(${comment.id})"><i class="icon-remove"></i> 删除</button>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
	
	<!-- Modal -->
	<div id="detailModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="detailModalLabel" aria-hidden="true" style="width: 600px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="detailModalLabel">修改留言</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" id="detail-form">
				<div class="control-group">
					<label class="control-label" for="senderName">姓名</label>
					<div class="controls">
						<input type="hidden" id="detail-id" name="id" value=""/>
						<input type="text" id="detail-senderName" name="senderName" class="span3" placeholder="senderName">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="senderEmail">邮箱</label>
					<div class="controls">
						<input type="text" id="detail-senderEmail" name="senderEmail" class="span3" placeholder="senderEmail">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="content">内容</label>
					<div class="controls">
						<textarea id="detail-content" name="content" rows="4" cols="4" class="span3" placeholder="content"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createAt">创建时间</label>
					<div class="controls">
						<input type="text" id="detail-createAt" name="createAt" class="span3" placeholder="createAt" >
					</div>
				</div>
				
				<hr/>
				
				<div class="control-group">
					<label class="control-label" for="replyContent">回复内容</label>
					<div class="controls">
						<textarea id="reply-replyContent" name="replyContent" rows="4" cols="4" class="span3" placeholder="replyContent"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="replyAt">回复时间</label>
					<div class="controls">
						<input type="text" id="reply-replyAt" name="replyAt" class="span3" placeholder="replyAt" >
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createAt">是否通知留言者</label>
					<div class="controls">
						<input type="checkbox" id="reply-isNoticeSender" name="isNoticeSender" value="true">
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button class="btn btn-primary" onclick="ns.doSave()">保存</button>
		</div>
	</div>

	<script>
	var ns = ns || {};
	ns.detail = function(commentId) {
		$.ajax({
			url : '/console/leaveComment/find',
			data : {commentId:commentId},
			type : 'post',
	        dataType : 'json',
			success: function(data) {
				var comment = data.value;
				$('#detail-id').val(comment.id);
				$('#detail-senderName').val(comment.userName);
				$('#detail-senderEmail').val(comment.email);
				$('#detail-content').val(comment.content);
				$('#detail-createAt').val(new Date(comment.createAt).format("yyyy-MM-dd HH:mm:ss"));
				
				if (comment.subComment) {
					$('#reply-replyContent').val(comment.subComment.content);
					$('#reply-replyAt').val(new Date(comment.subComment.createAt).format("yyyy-MM-dd HH:mm:ss"));
				} else {
					$('#reply-replyAt').val(new Date().format("yyyy-MM-dd hh:mm:ss"));
				}
			}
		});
	};
	ns.doSave = function() {
		if (!$('#detail-form').valid()) {
			return;
		}
		$.ajax({
			url : '/console/leaveComment/doSave',
			data : $('#detail-form').serialize(),
			type : 'post',
	        dataType : 'json',
			success: function(data) {
				alert(data.message);
				if (data.code == 0) {					
					location.href='/console/leaveComment/list';
				}
			}
		});
	};
	ns.doDelete = function(commentId) {
		if (!window.confirm('确定要删除该留言吗？')){
			return;
		}
		$.ajax({
			url : '/console/leaveComment/doDelete',
			data : {commentId:commentId},
			type : 'post',
	        dataType : 'json',
			success: function(data) {
				alert(data.message);
				if (data.code == 0) {
					location.reload();
				}
			}
		});
	};
	
	$('#detail-form').validate({
		rules: {
			senderName: {
				required: true,
				maxlength:255
			},
			senderEmail: {
				required: true,
				maxlength:255
			},
			content: {
				required: true,
				maxlength:3000
			},
			createAt: {
				required: true
			},
			replyContent: {
				required: true,
				maxlength:3000
			},
			replyAt: {
				required: true,
				maxlength:3000
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	</script>


