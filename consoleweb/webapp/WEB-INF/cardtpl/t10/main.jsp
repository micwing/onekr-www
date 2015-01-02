<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<!DOCTYPE html>
<!--[if IEMobile 7 ]>    <html class="no-js iem7"> <![endif]-->
<!--[if (gt IEMobile 7)|!(IEMobile)]><!--> 
<html class="no-js"> <!--<![endif]-->

<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta charset="utf-8">
<title>${card.title}</title>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta http-equiv="cleartype" content="on">
<base href="<%=basePath%>" />
<!--[if lt IE 9]><script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script><![endif]-->
<link href="http://cdn.bootcss.com/normalize/3.0.1/normalize.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.8.1/jquery.min.js"></script>

<link href="http://cdn.bootcss.com/photoswipe/3.0.5/photoswipe.min.css" rel="stylesheet">
<!-- klass，JS类继承模型 -->
<script src="http://cdn.bootcss.com/klass/1.4.0/klass.min.js"></script>
<script src="http://cdn.bootcss.com/photoswipe/3.0.5/code.photoswipe.jquery.min.js"></script>
		   
<script type="text/javascript" src="assets/js/SpryAssets/SpryAccordion.js"></script>

<link rel="stylesheet" type="text/css" href="assets/cardtpl/${card.templetId}/js/SpryAssets/SpryAccordion.css"  />
<link rel="stylesheet" href="assets/cardtpl/${card.templetId}/css/main.css" /> 
<link rel="stylesheet" href="assets/cardtpl/${card.templetId}/css/infostyle.css" />

<style>
header{margin-top:-10px;}
.jiuba {
	width: 100% !important;
	margin-top: 2px !important
}
.gallery table td {
	padding-top: 5px;
	padding-bottom: 3px;
}

.gallery {
	padding: 5px;
}

.redinput {
	width: 20px;
	height: 20px;
	border: 0px;
}
.clr {
	width: 100%;
	clear: both;
	float: none !important
}
.page .footer p.back a:before {
	top: 5px;
}
</style>
</head>
<body class="page">
<div class="container">
	
<div class="header">
	<h1>
	<img class="logo" src="assets/cardtpl/${card.templetId}/images/banner.jpg">                    
	</h1>
</div>

<div class="nav menus">
	<ul>
		<li id="tag1" class="selected"><a href="javascript:void(0);">婚礼信息</a></li>
		<li id="tag2" ><a href="javascript:void(0);">婚纱相册</a></li>
		<li id="tag3" ><a href="javascript:void(0);">留言祝福</a></li>
	</ul>
</div>

<div id="container-page1" name="tags" class="tag1"  style="display:block">
	<div class="textcontent">
	
        <div style="padding:1px;">
			<div id="Accordion1" class="Accordion" tabindex="0">
			  <div class="AccordionPanel">
			    <div class="AccordionPanelTab">婚礼信息</div>
			    <div class="AccordionPanelContent">
			    	<jsp:include page="_cardinfo.jsp"></jsp:include>
			    </div>
			  </div>
			  
			  <div class="AccordionPanel">
			    <div class="AccordionPanelTab">在线导航</div>
			    <div class="AccordionPanelContent">
			    	<jsp:include page="_map.jsp"></jsp:include>
			    </div>
			  </div>
			  
			  <div class="AccordionPanel">
			    <div class="AccordionPanelTab">现场照片</div>
			    <div class="AccordionPanelContent">
			    	 <jsp:include page="_uploadMomentPhoto.jsp"></jsp:include>
			     </div>
			  </div>
			  
			</div>
		</div>
		
	</div>
</div>

<!-- PHOTO START  -->
<div id="container-page2" name="tags" class="tag2" style="display:none">
	<jsp:include page="_photo.jsp"></jsp:include>
</div>
<!-- PHOTO END -->

<!-- MESSAGE START -->
<div id="container-page3" name="tags" class="tag3" style="display:none">
	<jsp:include page="_message.jsp"></jsp:include>
</div>
<!-- MESSAGE END -->

<script type="text/javascript">
var Accordion1 = new Spry.Widget.Accordion("Accordion1");

(function(window, $, PhotoSwipe){
	
	$(document).ready(function(){
		
		var options = {};
		$("#gallery a").photoSwipe(options);
		$("#gallery2 a").photoSwipe(options);
	});
}(window, window.jQuery, window.Code.PhotoSwipe));

$(document).ready(function(){
	$(".nav ul li").click(function(){
	  var change = $(this).attr('id');
	  $(this).addClass("selected").siblings().removeClass("selected");
	  $("div[name=tags]").hide().siblings("."+change).show();		  
	});
});
</script>
	
<div class="footer">
	<p class="back">
		<a href="card/cover/${card.id}" class="link"><span>返回封面</span></a>
	</p>
</div>

</div>
<jsp:include page="_music.jsp"></jsp:include>
<jsp:include page="_footer.jsp"></jsp:include>
<jsp:include page="../../common/analytics.jsp"></jsp:include>
</body>
</html>
