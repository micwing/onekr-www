<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.cardservice.model.Template" %>
<%@include file="../common/includes.jsp" %>
<!DOCTYPE html>
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一氪微信请柬</title>
<meta  name="description"  content="一氪软件工作室 电子请柬、电子喜帖、微信喜帖、婚纱电子相册制作平台">
<meta  name="apple-mobile-web-app-capable"  content="yes">
<meta  name="apple-mobile-web-app-status-bar-style"  content="black">
<meta  content="telephone=no"  name="format-detection">
<base href="<%=basePath%>" />
<script  type="text/javascript">
var phoneWidth =  parseInt(window.screen.width);
var phoneScale = phoneWidth/480;
var isAndroid = RegExp("Android").test(navigator.userAgent);
if (isAndroid) {
	if (phoneWidth>720){
		document.write('<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">');
	}else{
		document.write('<meta name="viewport" content="width=480,  minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
	}
} else {
	if (phoneWidth<420){
		document.write('<meta name="viewport" content="width=420, user-scalable=no, target-densitydpi=device-dpi">');
	}else{
		document.write('<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">');
	}
}
</script>
<style  type="text/css">
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockqueue,th,td{margin:0;padding:0}
table{border-collapse:collpase;border-spacing:0}
fieldset,img{border:0}
address,caption,cite,code,dfn,em,strong,th,var{font-style:normal;font-weight:normal}
ol,ul{list-style:none}
caption,th{text-align:left}
h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:normal}
q:before,q:after{content:''}
abbr,acroym{border:0}
*html{background-image:url(about:blank);background-attachment:fixed}
body{-webkit-text-size-adjust:none;width:100%}
*{font-family:"Microsoft YaHei"!important}
a{text-decoration:none;}
/*base*/
.cont_div{width:100%;margin:0 auto;overflow:hidden}
/*header*/       
.cont_div{margin:0 auto;max-width:480px;min-height:0;overflow:hidden;}
#header{padding-top:106px;background:url("assets/images/b_logo.jpg") center top no-repeat #C00000;}
#header h1{font:bold 30px/40px "微软雅黑";text-align:center;color:#FFFFFF}
#header p{font:bold 12px/18px "微软雅黑";text-align:center;color:#F7B314}
#container{margin:10px;}
#con_qhk {width:100%;overflow:hidden;height:35px;margin-top:15px;}
#qj_hl {display:block}
#qj_qy {display:none}
#qj_et {display:none}
#con_qhk p {width:46%;overflow:hidden;height:35px;background-color:#9C6E4C;margin-right:5px;float:left;text-align:center;line-height:35px;color:#fff; cursor:pointer;border-radius:5px}
#con_qhk p.qj_xz{background-color:#C00000;}
#con_qhk p.qj_xz a {color:#fff;}
#container .content {padding-bottom:10px;background-color:#fff;min-height:0;overflow:hidden;border-radius:5px;-webkit-border-radius:5px;padding-left:5%}
.content ul li{width:20%;float:left;margin-right:4%;min-height:0;overflow:hidden;border-radius:5px;-webkit-border-radius:5px;border:1px solid #D8D8D6;margin-top:20px;}
#container ul li .img_con{width:100%;overflow:hidden;}
#container ul li img{width:100%;}
#container ul li  a{display:block;font:bold 14px/25px "微软雅黑";text-align:center;color:#000000;}
#footer{background:#C00000;}
#footer p{height:35px;background-color:#820000;color:#fff}
#footer p a{font:400 12px/35px "微软雅黑";color:#fff;padding: 0 10px;}
#footer p a.top{float:right;}
#footer h1{font-size:12px;color:#fff;text-align:center;line-height:40px;}
#footer h1 a{color:#fff}
</style>
</head>
<body>
<div  class=""  style="display: none; position: absolute;">
	<div  class="aui_outer">
		<table  class="aui_border">
			<tbody>
				<tr>
					<td  class="aui_nw"></td>
					<td  class="aui_n"></td>
					<td  class="aui_ne"></td>
				</tr>
				<tr>
					<td  class="aui_w"></td>
					<td  class="aui_c">
						<div  class="aui_inner">
							<table  class="aui_dialog">
								<tbody>
									<tr>
										<td  colspan="2"  class="aui_header">
											<div  class="aui_titleBar">
												<div  class="aui_title"  style="cursor: move;"></div>
												<a  class="aui_close"  href="javascript:/*artDialog*/;">×</a>
											</div>
										</td>
									</tr>
									<tr>
										<td  class="aui_icon"  style="display: none;">
											<div  class="aui_iconBg"  style="background-image: none; background-position: initial initial; background-repeat: initial initial;"></div>
										</td>
										<td  class="aui_main"  style="width: auto; height: auto;">
											<div  class="aui_content"  style="padding: 20px 25px;"></div>
										</td>
									</tr>
									<tr>
										<td  colspan="2"  class="aui_footer">
											<div  class="aui_buttons"  style="display: none;"></div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
					<td  class="aui_e"></td>
				</tr>
				<tr>
					<td  class="aui_sw"></td>
					<td  class="aui_s"></td>
					<td  class="aui_se"  style="cursor: se-resize;"></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div  id="header"  class="cont_div">
    <h1>手机微喜帖</h1>
    <p>我的邀请如此与众不同</p>
</div>
<div  class="cont_div"  style="background: #C00000">
    <div  id="container">
		
		<div  class="content">

			<ul  id="qj_hl">
			<%
			for (Template t : Template.values()) {
				%>
				<li>
					<div  class="img_con"><a  href="card/cover/<%=t.getExampleOrderId()%>"  target="_blank"><img  src="assets/images/templatelist/<%=t.name()%>.jpg"></a></div>
					<a  href="card/cover/<%=t.getExampleOrderId()%>"  target="_blank"><%=t.getLabel().substring(7)%></a>
				</li>
				<%
			}
			%>
			</ul>
		</div>
    </div>
</div>
<div  id="footer"  class="cont_div">
    <p>
     <a  href="http://www.onekr.com/"  title="返回首页"  class="top">返回首页</a>
    </p>
</div>
<script>
(function(){
	function onBridgeReady() {
	        imgUrl = 'http://www.onekr.com/assets/images/logo.png',
	       	link = 'http://www.onekr.com/card/templatelist',
	        title = '一氪软件婚礼请柬模版',
	        desc = '支持微信、微博、QQ、短信发送。手机、平板、电脑都能查看！';
	
		if(typeof WeixinJSBridge !== 'undefined') {
	        // 发送给好友;
	        WeixinJSBridge.on('menu:share:appmessage', function(argv){
	                WeixinJSBridge.invoke('sendAppMessage',{
	                        'img_url' : imgUrl,
	                        'img_width' : '150',
	                        'img_height' : '150',
	                        'link' : link,
	                        'desc' : desc,
	                        'title' : title
                    }, function(res) {
                    	
                    });
	        });
			// 分享到朋友圈;
	        WeixinJSBridge.on('menu:share:timeline', function(argv){
	                WeixinJSBridge.invoke('shareTimeline',{
	                'img_url' : imgUrl,
	                'img_width' : '150',
	                'img_height' : '150',
	                'link' : link,
	                'desc' : desc,
	                'title' : title
	                }, function(res) {
	                	
					});
	        });
	        WeixinJSBridge.on('menu:share:weibo',
	        function(argv) {
		            WeixinJSBridge.invoke('shareWeibo', {
		                "content": desc + link,
		                "url": link
		            },
		            function(res) {
		                // do some thing
		                console.log(res.err_msg);
			    });
			});
		}
	};
	if(document.addEventListener){
		document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	} else if(document.attachEvent){
		document.attachEvent('WeixinJSBridgeReady' , onBridgeReady);
		document.attachEvent('onWeixinJSBridgeReady' , onBridgeReady);
	}
})();
</script>
<div  style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;"></div>
</body>
</html>