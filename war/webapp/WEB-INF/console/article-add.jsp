<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.model.Article" %>
<%@page import="onekr.commonservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp"%>
<h4><c:if test="${empty article}">新文章</c:if><c:if test="${!empty article}">修改文章</c:if></h4>
<%
request.setCharacterEncoding("UTF-8");
Article article = (Article) request.getAttribute("article");
%>

<link rel="stylesheet" href="/assets/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="/assets/js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="/assets/js/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/assets/js/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="/assets/js/kindeditor/plugins/code/prettify.js"></script>
<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="content"]', {
			cssPath : '/assets/js/kindeditor/plugins/code/prettify.css',
			uploadJson : '/console/file/doUploadFile',
			fileManagerJson : '/console/file/fileManager',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
		K('#imageUrlBtn').click(function() {
			editor1.loadPlugin('image', function() {
				editor1.plugin.imageDialog({
					imageUrl : K('#imageUrl').val(),
					clickFn : function(url, title, width, height, border, align) {
						K('#imageUrl').val(url);
						$('#titleImg').attr('src', url); 
						editor1.hideDialog();
					}
				});
			});
		});
	});
</script>

<form id="article-form" method="post" action="/console/article/doSave">
	<input type="hidden" name="id" value="${article == null ? '' : article.id}"/>
	<label>标题</label>
	<input type="text" id="title" name="title" placeholder="title" class="input-block-level"
		value="<%= (article != null) ? htmlspecialchars(article.getTitle()) : ""%>">
	<label style="margin-top: 10px">标题图片</label>
	<%
	String imageUrl = "";
	if (article != null && article.getImageUrl() != null) {
		imageUrl = article.getImageUrl();
	}
	%>
	<img id="titleImg" alt="" src="<%= imageUrl%>" style="max-height: 150px;">
	<input type="hidden" id="imageUrl" name="imageUrl" value="<%= imageUrl%>"/>
	<input type="button" class="btn" id="imageUrlBtn" value="选择图片" /> 
	<input type="button" class="btn" value="使用默认" onclick="ns.useImageUrl('<%=GlobalConstants.ARTICLE_DEFAULT_IMAGE_URL%>')"/>
	<input type="button" class="btn" value="删除图片" onclick="ns.useImageUrl('')"/>
	<label style="margin-top: 10px">内容</label>
	<textarea id="content" name="content" style="width:100%;height:400px;visibility:hidden;" placeholder="content">
	<%= (article != null) ? htmlspecialchars(article.getContent()) : ""%></textarea>
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


