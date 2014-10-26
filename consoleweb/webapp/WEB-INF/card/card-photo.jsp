<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<%@page import="java.io.File"%>
<%@page
	import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer"%>
<%@page import="onekr.commonservice.filestore.intf.FileBiz"%>
<%@page import="onekr.framework.utils.FileUtil"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="onekr.cardservice.model.Card"%>
<h4>请柬照片</h4>
<hr>

<div class="row-fluid">
	<div class="span12">
		<form class="form-horizontal" action="/card/photo/doUploadFile"
			method="post" enctype="multipart/form-data" id="file-form">
			<div class="control-group">
				<label class="control-label" for="file">选择照片</label>
				<div class="controls">
					<input type="hidden" name="cardId" value="${card.id}" /> <input
						type="file" id="file" name="file" multiple value="选择文件"
						placeholder="file" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="">压缩宽度 /长度</label>
				<div class="controls">
					<input type="text" name="width" placeholder="file" /> / <input
						type="text" name="height" placeholder="file" /> <span
						class="help-block">当照片宽度大于等于高度，则依照宽度压缩；</span> <span
						class="help-block">当照片宽度小于高度，则依照高度压缩；</span>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input class="btn" type="submit" id="uploadButton" value="上传照片" />
				</div>
			</div>
		</form>
	</div>
</div>

<hr>

<script type="text/javascript"
	src="/assets/js/fancyBox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
<script type="text/javascript"
	src="/assets/js/fancyBox/source/jquery.fancybox.js?v=2.1.5"></script>
<link rel="stylesheet" type="text/css"
	href="/assets/js/fancyBox/source/jquery.fancybox.css?v=2.1.5"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="/assets/js/fancyBox/source/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<script type="text/javascript"
	src="/assets/js/fancyBox/source/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
<link rel="stylesheet" type="text/css"
	href="/assets/js/fancyBox/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />
<script type="text/javascript"
	src="/assets/js/fancyBox/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
<script type="text/javascript"
	src="/assets/js/fancyBox/source/helpers/jquery.fancybox-media.js?v=1.0.6"></script>

<div class="row-fluid">
	<div class="span12">
		<c:forEach items="${photos}" var="fileStore" varStatus="st">
			<table class="table">
				<tr>
					<td><a
						href="${ctx}${fn:replace(fileStore.storePath, '\\', '/')}"
						class="fancybox" rel="group1"> <img
							src="${ctx}${fn:replace(fileStore.storePath, '\\', '/')}"
							class="img-polaroid" height="200px" width="200px">
					</a></td>
					<td><span class="label label-success">封面</span> <span
						class="label label-info">新郎独照</span> <span
						class="label label-info">新娘独照</span></td>
					<td>
						<form class="form-horizontal">
							<div class="control-group">
								<button class="btn btn-success">设为封面</button>
								<button class="btn btn-info">设为新郎独照</button>
								<button class="btn btn-info">设为新娘独照</button>
								<button class="btn btn-danger">删除</button>
							</div>

						</form>
					</td>
				</tr>
			</table>
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
	$(".fancybox").fancybox(
			{

				prevEffect : 'elastic',
				nextEffect : 'elastic',

				openEffect : 'elastic',
				openSpeed : 150,

				closeEffect : 'elastic',
				closeSpeed : 150,

				closeBtn : false,

				helpers : {
					title : {
						type : 'outside'
					},
					overlay : {
						speedOut : 0
					},
					thumbs : {
						width : 50,
						height : 50
					},
					buttons : {}
				},

				afterLoad : function() {
					this.title = '[ ' + (this.index + 1) + ' / '
							+ this.group.length
							+ (this.title ? ' ] ' + this.title : '');
				}
			});
</script>


