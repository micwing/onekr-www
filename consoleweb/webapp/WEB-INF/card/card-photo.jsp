<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<link rel="stylesheet"
	href="${ctx}/assets/js/kindeditor/themes/default/default.css" />
<script src="${ctx}/assets/js/kindeditor/kindeditor.js"></script>
<script src="${ctx}/assets/js/kindeditor/lang/zh_CN.js"></script>
<script>
	KindEditor.ready(function(K) {
		var editor = K.editor({
			uploadJson : '/card/photo/doCardUploadFile?cardId=${card.id}',
			allowFileManager : true
		});
		K('#image3').click(
				function() {
					editor.loadPlugin('image', function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K('#url3').val(),
							clickFn : function(url, title, width, height,
									border, align) {
								K('#url3').val(url);
								editor.hideDialog();
							}
						});
					});
				});

		/*打开文件管理器*/
		/* K('#imageUrlBtn').click(function() {
			editor1.loadPlugin('filemanager', function() {
				editor1.plugin.filemanagerDialog({
					viewType : 'VIEW',
					dirName : 'card',
					clickFn : function(url, title) {
						K('#url').val(url);
						editor.hideDialog();
					}
				});
			});
		}); */
	});
</script>
<h4>请柬照片</h4>
<hr>
<button type="submit" class="btn btn-primary" id="saveCard">保存</button>
<button type="button" class="btn btn-primary" id="saveCardGotoPhoto">保存并修改地图</button>

<div class="row-fluid">
	<div class="span12">
		<form class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="inputEmail">上传图片文件</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="url3" value=""
						name="storePath" /><br> <input type="button" class="btn"
						id="image3" value="选择图片" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">保存</button>
				</div>
			</div>
		</form>
	</div>
</div>

<div class="row-fluid">
	<div class="span12">
		<c:forEach items="${photos}" var="fileStore" varStatus="st">
			<img src="${ctx}/attached/${fileStore.storeName}"
				class="img-polaroid">
		</c:forEach>
	</div>
</div>




