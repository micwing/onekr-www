<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<div class="">
	<p class="ip4">
	<div class="lypho gallery" id="gallery2">
		<div class="collections2"></div>
	</div>

	<h4>请您婚礼当日为新人拍照留下精彩瞬间。点击无反应请点右上角选在浏览器中打开</h4>
	<form encType="multipart/form-data" method="post" name="com_form"
		action="" onsubmit="return check_img();">
		<table border="0" cellSpacing="0" cellPadding="0" width="95%">
			<tr>
				<td></td>
				<td><input class="jiuba" name="upfile" type="file"
					accept="application/jpeg"></td>
			</tr>
			<tr>
				<td><input name="xitieid" value="70" type="hidden"><input
					type="hidden" name="action" value="photo"></td>
				<td><input class="jiuba" value="上传" type="submit"></td>
			</tr>
			</tbody>
		</table>
	</form>

	</p>
</div>
<script type="text/javascript">
	function check_img() {
		if ($("input[name=upfile]").val() == '') {
			alert("请选择您要上传的图片!");
			return false;
		} else {
			if (!/\.(JPG|jpg|JPEG|jpeg)$/.test($("input[name=upfile]").val())) {
				alert("上传格式不正确,请上传JPG,JPEG格式图片");
				return false;
			}
		}
	}
</script>

