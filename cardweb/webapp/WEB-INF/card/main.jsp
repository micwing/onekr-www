<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<!--[if IEMobile 7 ]>    <html class="no-js iem7"> <![endif]-->
<!--[if (gt IEMobile 7)|!(IEMobile)]><!--> <html class="no-js"> <!--<![endif]-->


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
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
        <link rel="stylesheet" type="text/css" href="assets/css/normalize.css">
        <link rel="stylesheet" type="text/css" href="assets/js/toastr/toastr.min.css">
		<link rel="stylesheet" type="text/css" href="assets/js/photoswipe/photoswipe.css" />        
        <script type="text/javascript" src="assets/js/jquery.js"></script>
        <script type="text/javascript" src="assets/js/toastr/toastr.min.js"></script>
       	<script type="text/javascript" src="assets/js/commons.js"></script>
		<script type="text/javascript" src="assets/js/klass.min.js"></script>
		<script type="text/javascript" src="assets/js/photoswipe/code.photoswipe.jquery-3.0.5.min.js"></script>
        <script type="text/javascript" src="assets/js/respond.min.js"></script>
        <!--  Common CSS,JS end Mango-->
        <link rel="stylesheet" href="http://www.360hunjia.com/app/Tpl/fanwe/mob/t7/css/main.css"> 
        <link rel="stylesheet" href="http://www.360hunjia.com/app/Tpl/fanwe/mob/t7/css/infostyle.css">
	</head>
    <body class="page" onLoad="doOptions()" >
<script type="text/javascript">


function jumpMsg(guestid){
		var i = "tag3";
		$(".nav ul li").removeClass("selected").siblings("#"+i).addClass("selected");
		$("div[name=tags]").hide("slow").siblings("."+i).show("slow");
		$("input[name=name]").val($("td[guestid="+guestid+"]").html());
	
	}
$(document).ready(function(){
	$(".nav ul li").click(function(){
		  var change = $(this).attr('id');
		   
		  $(this).addClass("selected").siblings().removeClass("selected");
		  $("div[name=tags]").hide().siblings("."+change).show();		  
		});
	
		 	
});
</script>
<style>
.link span{display:block; margin-top:-32px; margin-left:15px}
header{margin-top:-10px;}
    #musicControl{
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
    background: url('http://www.360hunjia.com/app/Tpl/fanwe/mob/images/mcbg.png') no-repeat;
    }
    #musicControl a.stop {
    background-position: left bottom;
    }
</style>

   <script type="text/javascript">
                
        $(document).ready(function(){    

         
                $(document).one('touchstart', function (e) {
                var music = document.getElementById("audio_play");
                if (music.paused) { 
                music.play(); 
                } 
                });
           
        });
function play_music(){
    if ($('#mc_play').hasClass('on')){
        $('#mc_play audio').get(0).pause();
        $('#mc_play').attr('class','stop');
    }else{
        $('#mc_play audio').get(0).play();
        $('#mc_play').attr('class','on');
    }
}
    </script>


		<div class="container">
	
			<div class="header">
				<h1>
					
                    <!--1-->
                    <a href="http://www.360hunjia.com/youhui.php?ctl=store&act=mobview&id=47297"><img class="logo" src="http://www.360hunjia.com/public//attachment/201408/26/10/53fbef21978bb.jpg"></a>                    
                    
                    

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
		<script type="text/javascript" src="http://www.360hunjia.com/app/Tpl/fanwe/mob/SpryAssets/SpryAccordion.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="http://www.360hunjia.com/app/Tpl/fanwe/mob/t7//SpryAssets/SpryAccordion.css"  />
        <div style="padding:1px;">

<div id="Accordion1" class="Accordion" tabindex="0">
  <div class="AccordionPanel">
    <div class="AccordionPanelTab">婚礼信息</div>
    <div class="AccordionPanelContent"><div class="">
  
  

  
  <div class="mid">
  <div class="invitext">
              <p class="ip2" >${card.beforeInfo}</p>

  <header>
    <div class="eight columns header"> <span class="date"> ${card.partyTimeInfo}</span>
      <div class="text clearfix">
        <h1 class="h-left"><div>${card.people1Name}<span>新郎</span></div> </h1>
        <h1 class="h-right"><div>${card.people2Name}<span>新娘</span></div></h1>
      </div>
    </div>
  </header>  


              <p class="ip2" >${card.afterInfo}</p>
			  
			  <br>
             
			    <p class="ip6" style="line-height:12px;">时间：<span>${card.partyTimeInfo}</span></p>
			 
                <p class="ip5"  style="line-height:16px;">席设：<span>${card.restaurant}</span></p>			  
                <p class="ip5"  style="line-height:16px;">地点：<span>${card.address}</span></p>
             <p class="ip4" style="font-size:17px; line-height:24px; margin-top:20px;">交通路线:${card.traffic}<br />
温馨提醒:${card.remind}<br />
    <br />
    </p>

            </div>
  </div>
    <footer>
  </footer>
</div></div>
  </div>
  <div class="AccordionPanel">
    <div class="AccordionPanelTab">在线导航</div>
    <div class="AccordionPanelContent"><div class="">
                    <div style="height:10px;"></div>

            <!--<div id="allmap" class="mid" ></div>-->
         
        
        <INPUT style="WIDTH: 100%" id="location" name="location" value="苏州市工业园区李公堤3期22幢(近金鸡湖大道)" type="hidden"> 
<INPUT  id=cityname name=cityname value="" style="display:none"> 
  <DIV id=optionsNarrative></DIV> 
  <style>
  .mapimg img{width:90%!important;}
  .lypho h4{font-size:medium; font-weight:700;}

  </style>
        <script>
		
	var SAMPLE_ADVANCED_POST = 'http://api.map.baidu.com/geocoder/v2/?ak=5dbd6ef42378bdd6ed202331c10a27c8&callback=renderOption&output=json';

var advancedOptions = '';
var address
function showOptionsURL(type) {
    advancedOptions = SAMPLE_ADVANCED_POST;   
	address = document.getElementById('location').value; 
    var cityname=document.getElementById('cityname').value;
	advancedOptions+="&address="+address;
	advancedOptions+="&city="+cityname;

    
    var safe = advancedOptions;
    //document.getElementById('optionsSampleUrl').innerHTML = safe.replace(/</g, '&lt;').replace(/>/g, '&gt;');
};

function renderOption(response) {
    var html = '';

		if (response.status ) {
			var text = "无正确的返回结果:\n";
			document.getElementById('optionsNarrative').innerHTML = text;
			return;
		}
		var location = response.result.location;
		var uri = 'http://api.map.baidu.com/marker?location='+ location.lat +','+location.lng +'&title=婚礼位置&content='+address+'&output=html';
		var staticimageUrl = "http://api.map.baidu.com/staticimage?center=" +location.lng+','+location.lat + "&markers=" + location.lng+','+location.lat;
		
		html += '<p style="text-align:center"><a href="' + uri + '"><img class="mapimg" src="' + staticimageUrl + 'width=600&height=330&zoom=14"/></a></p><p style="text-align:center; line-heigt:30px;">朗庭别苑<a href="' + uri + '"><img src=http://www.360hunjia.com/app/Tpl/fanwe/mob/images/yjdh.png /></a></p>' ;
		document.getElementById('optionsNarrative').innerHTML = html;
		return;
    
    document.getElementById('optionsNarrative').innerHTML = html;
}

function doOptions() {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    showOptionsURL('buttonClick');
    var newURL = advancedOptions.replace('5dbd6ef42378bdd6ed202331c10a27c8','5dbd6ef42378bdd6ed202331c10a27c8');
    script.src = newURL;
    document.body.appendChild(script);
};


		
		</script>
        
        <div style="height:10px;"></div>
        
        
        </div></div>
  </div>
  
    <div class="AccordionPanel">
    <div class="AccordionPanelTab">现场照片</div>
    <div class="AccordionPanelContent"> <div class=""><p class="ip4">
    <div class="lypho gallery" id="gallery2">
     <div class="collections2">
     		
				</div>
      
     
     </div>
     <style>
	 .jiuba{width:100%!important; margin-top:2px!important}
	 </style>
     <h4>请您婚礼当日为新人拍照留下精彩瞬间。点击无反应请点右上角选在浏览器中打开</h4>
     <form encType="multipart/form-data" method="post" name="com_form" action="" onsubmit="return check_img();">
    	<table border="0" cellSpacing="0" cellPadding="0" width="95%">
            <tr><td></td><td><input class="jiuba" name="upfile" type="file" accept="application/jpeg"> </td></tr>
            <tr><td>
            		<input name="xitieid" value="70" type="hidden"><input type="hidden" name="action" value="photo">
           			</td><td><input class="jiuba" value="上传" type="submit"></td></tr>
        </tbody></table>
    </form>
     
        </p></div></div>
  </div>
  
  <style>
.zhuobox th{background-color:#ff5757; font-size:12px; font-weight:normal; padding-top:4px; padding-bottom:4px; color:#FFF; padding-left:3px;}
.zhuobox td{padding-left:3px; padding-top:5px; padding-bottom:5px; font-size:13px;}
.zhuobox td b{color:#F00; text-decoration:none}
.contenbox{background-color:#FFF; padding:5px 3px; box-shadow:0px 1px 3px rgba(34,25,25,0.4); margin-top:7px; border-radius:5px;}
.contenbox p{padding-top:2px; padding-bottom:2px; margin:0px!important;}
.contenbox h6{float:left; padding-left:5px;}
.contenboxspan{float:right; padding-right:10px; color:#CCC;}
</style>

</div>



</div>

        	</div>
</div>


<!-- PHOTO START  -->

<div id="container-page2" name="tags" class="tag2" style="display:none">

	<div class="gallery" id="gallery">
		<ul class="collections">
   <style>
   .clr{width:100%; clear:both; float:none!important}
   </style>
	<c:forEach items="${photos}" var="fileStore" varStatus="st">
                    <li><a href="${ctx}${fn:replace(fileStore.storePath, '\\', '/')}"><img src="${ctx}${fn:replace(fileStore.storePath, '\\', '/')}" alt=""></a>
			</li>
			<c:if test="${ (st.index > 0) && (st.index%3==2) }">
				<div class="clr"></div>
			</c:if>
	</c:forEach>
				</ul>
	</div> 
	
</div>



<!-- PHOTO END -->
<!-- MESSAGE START -->
<div id="container-page3" name="tags" class="tag3" style="display:none">


                          <div class="imgcontent">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <!-- /public/wedding/original/201409/03/10/5406785a4878a.JPG -->
                        <tr bgcolor="#FF3300" style="color:#FFF"><td width="50%" align="center"><img src="${ctx}${fn:replace(people1Photo.storePath, '\\', '/')}" width="50%"> </td>

                            <td width="50%"  align="center"> <img src="${ctx}${fn:replace(people2Photo.storePath, '\\', '/')}" width="50%"></td></tr>
                    </table>
                </div>

                <style>
                    .lxdh a{color:#FFF; font-size:18px;}
                </style>

                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr bgcolor="#FF3300" style="color:#FFF"><td width="50%" align="center"><div class="lxdh" >新郎<a href="tel:${card.people1Mobile}">${card.people1Mobile}</a>
                                </div></td><td width="50%"  align="center">  <div class="lxdh" >新娘<a href="tel:${card.people2Mobile}">${card.people2Mobile}</a>
                    </div></td></tr>
                </table>

                
            
            
	<div class="gallery">
 <form action="/create.php?ctl=create&act=ajaxmsgsave" name="com_form" class="ajax-form" method="post">
    	<table width="95%" border="0" cellspacing="0" cellpadding="0">
        	<tr><td width="27%">姓名</td><td width="73%"><input type="text" name="name" id="name" class="jiuba" /></td></tr>
            <!--<tr><td>是否参加</td><td><input type="radio" name="is_join" value="1" checked="checked" class="redinput" />是
            <input type="radio" name="is_join" value="0" class="redinput"/>否</td></tr>-->
            
            <tr><td>出席人数</td><td><select id="reply" name="reply_num" class="jiuba"> 
            <option value="无">无法出席</option>
            <option selected value="1">1人出席</option>
            <option value="2">2人出席</option>
            <option value="3">3人出席</option>
            <option value="4">4人出席</option>
            <option value="5">5人出席</option>
            <option value="6">6人出席</option>
            <option value="7">7人出席</option>
            <option value="8">8人出席</option>
            <option value="9">9人出席</option>
            <option value="10">10人出席</option>           
            </select></td></tr>
            <tr><td>默认祝福</td><td><select id="zhuf" onchange="show(this.value)" class="jiuba"> 
            <option value="新婚快乐，早生贵子!">新婚快乐，早生贵子!</option>
            <option value="敬祝百年好合永结同心！">敬祝百年好合永结同心！</option>
            <option value="天作之合，鸾凤和鸣。">天作之合，鸾凤和鸣。</option>
            <option value="天生才子佳人配，只羡鸳鸯不羡仙。">天生才子佳人配，只羡鸳鸯不羡仙。</option>
            </select></td></tr>
            <tr><td>内容</td><td><div class="topic_pub_form">
<textarea class="f-textarea pubform" name="content" id="con" style="width:; height:60px;"></textarea>
<div class="blank"></div>
<div class="f_l">
</div>
<div id="face_box_hd_con" >
<div class="blank1"></div>
<div class="emotion emotion_con wd" f="wd" >

	<span href="javascript:void(0);" title="爱情" rel="爱情"><img src="http://www.360hunjia.com/public/expression/wd/aiqing.png" /></span>
	<span href="javascript:void(0);" title="蛋糕" rel="蛋糕"><img src="http://www.360hunjia.com/public/expression/wd/dangao.png" /></span>
	<span href="javascript:void(0);" title="发财" rel="发财"><img src="http://www.360hunjia.com/public/expression/wd/facai.png" /></span>
	<span href="javascript:void(0);" title="给力" rel="给力"><img src="http://www.360hunjia.com/public/expression/wd/geili.gif" /></span>
	<span href="javascript:void(0);" title="恭喜" rel="恭喜"><img src="http://www.360hunjia.com/public/expression/wd/gongxi.png" /></span>
	<span href="javascript:void(0);" title="鼓掌" rel="鼓掌"><img src="http://www.360hunjia.com/public/expression/wd/guzhang.gif" /></span>
	<span href="javascript:void(0);" title="坏笑" rel="坏笑"><img src="http://www.360hunjia.com/public/expression/wd/huaixiao.gif" /></span>
	<span href="javascript:void(0);" title="婚纱" rel="婚纱"><img src="http://www.360hunjia.com/public/expression/wd/hunsha.png" /></span>
	<span href="javascript:void(0);" title="婚鞋" rel="婚鞋"><img src="http://www.360hunjia.com/public/expression/wd/hunxie.png" /></span>
	<span href="javascript:void(0);" title="脚印" rel="脚印"><img src="http://www.360hunjia.com/public/expression/wd/jiaoyin.png" /></span>
	<span href="javascript:void(0);" title="礼花" rel="礼花"><img src="http://www.360hunjia.com/public/expression/wd/lihua.png" /></span>
	<span href="javascript:void(0);" title="玫瑰" rel="玫瑰"><img src="http://www.360hunjia.com/public/expression/wd/meigui.png" /></span>
	<span href="javascript:void(0);" title="闪电" rel="闪电"><img src="http://www.360hunjia.com/public/expression/wd/shandian.png" /></span>
	<span href="javascript:void(0);" title="帅" rel="帅"><img src="http://www.360hunjia.com/public/expression/wd/shuai.png" /></span>
	<span href="javascript:void(0);" title="喜庆" rel="喜庆"><img src="http://www.360hunjia.com/public/expression/wd/xiqing.png" /></span>
	<span href="javascript:void(0);" title="携手" rel="携手"><img src="http://www.360hunjia.com/public/expression/wd/xieshou.gif" /></span>
	<span href="javascript:void(0);" title="心形" rel="心形"><img src="http://www.360hunjia.com/public/expression/wd/xinxing.png" /></span>
	<span href="javascript:void(0);" title="心意" rel="心意"><img src="http://www.360hunjia.com/public/expression/wd/xinyi.png" /></span>
	<span href="javascript:void(0);" title="信" rel="信"><img src="http://www.360hunjia.com/public/expression/wd/xin.gif" /></span>
	<span href="javascript:void(0);" title="钻戒" rel="钻戒"><img src="http://www.360hunjia.com/public/expression/wd/zuanjie.png" /></span>
	<span href="javascript:void(0);" title="棒棒糖" rel="棒棒糖"><img src="http://www.360hunjia.com/public/expression/wd/bangbangtang.png" /></span>
	<span href="javascript:void(0);" title="气球" rel="气球"><img src="http://www.360hunjia.com/public/expression/wd/qiqiu.png" /></span>
	<span href="javascript:void(0);" title="飞机" rel="飞机"><img src="http://www.360hunjia.com/public/expression/wd/feiji.png" /></span>
	<span href="javascript:void(0);" title="钞票" rel="钞票"><img src="http://www.360hunjia.com/public/expression/wd/chaopiao.png" /></span>
</div>
</div>
<div class="blank1"></div>
<script type="text/javascript">
	function valid_length()
		{
			var c = $("#con").val();
			if(c.length>5000)
			{
				$("#con").val(c.substr(0,5000));
			}
		}
	$(document).ready(function(){		
		init_con_form();
	});

function init_con_form()
{
	$("#con").bind("change keyup",function(){
			valid_length();
		});	

	

		$(".form_face_con").bind("click",function(){
			var obj = this;
			var face_html = $("#face_box_hd_con").html();
			var face_tab_html = $("#face_box_tab_con").html();
			$.weeboxs.open(
				face_html, 
				{
					boxid:'form_pop_box',
					contentType:'text',
					position:'element',
					trigger:obj,
					draggable:false,
					modal:false,
					showButton:false,
					title:face_tab_html,
					width:405
				});
			bind_con_set_expression();
		});
		

	
}	
	$(".emotion_con").find("span").bind("click",function(){
		var o = $(this);
		insert_con_cnt("["+$(o).attr("rel")+"]");	
	});
	
function toogle_mo(o)
{
	$(o).blur();
	$(o).parent().parent().parent().parent().parent().find(".emotion").hide();
	$(o).parent().parent().find("li").removeClass("c");
	$(o).parent().addClass("c");
	$(o).parent().parent().parent().parent().parent().find(".emotion[f='"+$(o).parent().attr("f")+"']").show();
}
function insert_con_cnt(cnt)
{
	var val = $("#con").val();
	$("#con").val(val+cnt);
}

</script>



</div></td></tr>
          
            <tr><td><input type="hidden" name="xitieid" value="31671" /><input type="hidden" name="action" value="message" /></td><td><input  class="jiuba" type="submit" value="发表祝福" /></td></tr>
        </table>
    </form>
    
    <style>
	.gallery table td{padding-top:5px; padding-bottom:3px;}
	.gallery{padding:5px;}
	.jiuba{width:90%;}
	.redinput{width:20px; height:20px; border:0px;}
	</style>
    
    <script>
		function show(v){
				document.getElementById("con").innerHTML = v;
		}
		
		function checkform(){
				if($("#name").val()==''){
					alert("请留下你的大名");	
					return false;
				}
		}
		function check_img(){
			if($("input[name=upfile]").val()==''){
				alert("请选择您要上传的图片!");	
				return false;
			}else{
			    if(!/\.(JPG|jpg|JPEG|jpeg)$/.test($("input[name=upfile]").val()))
			    {
			      alert("上传格式不正确,请上传JPG,JPEG格式图片");
			      return false;
			    }

				}
	}
	</script>
    <div id="mes_con">

  
   <div class="contenbox">
<h6>迈克</h6>
<span class="contenboxspan">2014-10-22 10:47</span>
<div class="clr"></div>
<p>敬祝百年好合永结同心！<img src='http://www.360hunjia.com/public/expression/wd/aiqing.png' alt='爱情' /><img src='http://www.360hunjia.com/public/expression/wd/aiqing.png' alt='爱情' /><img src='http://www.360hunjia.com/public/expression/wd/aiqing.png' alt='爱情' /><img src='http://www.360hunjia.com/public/expression/wd/aiqing.png' alt='爱情' /><img src='http://www.360hunjia.com/public/expression/wd/aiqing.png' alt='爱情' /></p>
<p style="text-align:right; color:#ccc"> 无法出席</p>
</div>
<div class="contenbox">
<h6>冯澜</h6>
<span class="contenboxspan">2014-09-28 12:28</span>
<div class="clr"></div>
<p>照片好美</p>
<p style="text-align:right; color:#ccc"> 1人出席</p>
</div>
<div class="contenbox">
<h6>朱国佳</h6>
<span class="contenboxspan">2014-09-12 14:27</span>
<div class="clr"></div>
<p><img src='http://www.360hunjia.com/public/expression/wd/xiqing.png' alt='喜庆' /><img src='http://www.360hunjia.com/public/expression/wd/xiqing.png' alt='喜庆' /><img src='http://www.360hunjia.com/public/expression/qq/guzhang.gif' alt='鼓掌' /><img src='http://www.360hunjia.com/public/expression/wd/facai.png' alt='发财' /><img src='http://www.360hunjia.com/public/expression/wd/chaopiao.png' alt='钞票' /></p>
<p style="text-align:right; color:#ccc"> 5人出席</p>
</div>
<div class="contenbox">
<h6>刘传安</h6>
<span class="contenboxspan">2014-09-03 17:32</span>
<div class="clr"></div>
<p><img src='http://www.360hunjia.com/public/expression/wd/xiqing.png' alt='喜庆' /><img src='http://www.360hunjia.com/public/expression/wd/xiqing.png' alt='喜庆' /></p>
<p style="text-align:right; color:#ccc"> 1人出席</p>
</div>
<div class="contenbox">
<h6>张培</h6>
<span class="contenboxspan">2014-09-03 13:10</span>
<div class="clr"></div>
<p>好棒噢！<img src='http://www.360hunjia.com/public/expression/qq/geili.gif' alt='给力' /><img src='http://www.360hunjia.com/public/expression/qq/guzhang.gif' alt='鼓掌' /><img src='http://www.360hunjia.com/public/expression/wd/aiqing.png' alt='爱情' /><img src='http://www.360hunjia.com/public/expression/wd/xinyi.png' alt='心意' /><img src='http://www.360hunjia.com/public/expression/wd/xiqing.png' alt='喜庆' /><img src='http://www.360hunjia.com/public/expression/wd/meigui.png' alt='玫瑰' /></p>
<p style="text-align:right; color:#ccc"> 2人出席</p>
</div>
<div class="contenbox">
<h6>朱奕倩</h6>
<span class="contenboxspan">2014-09-03 11:21</span>
<div class="clr"></div>
<p><img src='http://www.360hunjia.com/public/expression/qq/geili.gif' alt='给力' /><img src='http://www.360hunjia.com/public/expression/qq/geili.gif' alt='给力' /><img src='http://www.360hunjia.com/public/expression/qq/geili.gif' alt='给力' />这个不错哦</p>
<p style="text-align:right; color:#ccc"> 2人出席</p>
</div>
<div class="contenbox">
<h6>张斌</h6>
<span class="contenboxspan">2014-09-03 11:20</span>
<div class="clr"></div>
<p>敬祝百年好合永结同心！<img src='http://www.360hunjia.com/public/expression/wd/xieshou.gif' alt='携手' /><img src='http://www.360hunjia.com/public/expression/wd/xiqing.png' alt='喜庆' /><img src='http://www.360hunjia.com/public/expression/wd/aiqing.png' alt='爱情' /><img src='http://www.360hunjia.com/public/expression/wd/chaopiao.png' alt='钞票' /><img src='http://www.360hunjia.com/public/expression/wd/lihua.png' alt='礼花' /><img src='http://www.360hunjia.com/public/expression/wd/xin.gif' alt='信' /><img src='http://www.360hunjia.com/public/expression/qq/huaixiao.gif' alt='坏笑' /><img src='http://www.360hunjia.com/public/expression/qq/guzhang.gif' alt='鼓掌' /><img src='http://www.360hunjia.com/public/expression/qq/guzhang.gif' alt='鼓掌' /></p>
<p style="text-align:right; color:#ccc"> 1人出席</p>
</div>
<div class="contenbox">
<h6>徐亮</h6>
<span class="contenboxspan">2014-09-03 10:54</span>
<div class="clr"></div>
<p><img src='http://www.360hunjia.com/public/expression/qq/huaixiao.gif' alt='坏笑' />好美</p>
<p style="text-align:right; color:#ccc"> 3人出席</p>
</div>
<div class="contenbox">
<h6>任昱璇</h6>
<span class="contenboxspan">2014-09-03 10:51</span>
<div class="clr"></div>
<p><img src='http://www.360hunjia.com/public/expression/qq/guzhang.gif' alt='鼓掌' /><img src='http://www.360hunjia.com/public/expression/qq/guzhang.gif' alt='鼓掌' /><img src='http://www.360hunjia.com/public/expression/qq/guzhang.gif' alt='鼓掌' /></p>
<p style="text-align:right; color:#ccc"> 2人出席</p>
</div>
<div class="contenbox">
<h6>巫苏晓</h6>
<span class="contenboxspan">2014-09-03 10:48</span>
<div class="clr"></div>
<p></p>
<p style="text-align:right; color:#ccc"> 2人出席</p>
</div>
    </div>           
	</div>

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
					<a href="card/cover/${card.id}" class="link"><span></span></a>
				</p>
			</div>
		</div>


               <style>
		.footerp{color:#666; font-size:small; line-height:12px; padding-top:5px; text-align:center}
		.footerp a{color:#666}
		</style>
        
        <p class="footerp">
        Powered by <a href="http://www.360hunjia.com/" target="_blank">www.360hunjia.com</a>
        </p>
 <script type="text/javascript">
 
 var contentModel = {    
			"img_url": "<%=basePath %>${fn:replace(coverPhoto.storePath, '\\', '/')}",     
			"title": "${card.title} ",     
			"src": "诚挚邀请您共同分享幸福与喜悦" 
		};
 </script><script language="JavaScript" src="http://www.360hunjia.com/app/Tpl/fanwe/mob/js/share.js" type="text/javascript" ></script>
<span id="musicControl">
    <a id="mc_play" class="on" onclick="play_music();">
        <audio autoplay="autoplay" id="audio_play" loop>
                <source src="http://mp3.360hunjia.com/mob/music/m4.mp3" type="audio/mpeg">
                </audio>
    </a>

</span>
</body>
</html>
