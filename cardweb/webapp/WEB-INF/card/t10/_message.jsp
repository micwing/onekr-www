<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@include file="../../common/includes.jsp"%>
<%
Map<String, String> express = new LinkedHashMap<String, String>();
express.put("爱情", "aiqing.png");
express.put("蛋糕", "dangao.png");
express.put("发财", "facai.png");
express.put("给力", "geili.gif");
express.put("恭喜", "gongxi.png");
express.put("鼓掌", "guzhang.gif");
express.put("坏笑", "huaixiao.gif");
express.put("婚纱", "hunsha.png");
express.put("婚鞋", "hunxie.png");
express.put("脚印", "jiaoyin.png");
express.put("礼花", "lihua.png");
express.put("玫瑰", "meigui.png");
express.put("闪电", "shandian.png");
express.put("帅", "shuai.png");
express.put("喜庆", "xiqing.png");
express.put("携手", "xieshou.gif");
express.put("心形", "xinxing.png");
express.put("心意", "xinyi.png");
express.put("信", "xin.gif");
express.put("钻戒", "zuanjie.png");
express.put("棒棒糖", "bangbangtang.png");
express.put("气球", "qiqiu.png");
express.put("飞机", "feiji.png");
express.put("钞票", "chaopiao.png");
%>
<style>
.contenbox {
	background-color: #FFF;
	padding: 5px 3px;
	box-shadow: 0px 1px 3px rgba(34, 25, 25, 0.4);
	margin-top: 7px;
	border-radius: 5px;
}

.contenbox p {
	padding-top: 2px;
	padding-bottom: 2px;
	margin: 0px !important;
}

.contenbox h6 {
	float: left;
	padding-left: 5px;
}

.contenboxspan {
	float: right;
	padding-right: 10px;
	color: #CCC;
}
</style>
<div class="imgcontent">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr bgcolor="#FF3300" style="color: #FFF">
			<td width="50%" align="center">
				<c:if test="${!empty people1Photo.photo}">
				<img
					src="attached${fn:replace(people1Photo.photo.storePath, '\\', '/')}"
					width="50%">
				</c:if>
			</td>

			<td width="50%" align="center">
				<c:if test="${!empty people2Photo.photo}">
				<img
					src="attached${fn:replace(people2Photo.photo.storePath, '\\', '/')}"
					width="50%">
				</c:if>
			</td>
		</tr>
	</table>
</div>

<style>
.lxdh a {
	color: #FFF;
	font-size: 18px;
}
</style>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr bgcolor="#FF3300" style="color: #FFF">
		<td width="50%" align="center"><div class="lxdh">
				新郎<a href="tel:${card.people1Mobile}">${card.people1Mobile}</a>
			</div></td>
		<td width="50%" align="center">
			<div class="lxdh">
				新娘<a href="tel:${card.people2Mobile}">${card.people2Mobile}</a>
			</div>
		</td>
	</tr>
</table>




<div class="gallery">
	<form action="" method="post" id="commentForm">
		<table width="95%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="27%">姓名</td>
				<td width="73%"><input type="text" name="userName"
					class="jiuba" /></td>
			</tr>
			<tr>
				<td>出席人数</td>
				<td><select name="reply" class="jiuba">
						<option value="0">无法出席</option>
						<option selected value="1">1人出席</option>
						<option value="2">2人出席</option>
						<option value="3">3人出席</option>
						<option value="4">4人出席</option>
						<option value="5">5人出席</option>
						<option value="6">6人出席</option>
						<option value="7">7人出席</option>
						<option value="8">8人出席</option>
						<option value="9">9人出席</option>
						<option value="10">10人出席</option>
				</select></td>
			</tr>
			<tr>
				<td>默认祝福</td>
				<td><select onchange="messageContentShow(this.value)" class="jiuba">
						<option value="新婚快乐，早生贵子!">新婚快乐，早生贵子!</option>
						<option value="敬祝百年好合永结同心！">敬祝百年好合永结同心！</option>
						<option value="天作之合，鸾凤和鸣。">天作之合，鸾凤和鸣。</option>
						<option value="天生才子佳人配，只羡鸳鸯不羡仙。">天生才子佳人配，只羡鸳鸯不羡仙。</option>
				</select></td>
			</tr>
			<tr>
				<td>内容</td>
				<td><div class="topic_pub_form">
						<textarea class="f-textarea pubform" name="content" id="messageContent"
							style="width:; height: 60px;"></textarea>
						<div class="blank"></div>
						<div class="f_l"></div>
						<div>
							<div class="blank1"></div>
							<div class="emotion emotion_con wd" f="wd">
							<% for (Map.Entry<String, String> entry : express.entrySet()) { %>
								<span href="javascript:void(0);" title="<%=entry.getKey() %>" rel="<%=entry.getKey() %>">
								<img src="assets/images/expression/<%=entry.getValue() %>" /></span>
							<%}%>
							</div>
						</div>
						<div class="blank1"></div>
					</div></td>
			</tr>
			<tr>
				<td><input type="hidden" name="cardId" value="${card.id}" /></td>
				<td><input class="jiuba" type="button" value="发表祝福" onclick="doSaveComment()"/></td>
			</tr>
		</table>
	</form>

	<script>
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	};
	
	function messageContentShow(v) {
		document.getElementById("messageContent").innerHTML = v;
	}
	
	function reloadCommentList() {
		$.ajax({
            type: "post",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: "card/listComments",//要访问的后台地址
            data: {
            	'cardId' : '${card.id}'
            },
            success: function(result){
            	if (result.code == 0) {
            		var html ='';
            		if (result.value) {
            			var comments = result.value;
            			for (var i = 0; i < comments.length; i++) {
		            		html += '<div class="contenbox">'
		            		+ '<h6>'+comments[i].userName+'</h6>'
		    				+ '<span class="contenboxspan">'+(new Date(comments[i].createAt)).Format("yyyy-MM-dd hh:mm")+'</span>'
		    				+ '<div class="clr"></div>'
		    				+ '<p>'+replayExpress(comments[i].content)+'</p>'
		    				+ '<p style="text-align: right; color: #ccc">'+(comments[i].json ? comments[i].json+'人出席':'无法出席')+'</p>'
		    				+ '</div>';
            			}
            			$('#mes_con').html(html);
            		}
            	} else {
            	}
            }
		});
	}

	function doSaveComment() {
		if ($("#commentForm input[name=userName]").val() == '') {
			alert("请留下你的大名！");
			return false;
		}
		if ($("#messageContent").val() == '') {
			alert("内容不能为空！");
			return false;
		}
		$.ajax({
            type: "post",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: "card/doSaveComment",//要访问的后台地址
            data: {
            	'userName' : $('#commentForm input[name=userName]').val(),
            	'reply' : $('#commentForm select[name=reply]').val(),
            	'content' : $('#commentForm textarea[name=content]').val(),
            	'cardId' : $('#commentForm input[name=cardId]').val()
            },
            success: function(result){
            	if (result.code == 0) {
	            	alert('发表成功！');
	                reloadCommentList();
            	} else {
            		alert('操作失败！');
            	}
            }
		});
	}
		
	function replayExpress(html) {
		<% for (Map.Entry<String, String> entry : express.entrySet()) { %>
		html = html.replace(/\[<%=entry.getKey()%>\]/g, '<img src="assets/images/expression/<%=entry.getValue()%>" alt="<%=entry.getKey()%>" />');
		<%}%>
		return html;
	}
	$(document).ready(function() {
		$("#messageContent").bind("change keyup", function() {
			var c = $("#messageContent").val();
			if (c.length > 1000) {
				$("#messageContent").val(c.substr(0, 1000));
			}
		});
		
		$(".emotion_con").find("span").bind(
			"click",
			function() {
				$("#messageContent").val($("#messageContent").val() + "[" + $(this).attr("rel")+ "]");
			}
		);
		
		reloadCommentList();
	});
	
	</script>
	<div id="mes_con">
	</div>
</div>
