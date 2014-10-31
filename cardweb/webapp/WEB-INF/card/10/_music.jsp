<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<style>
#musicControl {
	position: fixed;
	right: 10px;
	top: 80%;
	display: inline-block;
	z-index: 99999999;
}

#musicControl a {
	display: inline-block;
	width: 52px;
	height: 52px;
	overflow: hidden;
	background: url('assets/images/mcbg.png') no-repeat;
}

#musicControl a.stop {
	background-position: left bottom;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		$(document).one('touchstart', function(e) {
			var music = document.getElementById("audio_play");
			if (music.paused) {
				music.play();
			}
		});

	});
	function play_music() {
		if ($('#mc_play').hasClass('on')) {
			$('#mc_play audio').get(0).pause();
			$('#mc_play').attr('class', 'stop');
		} else {
			$('#mc_play audio').get(0).play();
			$('#mc_play').attr('class', 'on');
		}
	}
</script>

<span id="musicControl"> <a id="mc_play" class="on"
	onclick="play_music();"> <audio autoplay="autoplay" id="audio_play"
			loop>
			<source src="http://mp3.360hunjia.com/mob/music/m4.mp3"
				type="audio/mpeg"></source>
		</audio>
</a>
</span>
