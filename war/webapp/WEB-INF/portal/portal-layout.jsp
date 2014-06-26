<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<html lang="en" xmlns:wb="http://open.weibo.com/wb">
    <head>
<%--         <meta charset="utf-8">
        <title>一刻工作室</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <base href="<%=basePath%>"/>

        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>

        <!--Le HTML5 shim, for IE6-8 support of HTML5 elements--> 
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!-- Le styles -->
        <link href="/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/font-awesome.css" media="all" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="/assets/css/jquery.fancybox.css" media="all" />
        <link rel="stylesheet" type="text/css" href="/assets/css/jquery.fancybox-thumbs.css" />
        <link href="/assets/css/animate.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/isotope.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/style.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/style-responsive.css" rel="stylesheet" type="text/css" />
        
        <script src="/assets/js/jquery.js"></script>
        
        <!-- Le fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="/assets/ico/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="/assets/ico/favicon.png">

 --%>
		<meta charset="utf-8">
	    <title>ONEKR</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <base href="<%=basePath%>"/>
	
	    <!-- Le styles -->
	    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="/assets/css/bootstrap-responsive.min.css" rel="stylesheet">
	    <link href="/assets/css/site.css" rel="stylesheet">
	
	    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	    <!--[if lt IE 9]>
	      <script src="/assets/js/html5shiv.js"></script>
	    <![endif]-->
	    
	    <script src="/assets/js/jquery.js"></script>
	    <script src="/assets/js/bootstrap.min.js"></script>
	    <script src="/assets/js/respond.min.js"></script>
	    
	    <script src="/assets/js/jquery.json.js"></script>
	    
	    <script src="/assets/js/validate/jquery.validate.js" type="text/javascript"></script>
		<script src="/assets/js/validate/more_rules.js" type="text/javascript"></script>
		<script src="/assets/js/validate/message_cn.js" type="text/javascript"></script>
		
	    <!-- Fav and touch icons -->
	    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/assets/ico/apple-touch-icon-144-precomposed.png">
	    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/assets/ico/apple-touch-icon-114-precomposed.png">
	    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/assets/ico/apple-touch-icon-72-precomposed.png">
	    <link rel="apple-touch-icon-precomposed" href="/assets/ico/apple-touch-icon-57-precomposed.png">
	    <link rel="shortcut icon" href="/assets/ico/favicon.png">
    </head>

    <body>
	    <div class="container">
	    	<tiles:insertAttribute name="navbar" />
	    	
	      <div class="row">
	        
	        <div class="span12">
	        	<tiles:insertAttribute name="content" />
	        </div><!--/span-->
	      </div><!--/row-->
	
	      <hr>
	
		  <tiles:insertAttribute name="footer" />
	
	    </div>

        <!-- <script src="/assets/js/modernizr.js"></script>一个利用 JS 和 CSS 来检测浏览器说支持功能的小工具
        <script src="/assets/js/bootstrap.js"></script>
        <script src="/assets/js/jquery.fitvids.js"></script>视频自适应网页宽度
        <script src="/assets/js/jquery.easing.1.3.js"></script>一个简单的为对象扩展高级属性和选项的jQuery插件

        <script src="/assets/js/stellar.js"></script>提供任何滚动元素的视差滚动效应
        <script src="/assets/js/jquery.isotope.min.js"></script>Isotope绝对是一个令人难以置信的jQuery插件，你可以用它来创建动态和智能布局。你可以隐藏和显示与过滤项目，重新排序和整理甚至更多。同时Isotope还有许多很酷的动画。
        <script type="text/javascript" src="/assets/js/jquery.fancybox.pack.js"></script>弹出窗口插件 
        <script type="text/javascript" src="/assets/js/jquery.fancybox-thumbs.js"></script>
        <script type="text/javascript" src="/assets/js/jquery.fancybox-media.js"></script>
        <script src="/assets/js/custom.js"></script>
        <script src="/assets/js/jquery.flexslider.js"></script>内容滚动插件 
        <script src="/assets/js/retina.js"></script> -->
        <script charset="utf-8" src="/assets/js/scrolltotop/scrolltotop.js"></script>

		<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
		document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc66053765b2cb1b2a03cdbbfa97aebf4' type='text/javascript'%3E%3C/script%3E"));
		</script>
		
	</body> 
</html>