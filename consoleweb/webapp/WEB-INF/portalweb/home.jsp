<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
	<div id="myCarousel" class="carousel slide">
	    <div class="carousel-inner">
		    ${HOME_SLIDER.value}
	    </div>
	    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
	    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
    <script>
    $('.carousel').carousel({
        interval: 10000
    });
    </script>