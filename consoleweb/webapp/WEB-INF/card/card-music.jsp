<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<h4>请柬音乐</h4>
<hr>
<form class="form-horizontal" id="card-form" method="post"
	action="card/info/doSave">

	<div class="control-group">
		<label class="control-label" for="musicUrl">选择音乐</label>
		<div class="controls">
			<input type="text" name="musicUrl" placeholder="musicUrl">
		</div>
	</div>
	
	<br> <br>
	<div class="control-group">
		<div class="controls">
			<input type="hidden" id="isGotoPhoto" value="false">
			<button type="submit" class="btn btn-primary" id="saveCard">保存并查看</button>
		</div>
	</div>

</form>
