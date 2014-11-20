<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<%@page import="onekr.cardservice.card.intf.CardPhotoFileBiz" %>
<h3>请柬照片
<span class="pull-right">
    <span class="btn-group">
		<a class="btn" href="card/info/modify/${card.id}">上一步请柬信息</a>
		<a class="btn" href="card/map/cardmap/${card.id}">下一步设置地图</a>
    </span>
</span>
</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<form class="form-horizontal" action="card/photo/doUploadFile"
			method="post" enctype="multipart/form-data" id="file-form">
			<div class="control-group">
				<label class="control-label" for="file">选择照片</label>
				<div class="controls">
					<input type="hidden" name="cardId" value="${card.id}" /> <input
						type="file" id="file" name="file" multiple value="选择文件"
						accept="image/*"
						placeholder="file" />
					<span class="help-block">系统自动等比例压缩宽高大于<%=CardPhotoFileBiz.SQUARE_IMAGE_PHOTO_MAX_WIDTH%>px的图片，并生成宽高<%=CardPhotoFileBiz.SQUARE_IMAGE_THUMB_WIDTH%>px的缩略图</span>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input class="btn btn-primary btn-large" type="button" id="uploadButton" value="上传照片" />
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
$('#uploadButton').click(function() {
	if ($('#file-form input[name=file]').val() == '') {
		alert('请选择照片！');
		return;
	}
	$('#file-form').submit();
	$('#uploadButton').attr('disabled', true);
});
</script>

<hr>

<script type="text/javascript"
	src="assets/js/fancyBox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
<script type="text/javascript"
	src="assets/js/fancyBox/source/jquery.fancybox.js?v=2.1.5"></script>
<link rel="stylesheet" type="text/css"
	href="assets/js/fancyBox/source/jquery.fancybox.css?v=2.1.5"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="assets/js/fancyBox/source/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<script type="text/javascript"
	src="assets/js/fancyBox/source/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
<link rel="stylesheet" type="text/css"
	href="assets/js/fancyBox/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />
<script type="text/javascript"
	src="assets/js/fancyBox/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
<script type="text/javascript"
	src="assets/js/fancyBox/source/helpers/jquery.fancybox-media.js?v=1.0.6"></script>

<div class="row-fluid">
	<div class="span12">
		<table class="table">
		<c:forEach items="${photos}" var="dto" varStatus="st">
			<tr>
				<td>
					<c:if test="${dto.isCover}">
					<div><span class="label label-success">请柬封面</span></div>
					</c:if>
					<c:if test="${dto.isPeople1Photo}">
					<div><span class="label label-info">新郎独照</span></div>
					</c:if>
					<c:if test="${dto.isPeople2Photo}">
					<span class="label label-important">新娘独照</span>
					</c:if>
					<div><span class="label">请柬相册</span></div>
				</td>
				<td><a
					href="attached${fn:replace(dto.photo.storePath, '\\', '/')}"
					class="fancybox" rel="group1"> <img
						src="attached${fn:replace(dto.thumb.storePath, '\\', '/')}"
						class="img-polaroid" height="200px" width="200px">
				</a></td>
				<td>
					<div>
						<c:if test="${!dto.isCover}">
						<div><a href="card/photo/doUseWay?cardId=${card.id}&desc=cover&fileStoreId=${dto.photo.id}">设为封面</a> </div>
						</c:if>
						<c:if test="${dto.isCover}">
						<div><a href="card/photo/doCancelWay?cardId=${card.id}&desc=cover&fileStoreId=${dto.photo.id}">取消封面</a> </div>
						</c:if>
						
						<c:if test="${!dto.isPeople1Photo}">
						<div><a href="card/photo/doUseWay?cardId=${card.id}&desc=people1&fileStoreId=${dto.photo.id}">设为新郎独照</a> </div>
						</c:if>
						<c:if test="${dto.isPeople1Photo}">
						<div><a href="card/photo/doCancelWay?cardId=${card.id}&desc=people1&fileStoreId=${dto.photo.id}">取消新郎独照</a> </div>
						</c:if>
						
						<c:if test="${!dto.isPeople2Photo}">
						<div><a href="card/photo/doUseWay?cardId=${card.id}&desc=people2&fileStoreId=${dto.photo.id}">设为新娘独照</a> </div>
						</c:if> 
						<c:if test="${dto.isPeople2Photo}">
						<div><a href="card/photo/doCancelWay?cardId=${card.id}&desc=people2&fileStoreId=${dto.photo.id}">取消新娘独照</a> </div>
						</c:if>
						<div><a href="card/photo/doDelete?fileStoreId=${dto.photo.id}&cardId=${card.id}">删除</a> </div>
					</div>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>
<script type="text/javascript">
var ns = ns || {};

$(function() {
	
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
});
</script>


