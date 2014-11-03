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
<!--  Common CSS,JS Start Mango Filing -->
<!-- This script prevents links from opening in Mobile Safari. -->
<script>(function(a,b,c){if(c in b&&b[c]){var d,e=a.location,f=/^(a|html)$/i;a.addEventListener("click",function(a){d=a.target;while(!f.test(d.nodeName))d=d.parentNode;"href"in d&&(d.href.indexOf("http")||~d.href.indexOf(e.host))&&(a.preventDefault(),e.href=d.href)},!1)}})(document,window.navigator,"standalone")</script>
<link rel="stylesheet" type="text/css" href="assets/css/normalize.css">
<link rel="stylesheet" type="text/css" href="assets/js/toastr/toastr.min.css">
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="assets/js/toastr/toastr.min.js"></script>
<script type="text/javascript" src="assets/js/commons.js"></script>
<script type="text/javascript" src="assets/js/klass.min.js"></script>
<script type="text/javascript" src="assets/js/respond.min.js"></script>
   
<link rel="stylesheet" type="text/css" href="assets/cardtpl/${card.templetId}/js/SpryAssets/SpryAccordion.css"  />
<script type="text/javascript" src="assets/cardtpl/${card.templetId}/js/SpryAssets/SpryAccordion.js"></script>
<link rel="stylesheet" href="assets/cardtpl/${card.templetId}/css/main.css"> 
<link rel="stylesheet" href="assets/cardtpl/${card.templetId}/css/infostyle.css">

<style>
header{margin-top:-10px;}
.jiuba {
	width: 100% !important;
	margin-top: 2px !important
}
</style>
</head>
<body class="page">
<script type="text/javascript">
$(document).ready(function(){
	$(".nav ul li").click(function(){
	  var change = $(this).attr('id');
	  $(this).addClass("selected").siblings().removeClass("selected");
	  $("div[name=tags]").hide().siblings("."+change).show();		  
	});
});
</script>

<div class="container">
	
	<div class="header">
		<h1>
		<img class="logo" src="assets/cardtpl/${card.templetId}/images/53fbef21978bb.jpg">                    
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
	</script>
		
	<div class="footer">
		<p class="back">
			<a href="card/cover/${card.id}" class="link"><span>返回封面</span></a>
		</p>
	</div>
</div>

<jsp:include page="_music.jsp"></jsp:include>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
