<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<%@page import="java.io.File"%>
<%@page
	import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer"%>
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
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=nBM1MoOPrltzt4VC8AFoYA6D"></script>

<h4>
	请柬地图 <span class="pull-right"><a class="btn" href="#">下一步选择音乐</a></span>
</h4>
<hr>

<c:if test="${!empty mappic}">
<div class="row-fluid">
	<div class="span12">
		<img src="${ctx}${fn:replace(mappic.storePath, '\\', '/')}" alt="" height="400" width="330"/>
	</div>
	<div class="span12">
		<button type="button" class="btn btn-danger" id="deleteMappic">删除</button>
	</div>
</div>
<script type="text/javascript">
$('#deleteMappic').click(function() {
	if (!confirm('确定要删除该地图图片吗？')) {
		return;
	}
	location.href = '/card/map/doDelete?cardId=${card.id}&fileStoreId=${mappic.id}';
});
</script>
</c:if>

<br>

<div class="row-fluid">
	<div class="span12">
		<fieldset>
			<legend>上传图片文件</legend>
			<form class="form-horizontal" action="/card/map/doUploadFile"
						method="post" enctype="multipart/form-data" id="file-form">
						<div class="control-group">
							<label class="control-label" for="file">选择图片</label>
							<div class="controls">
								<input type="hidden" name="cardId" value="${card.id}" /> <input
									type="file" id="file" name="file" value="选择文件"
									placeholder="file" />
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input class="btn btn-primary" type="submit" id="uploadButton"
									value="上传地图图片" />
							</div>
						</div>
			</form>
		</fieldset>
	</div>
</div>

<br>
	
<div class="row-fluid">
	<div class="span12">
	<fieldset>
		<legend>百度地图查找</legend>
			
		<p>
			<div id="r-result">请输入:<input type="text"  id="suggestId" size="20" value="百度" class="input-block-level"/></div>
			<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
		</p>
		<p class="text-left">
			<span>推荐尺寸 400px * 330px</span>
		</p>
		<p class="text-right">
			<a href="http://map.baidu.com/" class="btn" target="_blank">访问百度地图</a> 
			<button type="button" class="btn" id="showMapTool">显示地图工具</button>
			<button type="button" class="btn" id="hideMapTool" style="display: none">隐藏地图工具</button>
			<br>
		</p>
		<p>
			<div id="allmap"></div>
		</p>
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
function setPlace(){
	map.clearOverlays();    //清除地图上所有覆盖物
	function myFun(){
		var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
		map.centerAndZoom(pp, 18);
		
		var marker = new BMap.Marker(pp);
		map.addOverlay(marker);    //添加标注
		marker.enableDragging();
	}
	var local = new BMap.LocalSearch(map, { //智能搜索
	  onSearchComplete: myFun
	});
	local.search(myValue);
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

//移除控件和比例尺
function delete_control() {
	map.removeControl(top_left_control);
	map.removeControl(top_left_navigation);
}
delete_control();

$('#showMapTool').click(function() {
	$('#hideMapTool').show();
	$('#showMapTool').hide();
	add_control();
});
$('#hideMapTool').click(function() {
	$('#hideMapTool').hide();
	$('#showMapTool').show();
	delete_control();
});
/*==========================================================控件和比例尺end===============================================================*/
</script>




