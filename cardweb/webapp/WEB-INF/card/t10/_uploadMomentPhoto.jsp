<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<div class="">
	<p class="ip4">
	
	<c:if test="${!empty moments}">
	<div class="lypho gallery" id="gallery2">
		<ul class="collections2">
			<c:forEach items="${moments}" var="fileStore" varStatus="st">
				<li><a href="attached${fn:replace(fileStore.photo.storePath, '\\', '/')}"><img
						src="attached${fn:replace(fileStore.thumb.storePath, '\\', '/')}" alt=""></a>
				</li>
				<c:if test="${ (st.index > 0) && (st.index%5==4) }">
					<div class="clr"></div>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	</c:if>

	<h4>请您婚礼当日为新人拍照留下精彩瞬间。点击无反应请点右上角选在浏览器中打开</h4>
	<form enctype="multipart/form-data" method="post" 
		action="card/doUploadMemontPhoto" onsubmit="return check_img();">
		<table border="0" cellSpacing="0" cellPadding="0" width="95%">
			<tr>
				<td></td>
				<td><input class="jiuba" name="file" type="file"
					accept="application/jpeg"></td>
			</tr>
			<tr>
				<td><input name="cardId" value="${card.id}" type="hidden"></td>
				<td><input class="jiuba" value="上传" type="submit"></td>
			</tr>
		</table>
	</form>

	</p>
</div>
<script type="text/javascript">
	function check_img() {
		if ($("input[name=file]").val() == '') {
			alert("请选择您要上传的图片!");
			return false;
		} else {
			if (!/\.(JPG|jpg|JPEG|jpeg)$/.test($("input[name=file]").val())) {
				alert("上传格式不正确,请上传JPG,JPEG格式图片");
				return false;
			}
		}
	}
</script>

