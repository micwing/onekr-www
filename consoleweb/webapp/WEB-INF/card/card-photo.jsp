<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<%@page import="onekr.cardservice.card.intf.CardPhotoFileBiz" %>
<h3>管理照片
<span class="pull-right">
    <span class="btn-group">
		<a class="btn" href="console/card/info/modify/${card.id}">请柬信息</a>
		<a class="btn btn-info" href="console/card/photo/cardphoto/${card.id}">管理照片</a>
		<a class="btn" href="console/card/map/cardmap/${card.id}">设置地图</a>
		<a class="btn" href="console/card/music/cardmusic/${card.id}">选择音乐</a>
		<a class="btn" href="console/card/2dcode/index/${card.id}">扫描二维码</a>
    </span>
    <a class="btn btn-large" href="console/card/map/cardmap/${card.id}">下一步</a>
</span>
</h3>
<hr class="head-hr">

<div class="alert">
	<strong>温馨提示</strong><br>
	请在本页面进行<strong>第2步</strong>，<strong>管理照片</strong>；<br>
	上传之前请保证每张照片大小不超过<strong>5M</strong>；您每次至多上传<strong>5张</strong>照片，同时上传多张照片时速度较慢请您耐心等待；<br>
	上传完成之后请在照片列表中设置一张照片为<strong>封面</strong>、设置一张照片为<strong>新郎独照</strong>、设置一张照片为<strong>新娘独照</strong>。<br>
</div>

<jsp:include page="../util/message.jsp"/>

<div class="row-fluid">
	<div class="span12">
		<form class="form-horizontal" action="console/card/photo/doUploadFile"
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
	var fileCount = document.getElementById('file').files.length;
	if (fileCount > 5) {
		alert('一次至多选择5张照片上传！');
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
				<td style="text-align: left;width: 200px">
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
				<td style="text-align: center;"><a 
					href="attached${fn:replace(dto.photo.storePath, '\\', '/')}"
					class="fancybox" rel="group1"> <img
						src="attached${fn:replace(dto.thumb.storePath, '\\', '/')}"
						class="img-polaroid" height="200px" width="200px"><br><i class="icon-search" style="font-size: 40px">&nbsp;</i> 
				</a></td>
				<td style="text-align: right;width: 200px">
					<div>
						<c:if test="${!dto.isCover}">
						<div><button class="btn" type="button" onclick="ns.doUseWay('${card.id}','cover','${dto.photo.id}', this);">设为封面</button> </div>
						</c:if>
						<c:if test="${dto.isCover}">
						<div><button class="btn btn-info" type="button" onclick="ns.doCancelWay('${card.id}','cover','${dto.photo.id}', this);">取消封面</button> </div>
						</c:if>
						<br>
						<c:if test="${!dto.isPeople1Photo}">
						<div><button class="btn" type="button" onclick="ns.doUseWay('${card.id}','people1','${dto.photo.id}', this);">设为新郎独照</button> </div>
						</c:if>
						<c:if test="${dto.isPeople1Photo}">
						<div><button class="btn btn-info" type="button" onclick="ns.doCancelWay('${card.id}','people1','${dto.photo.id}', this);">取消新郎独照</button> </div>
						</c:if>
						<br>
						<c:if test="${!dto.isPeople2Photo}">
						<div><button class="btn" type="button" onclick="ns.doUseWay('${card.id}','people2','${dto.photo.id}', this);">设为新娘独照</button> </div>
						</c:if> 
						<c:if test="${dto.isPeople2Photo}">
						<div><button class="btn btn-info" type="button" onclick="ns.doCancelWay('${card.id}','people2','${dto.photo.id}', this);">取消新娘独照</button> </div>
						</c:if>
						<br>
						<div><button class="btn btn-danger" type="button" onclick="ns.doDelete('${card.id}','${dto.photo.id}', this);">删除</button> </div>
					</div>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>
<script type="text/javascript">
var ns = ns || {};
ns.doUseWay = function(cardId,desc,fileStoreId, obj) {
	$(obj).attr('disabled', true);
	$.ajax({
		url : "console/card/photo/doUseWay",
		type : 'post',
        dataType : 'json',
		data : {cardId: cardId,desc:desc,fileStoreId:fileStoreId},
		success : function(data) {
			if (data.code == 0) {
				location.href='${ctx}/console/card/photo/cardphoto/${card.id}?msg='+encodeURIComponent(data.message);
			}
			$(obj).attr('disabled', false);
		}
	});
};
ns.doCancelWay = function(cardId,desc,fileStoreId, obj) {
	$(obj).attr('disabled', true);
	$.ajax({
		url : "console/card/photo/doCancelWay",
		type : 'post',
        dataType : 'json',
		data : {cardId: cardId,desc:desc,fileStoreId:fileStoreId},
		success : function(data) {
			if (data.code == 0) {
				location.href='${ctx}/console/card/photo/cardphoto/${card.id}?msg='+encodeURIComponent(data.message);
			}
			$(obj).attr('disabled', false);
		}
	});
};
ns.doDelete = function(cardId,fileStoreId, obj) {
	if (!confirm('确定要删除该照片吗？')) {
		return;
	}
	$(obj).attr('disabled', true);
	$.ajax({
		url : "console/card/photo/doDelete",
		type : 'post',
        dataType : 'json',
		data : {cardId: cardId,fileStoreId:fileStoreId},
		success : function(data) {
			if (data.code == 0) {
				location.href='${ctx}/console/card/photo/cardphoto/${card.id}?msg='+encodeURIComponent(data.message);
			}
			$(obj).attr('disabled', false);
		}
	});
};

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


