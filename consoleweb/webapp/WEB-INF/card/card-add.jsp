<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<h4><c:if test="${empty article}">添加请柬</c:if><c:if test="${!empty article}">修改请柬</c:if></h4>

<form id="article-form" method="post" action="/console/article/doSave">
	<input type="hidden" name="id" value="${article == null ? '' : article.id}"/>
	<label>标题</label>
	<input type="text" id="title" name="title" placeholder="title" class="input-block-level"
		value="">
	<label style="margin-top: 10px">标题图片</label>
	<input type="hidden" id="imageUrl" name="imageUrl" value=""/>
	<input type="button" class="btn" id="imageUrlBtn" value="选择图片" /> 
	<label style="margin-top: 10px">内容</label>
	<textarea id="content" name="content" style="width:100%;height:400px;visibility:hidden;" placeholder="content"></textarea>
	<br>
	<button type="submit" class="btn">保存</button> <button type="button" class="btn" onclick="ns.cancel()">取消</button>
</form>
<script>
var ns = ns || {};
ns.useImageUrl = function(url) {
	$('#imageUrl').val(url);
	$('#titleImg').attr('src', url); 
};
ns.cancel = function() {
	if (!window.confirm('确定要取消吗?')) {
		return;
	}
	history.go(-1);
};
$('#article-form').validate({
	rules: {
		title: {
			required: true,
			maxlength:255
		}
	},
	errorClass: "help-inline",
	errorElement: "span",
	highlight:function(element, errorClass, validClass) {
		//$(element).parents('.control-group').removeClass('success');
		//$(element).parents('.control-group').addClass('error');
	},
	unhighlight: function(element, errorClass, validClass) {
		//$(element).parents('.control-group').removeClass('error');
		//$(element).parents('.control-group').addClass('success');
	}
});
</script>


