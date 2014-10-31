<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<!DOCTYPE html>
<!--[if IEMobile 7 ]>    <html class="no-js iem7"> <![endif]-->
<!--[if (gt IEMobile 7)|!(IEMobile)]><!--> 
<html class="no-js"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<title>${card.title}</title>
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width">
<meta http-equiv="cleartype" content="on">
<base href="<%=basePath%>" />

<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- This script prevents links from opening in Mobile Safari. -->
<script>(function(a,b,c){if(c in b&&b[c]){var d,e=a.location,f=/^(a|html)$/i;a.addEventListener("click",function(a){d=a.target;while(!f.test(d.nodeName))d=d.parentNode;"href"in d&&(d.href.indexOf("http")||~d.href.indexOf(e.host))&&(a.preventDefault(),e.href=d.href)},!1)}})(document,window.navigator,"standalone")</script>

<link rel="stylesheet" href="assets/css/normalize.css">
<link rel="stylesheet" href="assets/cardtpl/${card.templetId}/css/main.css">
<script src="assets/js/respond.min.js"></script>
<script src="assets/js/snowstorm.js"></script>
    
</head>
<body class="home">
<div class="container">
	<div class="container-home">
		<div class="header">
			<div class="btn-collections">
				<a class="ir" id='page_link' href="card/main/${card.id}">^</a>
			</div>
			<h1 class="title"><a href="#">${card.title}</a></h1>
			<p class="subtitle">${card.people1Name}  & ${card.people2Name} </p>
			<p>${card.partyDate}</p>
		</div>
		<p class="subtitle-comment">
			<a href="card/main/${card.id}"></a>
		</p>
	</div> 
</div>

<script src="assets/js/jquery.js"></script>
<script>
function get_viewport_width() {
	var width;
	if (window.innerWidth != undefined) {	// all but IE 
		width =  window.innerWidth;
	} else if (document.documentElement != undefined) {		// IE
		width = document.documentElement.clientWidth;
	} 

	// to support pc preview
	if (document.body.clientWidth != undefined) {
		if ( width > document.body.clientWidth) {
			width = document.body.clientWidth;
		}
	}

	return width;
}

function get_viewport_height() {
	var height;
	if (window.innerHeight != undefined) {
		height =  window.innerHeight;
	} else if (document.documentElement != undefined) {
		height = document.documentElement.clientHeight;
	}

	return height;
}

$(function () {
	var width = get_viewport_width();
	var height = get_viewport_height();

	if(height / width > 3) { // when Android browser initialized, window.innerHeight property might be incorrect
		height = width * 488 / 320;
	}
	
	// to support mobile
	var pic_width = width;
	var pic_height = height;
	if (window.devicePixelRatio != undefined) {
		pic_width = width * window.devicePixelRatio;
		pic_height = height * window.devicePixelRatio;
	}
	var max_width = 1024;
	pic_width = max_width;
	pic_height = max_width * height / width;

	var url = "${ctx}${fn:replace(coverPhoto.storePath, '\\', '/')}";//+ '&w=' + pic_width + '&h=' + pic_height
	$('div.container').css({'background':'url("' + url + '") no-repeat scroll 0 0', 'background-size':'cover'});
	$('div.container').css('height',height);

});

</script>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>