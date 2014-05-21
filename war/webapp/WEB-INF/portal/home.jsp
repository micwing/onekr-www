<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
	<div id="myCarousel" class="carousel slide">
	    <ol class="carousel-indicators">
		    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		    <li data-target="#myCarousel" data-slide-to="1"></li>
		    <li data-target="#myCarousel" data-slide-to="2"></li>
		    <li data-target="#myCarousel" data-slide-to="3"></li>
	    </ol>
	    <!-- Carousel items -->
	    <div class="carousel-inner">
		    <div class="item active">
		    	<img alt="" src="/assets/img/1.jpg" style="width: 1170px">
		    	<div class="carousel-caption">
				<h4>会设计的程序员和会编程的设计师</h4>
				<p>优秀的设计师和程序员统治着网络–毕竟是他们创建的网站。而那些创建最好玩和最有用的网站的人，往往都是两种技能兼而有之，他们可以将两种完全不同的视觉语言和技术语言运用自如。</p>
				</div>
		    </div>
		    <div class="item">
		    	<img alt="" src="/assets/img/2.jpg" style="width: 1170px">
		    	<div class="carousel-caption">
				<h4>程序猿的工资级别，请对号入座</h4>
				<p>码畜：年入低于3万；码奴：年入3万到6万；码农：年入6万到10万；IT民工：年入10万；IT工程师：年入20万；IT人才：年入50万；IT精英：年入百万......</p>
				</div>
		    </div>
		    <div class="item">
		    	<img alt="" src="/assets/img/3.jpg" style="width: 1170px">
		    	<div class="carousel-caption">
				<h4>别懒</h4>
				<p>想想吧，因为我们的懒惰，总想着来日方长，做何事都能拖则拖，竟致那么多的计划、旅行、恋爱、对人生的探究未见实行！大难不至，我们就会什么也不做，我们会发现自己又回到日复一日的平庸生活，生活的欲望被消磨殆尽。</p>
				</div>
		    </div>
		    <div class="item">
		    	<img alt="" src="/assets/img/4.jpg" style="width: 1170px">
		    	<div class="carousel-caption">
				<h4>不争，不辩，不急，不燥</h4>
				<p>淡泊就是对世间事洞明后的淡然，不争，不辩，不急，不燥，以平常心的心态去面对生活。</p>
				</div>
		    </div>
	    </div>
	    <!-- Carousel nav -->
	    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
	    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
    <script>
    $('.carousel').carousel({
        interval: 10000
    })
    </script>