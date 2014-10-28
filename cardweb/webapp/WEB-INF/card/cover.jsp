<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<!--[if IEMobile 7 ]>    <html class="no-js iem7"> <![endif]-->
<!--[if (gt IEMobile 7)|!(IEMobile)]><!--> <html class="no-js"> <!--<![endif]-->
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

        <link rel="stylesheet" href="/assets/css/normalize.css">
        <link rel="stylesheet" href="/assets/cardtpl/10001/main.css">
        <script src="/assets/js/respond.min.js"></script>
        <script src="/assets/js/snowstorm.js"></script>
        
    </head>
    <body class="home">

		
        

<div class="container">
	<div class="container-home">
	
		
	<div class="header">
		<div class="btn-collections">
			<a class="ir" id='page_link' href="/card/main/${card.id}">^</a>
		</div>
		<h1 class="title"><a href="#">${card.title}</a></h1>
		<p class="subtitle">${card.people1Name}  & ${card.people2Name} </p>
		<p>2014-10-6</p>
		
	</div>
			<p class="subtitle-comment">
		<a href="/card/main/${card.id}"></a>
		</p>
			
	</div> 

</div>

<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/countdown.js"></script>
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

	var url = "${fn:replace(coverPhoto.storePath, '\\', '/')}";//+ '&w=' + pic_width + '&h=' + pic_height
	$('div.container').css({'background':'url("' + url + '") no-repeat scroll 0 0', 'background-size':'cover'});
	$('div.container').css('height',height);


	TargetDate = "";
	TargetDate = TargetDate.replace(/\-/g, '/');
	date_then = new Date(TargetDate);
	date_now = new Date();
	if(date_now < date_then) {
		$('#countdown').countDown({
			targetDate: {
				'day': 		date_then.getDate(),
				'month': 	date_then.getMonth() + 1,
				'year': 	date_then.getFullYear(),
				'hour': 	date_then.getHours(),
				'min': 		date_then.getMinutes(),
				'sec': 		date_then.getSeconds()
			}
		});
	}else {
		//$('#countdown').hide();
		$('#countdown').countDown({
			targetDate: {
				'day': 		date_then.getDate(),
				'month': 	date_then.getMonth() + 1,
				'year': 	date_then.getFullYear(),
				'hour': 	date_then.getHours(),
				'min': 		date_then.getMinutes(),
				'sec': 		date_then.getSeconds()
			}
		});
		//$('#countdown-name').html('时间已过:');
	}
	
});

</script>
		
     
<script type="text/javascript">
var contentModel = {    
	"img_url": "<%=basePath %>${fn:replace(coverPhoto.storePath, '\\', '/')}",     
	"title": "${card.title} ",     
	"src": "诚挚邀请您共同分享幸福与喜悦" 
};
</script>
<script src="/assets/js/share.js" type="text/javascript" ></script>

<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc66053765b2cb1b2a03cdbbfa97aebf4' type='text/javascript'%3E%3C/script%3E"));
</script>

</body>
</html>