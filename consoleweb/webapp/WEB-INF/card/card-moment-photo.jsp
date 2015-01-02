<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<%@page import="onekr.cardservice.card.intf.CardPhotoFileBiz" %>
<h3>管理现场照片
<span class="pull-right">
	<a class="btn btn-large" href="console/card/info/list">返回列表</a>
</span>
</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

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
		<div class="pull-right">已上传${empty photos ? 0 : fn:length(photos)}张</div>
	</div>
</div>

<style>
.thumbnails .span4 {
	margin-left: 0px;
	margin-right: 5px;
}
</style>
<div class="row-fluid">
	<ul class="thumbnails">
		<c:forEach items="${photos}" var="dto" varStatus="st">
		<li class="span4" >
			<div class="thumbnail" style="text-align: center;">
				<a href="attached${fn:replace(dto.photo.storePath, '\\', '/')}?timestemp=<%=new java.util.Date().getTime()%>"
					class="fancybox" rel="group1"> <img
						src="attached${fn:replace(dto.thumb.storePath, '\\', '/')}?timestemp=<%=new java.util.Date().getTime()%>"
						data-src="holder.js/270x270"></a>
				<p>
					<a href="attached${fn:replace(dto.photo.storePath, '\\', '/')}?timestemp=<%=new java.util.Date().getTime()%>"
					class="fancybox" rel="group2"><i class="icon-search" style="font-size: 40px">&nbsp;</i>查看</a> 
					<a href="javascript:;" onclick="ns.doRotate('${card.id}','${dto.photo.id}', this);"><i class="icon-repeat" style="font-size: 40px">&nbsp;</i>旋转90度</a>
					<a href="javascript:;" onclick="ns.doDelete('${card.id}','${dto.photo.id}', this);" style="color: red"><i class="icon-remove" style="font-size: 40px;">&nbsp;</i>删除</a>
				</p>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
var ns = ns || {};
ns.doRotate = function(cardId, fileStoreId, obj) {
	if (!confirm('确定要旋转该照片吗？')) {
		return;
	}
	$(obj).attr('disabled', true);
	$.ajax({
		url : "console/card/photo/doRotate",
		type : 'post',
        dataType : 'json',
		data : {cardId: cardId,fileStoreId:fileStoreId},
		success : function(data) {
			if (data.code == 0) {
				location.href='${ctx}/console/card/momentphoto/cardphoto/${card.id}?msg='+encodeURIComponent(data.message);
			}
			$(obj).attr('disabled', false);
		}
	});
};
ns.doDelete = function(cardId,fileStoreId, obj) {
	if (!confirm('确定要删除该现场照片吗？')) {
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
				location.href='${ctx}/console/card/momentphoto/cardphoto/${card.id}?msg='+encodeURIComponent(data.message);
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


