<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>${card.title}</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="keywords" content="微信请柬,手机请柬,电子请柬,一氪软件工作室" />
<meta name="description" content="一氪软件工作室" />
<script src="http://siteapp.baidu.com/static/webappservice/uaredirect.js" type="text/javascript"></script>
<script type="text/javascript">uaredirect("<%=basePath%>");</script>

<style>
.con_actual_effect{text-align: center;font-family: "微软雅黑";font-size: 16px;height: 30px;margin-left: 110px;}
.con_left_an{float: left;margin: 83px 0px 0px 184px;}
.con_message{font-family: "微软雅黑";text-align: center;margin-left: -3px;}
#con_right_lj {width: 480px;height: 640px;overflow: hidden;float: right;border: 10px solid #d6d6d6;margin-right: 180px;}
.con_erweima_xs{width:90px;height:90px;}
</style>
</head>
<body>
<div style="width:980px;margin:0px auto;">
	<div class="con_actual_effect"><p>部分模板电脑上不兼容html5，用手机微信扫一扫查看实际效果</p></div>
	<div class="con_left_an">
		<p><a href="<%=basePath%>" target="_blank">返回网站首页</a></p>
		<p><a><img src="<%=basePath%>qr2dCode?m=<%=basePath%>/card/cover/${card.id}&w=300&h=300"  class ="con_erweima_xs" style="margin-top: -18;"></a></p>
		<div  class="con_message" style="font-size: 12px;line-height: 5px;margin-top: -17px;">
			<p>手机微信扫一扫</p>
			<p>查看实际效果</p>
		</div>
	</div>
	<div id="con_right_lj">
		<iframe src="<%=basePath%>/card/cover/${card.id}" name="cont_iframe"  frameborder="none" id="cont_iframe" width="100%" height="100%">
		</iframe>
	</div>
</div>
</body>
</html>