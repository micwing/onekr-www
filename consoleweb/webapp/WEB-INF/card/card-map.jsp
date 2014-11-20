<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<%@page import="java.io.File"%>
<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer"%>
<%@page import="onekr.commonservice.filestore.intf.FileBiz"%>
<%@page import="onekr.framework.utils.FileUtil"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="onekr.cardservice.model.Card"%>
<style type="text/css">

#allmap {
	width: 100%;
	height: 500px;
}

#allmap img {
    max-width: none;
}

#r-result {
	width: 100%;
}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=nBM1MoOPrltzt4VC8AFoYA6D"></script>

<h3>
	请柬地图 
	<span class="pull-right">
	    <span class="btn-group">
			<a class="btn" href="card/photo/cardphoto/${card.id}">上一步管理照片</a>
			<a class="btn" href="card/music/cardmusic/${card.id}">下一步选择音乐</a>
	    </span>
	</span>
</h3>
<hr class="head-hr">

<jsp:include page="../util/message.jsp"/>

<c:if test="${!empty card.mapPicUrl}">
<div class="row-fluid" id="baiduMapDiv">
	<div class="span12">
		<fieldset>
			<legend>地图图片</legend>
			<section>
				<img src="${card.mapPicUrl}" alt="baiduMapPicture"/>
			</section>
			<section>
				<button type="button" class="btn btn-danger btn-large" id="modifyMapurl">重新选择</button>
			</section>
		</fieldset>
	</div>
</div>
<br>
<script type="text/javascript">
$('#modifyMapurl').click(function() {
	$('#baiduMapDiv').hide();
	$('#baiduMapChooseDiv').show();
});
</script>
</c:if>

<div class="row-fluid" id="baiduMapChooseDiv" style="display: ${empty card.mapPicUrl ? 'block' : 'none'}">
	<div class="span12">
	<fieldset>
		<legend>百度地图查找</legend>
			
		<section>
			<div id="r-result">在地图上搜索婚礼位置:<input type="text"  id="suggestId" size="20" value="" class="input-block-level"/></div>
			<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
		</section>
		<section>
			<div id="allmap"></div>
		</section>
		<section>
		<div class="pull-right">
			<form id="map-form" action="card/map/doUpdateMap" method="post">
				<input type="hidden" name="mapPicUrl">
				<input type="hidden" name="mapUrl">
				<input type="hidden" name="cardId" value="${cardId}">
				<span>请拖拽地图，把红色标记对准酒店所在位置</span>
				<button type="button" class="btn btn-primary btn-large" id="updateMapUrl">确定</button>
				<c:if test="${!empty card.mapPicUrl}">
				<button type="button" class="btn btn-large" id="cancelMapUrl">取消</button>
				<script type="text/javascript">
				$('#cancelMapUrl').click(function() {
					$('#baiduMapDiv').show();
					$('#baiduMapChooseDiv').hide();
				});
				</script>
				</c:if>
			</form>
		</div>
		</section>
	</fieldset>
	</div>
</div>

<script type="text/javascript">
var ns = ns || {};

function G(id) {
	return document.getElementById(id);
}

// 百度地图API功能
var map = new BMap.Map("allmap");
map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。
//滚轮
map.enableScrollWheelZoom(true);
map.addEventListener('tilesloaded', function() {
	addPointMarker(map.getCenter());
});

$('#suggestId').keyup(function(e) {
	if (e.keyCode == 13 && $('#suggestId').val() != '') {
		myValue = $('#suggestId').val();
		setPlace();
	}
});
/*==========================================================自动完成功能start===============================================================*/
var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
	{"input" : "suggestId"
	,"location" : map
});
ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
	var _value = e.fromitem.value;
	var value = "";
	if (e.fromitem.index > -1) {
		value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	}    
	str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
	
	value = "";
	if (e.toitem.index > -1) {
		_value = e.toitem.value;
		value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	}    
	str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
	G("searchResultPanel").innerHTML = str;
});

var myValue;
ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
	myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
	
	setPlace();
});

ac.setInputValue('${card.restaurant}');
myValue = '${card.restaurant}';
setPlace();

var marker;
function setPlace(){
	map.clearOverlays();    //清除地图上所有覆盖物
	function myFun(){
		var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
		map.centerAndZoom(pp, 18);
		
		addPointMarker(pp);
	}
	var local = new BMap.LocalSearch(map, { //智能搜索
	  onSearchComplete: myFun
	});
	local.search(myValue);
}

function addPointMarker(pp) {
	map.clearOverlays();
	marker = new BMap.Marker(pp,{
	    enableDragging: false,
	    raiseOnDrag: false
	});
	map.addOverlay(marker);    //添加标注
}

/*==========================================================自动完成功能end===============================================================*/
	
/*==========================================================控件和比例尺start===============================================================*/
//比例尺
var top_left_control = new BMap.ScaleControl({
	anchor : BMAP_ANCHOR_TOP_LEFT
});// 左上角，添加比例尺
var top_left_navigation = new BMap.NavigationControl(); //左上角，添加默认缩放平移控件
var top_right_navigation = new BMap.NavigationControl({
	anchor : BMAP_ANCHOR_TOP_RIGHT,
	type : BMAP_NAVIGATION_CONTROL_SMALL
}); //右上角，仅包含平移和缩放按钮

//添加控件和比例尺
function add_control() {
	map.addControl(top_left_control);
	map.addControl(top_left_navigation);
}
add_control();

/* //移除控件和比例尺
function delete_control() {
	map.removeControl(top_left_control);
	map.removeControl(top_left_navigation);
} */

/*==========================================================控件和比例尺end===============================================================*/

$(function() {
	
	$('#updateMapUrl').click(function() {
		var lng = marker.getPosition().lng;
		var lat = marker.getPosition().lat;
		var rest = encodeURIComponent('婚礼位置').toUpperCase();
		var addr = encodeURIComponent('${card.address}').toUpperCase();
		var zoom = map.getZoom();
		var mapPicUrl = 'http://api.map.baidu.com/staticimage?center='+lng+','+lat+'&markers='+lng+','+lat+'&width=600&height=330&zoom='+zoom;
		var mapUrl = 'http://api.map.baidu.com/marker?location='+lat+','+lng+'&title='+rest+'&content='+addr+'&output=html';
		$('#map-form input[name=mapPicUrl]').val(mapPicUrl);
		$('#map-form input[name=mapUrl]').val(mapUrl);
		$('#map-form').submit();
		$('#updateMapUrl').attr('disabled', true);
	});
});
</script>




