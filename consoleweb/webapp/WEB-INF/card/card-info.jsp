<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.CardType" %>
<%@include file="../common/includes.jsp"%>
<script language="javascript" type="text/javascript" src="assets/js/My97DatePicker/WdatePicker.js"></script>
<style>
.star{
	color: red;
}
</style>
<h3>请柬信息
<c:if test="${!empty card}">
	<span class="pull-right"><a class="btn" href="card/photo/cardphoto/${card.id}">下一步管理照片</a></span>
</c:if>
</h3>
<hr class="head-hr">

<jsp:include page="_message.jsp"/>

<ul class="nav nav-tabs">
	<li class="active"><a href="#tab1" data-toggle="tab"><%=CardType.WED_CARD.getLabel() %></a></li>
	<%-- <li><a href="#tab2" data-toggle="tab"><%=CardType.BABY_CARD.getLabel() %></a></li>
	<li><a href="#tab3" data-toggle="tab"><%=CardType.BIRTHDAY_CARD.getLabel() %></a></li> --%>
</ul>
<div class="tab-content">
	<div class="tab-pane active" id="tab1">
		<form class="form-horizontal" id="card-form" method="post" action="card/info/doSave">
			<input type="hidden" name="id" value="${card.id}">
			<input type="hidden" name="makecode" value="${makecode}">
			<input type="hidden" name="cardType" value="WED_CARD" />
			<fieldset>
				<legend>新人</legend>
				<div class="control-group">
					<label class="control-label" for="people1Name"><span class="star">*</span> 新郎姓名</label>
					<div class="controls">
						<input type="text" name="people1Name" placeholder="people1Name" value="${card.people1Name}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="people1Mobile">新郎手机号码</label>
					<div class="controls">
						<input type="text" name="people1Mobile" placeholder="people1Mobile" value="${card.people1Mobile}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="people2Name"><span class="star">*</span> 新娘姓名</label>
					<div class="controls">
						<input type="text" name="people2Name" placeholder="people2Name" value="${card.people2Name}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="people2Mobile">新娘手机号码</label>
					<div class="controls">
						<input type="text" name="people2Mobile" placeholder="people2Mobile" value="${card.people2Mobile}">
					</div>
				</div>
			</fieldset>

			<fieldset>
				<legend>典礼</legend>
				<div class="control-group">
					<label class="control-label" for="partyTime"><span class="star">*</span> 典礼时间</label>
					<div class="controls">
						<input type="text" class="Wdate" name="partyTime" placeholder="partyTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${card.partyTime}" type="both"/>"/>
						<span class="help-block">使用时间控件选择</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="restaurant"><span class="star">*</span> 酒店名称</label>
					<div class="controls">
						<input type="text" class="input-block-level" name="restaurant" placeholder="restaurant" value="${card.restaurant}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="address">酒店地址</label>
					<div class="controls">
						<input type="text" class="input-block-level" name="address" placeholder="address" value="${card.address}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="traffic">交通方式</label>
					<div class="controls">
						<input type="text" class="input-block-level" name="traffic" placeholder="traffic" value="${card.traffic}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remind">温馨提醒</label>
					<div class="controls">
						<input type="text" class="input-block-level" name="remind" placeholder="remind" value="${empty card.remind ? '喝酒不开车，开车不喝酒' : card.remind}">
						<span class="help-block">示例：喝酒不开车，开车不喝酒</span>
					</div>
				</div>
			</fieldset>

			<fieldset>
				<legend>请柬</legend>
				<div class="control-group">
					<label class="control-label" for="title"><span class="star">*</span> 标题</label>
					<div class="controls">
						<input type="text" class="input-block-level" name="title" placeholder="title" value="${card.title}">
						<span class="help-block">标题根据已经填写的内容自动生成，也可以手动修改。</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="beforeInfo"><span class="star">*</span> 前置语句</label>
					<div class="controls">
						<input type="text" class="input-block-level" name="beforeInfo" placeholder="beforeInfo" value="${empty card.beforeInfo ? '沉浸在幸福中的我们&lt;br/&gt;谨定于' : card.beforeInfo}">
						<span class="help-block">示例：沉浸在幸福中的我们&lt;br/&gt;谨定于</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="afterInfo"><span class="star">*</span> 后置语句</label>
					<div class="controls">
						<input type="text" class="input-block-level" name="afterInfo" placeholder="afterInfo" value="${empty card.afterInfo ? '举行典礼&lt;br/&gt;敬备喜宴 恭请光临' : card.afterInfo}">
						<span class="help-block">示例：举行典礼&lt;br/&gt;敬备喜宴 恭请光临</span>
					</div>
				</div>
				
			</fieldset>

			<fieldset>
				<legend>系统</legend>
				<div class="control-group">
					<label class="control-label" for="templetId"><span class="star">*</span> 选择模板</label>
					<div class="controls">
						<select name="templetId">
							<option value="10001" ${card.templetId == 10001 ? 'selected':'' }>浪漫爱情</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remark">备注</label>
					<div class="controls">
						<textarea name="remark" class="input-block-level" placeholder="remark">${card.remark}</textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="status"><span class="star">*</span> 状态</label>
					<div class="controls">
						<select name="status">
							<option value="NORMAL" ${card.status == 'NORMAL' ? 'selected':'' }>正常</option>
							<option value="PAUSED" ${card.status == 'PAUSED' ? 'selected':'' }>停用</option>
						</select>
					</div>
				</div>
			</fieldset>

			<br> <br>
			<div class="control-group">
				<div class="controls">
					<button type="button" class="btn btn-primary" id="saveCard">保存</button>
				</div>
			</div>

		</form>
		<script>
			var ns = ns || {};
			ns.updateTitle = function() {
				$('#card-form input[name=title]').val('我们结婚啦！'+$('#card-form input[name=people1Name]').val()+'&'+$('#card-form input[name=people2Name]').val());
			};
			
			$('#card-form').validate({
				rules : {
					people1Name : {
						required : true,
						maxlength : 20
					},
					people1Mobile : {
						maxlength : 20,
						number: true
					},
					people2Name : {
						required : true,
						maxlength : 20
					},
					people2Mobile : {
						maxlength : 20,
						number: true
					},
					partyTime : {
						required : true
					},	
					restaurant : {
						required : true,
						maxlength : 50
					},
					address : {
						maxlength : 50
					},
					traffic : {
						maxlength : 50
					},
					remind : {
						maxlength : 100
					},
					title : {
						required : true,
						maxlength : 50
					},
					beforeInfo : {
						required : true,
						maxlength : 255
					},
					afterInfo : {
						required : true,
						maxlength : 255
					},
					templetId : {
						required : true,
						maxlength : 50
					},
					remark : {
						maxlength : 255
					},
					status : {
						required : true,
						maxlength : 50
					}
				},
				errorClass : "help-block",
				errorElement : "span",
				highlight : function(element, errorClass, validClass) {
					$(element).parents('.control-group').removeClass('success');
					$(element).parents('.control-group').addClass('error');
				},
				unhighlight : function(element, errorClass, validClass) {
					$(element).parents('.control-group').removeClass('error');
					$(element).parents('.control-group').addClass('success');
				}
			});
			$(function() {
				$('#saveCard').click(function(){
					$('#card-form').submit();
				});
				$('#card-form input[name=people1Name]').change(function() {
					ns.updateTitle();
				});
				$('#card-form input[name=people2Name]').change(function() {
					ns.updateTitle();
				});
			});
		</script>
	</div>
</div>



