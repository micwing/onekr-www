<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp"%>
<style>
.contenbox {
	background-color: #FFF;
	padding: 5px 3px;
	box-shadow: 0px 1px 3px rgba(34, 25, 25, 0.4);
	margin-top: 7px;
	border-radius: 5px;
}

.contenbox p {
	padding-top: 2px;
	padding-bottom: 2px;
	margin: 0px !important;
}

.contenbox h6 {
	float: left;
	padding-left: 5px;
}

.contenboxspan {
	float: right;
	padding-right: 10px;
	color: #CCC;
}
</style>
<div class="imgcontent">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<!-- /public/wedding/original/201409/03/10/5406785a4878a.JPG -->
		<tr bgcolor="#FF3300" style="color: #FFF">
			<td width="50%" align="center"><img
				src="attached${fn:replace(people1Photo.photo.storePath, '\\', '/')}"
				width="50%"></td>

			<td width="50%" align="center"><img
				src="attached${fn:replace(people2Photo.photo.storePath, '\\', '/')}"
				width="50%"></td>
		</tr>
	</table>
</div>

<style>
.lxdh a {
	color: #FFF;
	font-size: 18px;
}
</style>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr bgcolor="#FF3300" style="color: #FFF">
		<td width="50%" align="center"><div class="lxdh">
				新郎<a href="tel:${card.people1Mobile}">${card.people1Mobile}</a>
			</div></td>
		<td width="50%" align="center">
			<div class="lxdh">
				新娘<a href="tel:${card.people2Mobile}">${card.people2Mobile}</a>
			</div>
		</td>
	</tr>
</table>




<div class="gallery">
	<form action="/create.php?ctl=create&act=ajaxmsgsave" name="com_form"
		class="ajax-form" method="post">
		<table width="95%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="27%">姓名</td>
				<td width="73%"><input type="text" name="name" id="name"
					class="jiuba" /></td>
			</tr>
			<!--<tr><td>是否参加</td><td><input type="radio" name="is_join" value="1" checked="checked" class="redinput" />是
            <input type="radio" name="is_join" value="0" class="redinput"/>否</td></tr>-->

			<tr>
				<td>出席人数</td>
				<td><select id="reply" name="reply_num" class="jiuba">
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
				</select></td>
			</tr>
			<tr>
				<td>默认祝福</td>
				<td><select id="zhuf" onchange="show(this.value)" class="jiuba">
						<option value="新婚快乐，早生贵子!">新婚快乐，早生贵子!</option>
						<option value="敬祝百年好合永结同心！">敬祝百年好合永结同心！</option>
						<option value="天作之合，鸾凤和鸣。">天作之合，鸾凤和鸣。</option>
						<option value="天生才子佳人配，只羡鸳鸯不羡仙。">天生才子佳人配，只羡鸳鸯不羡仙。</option>
				</select></td>
			</tr>
			<tr>
				<td>内容</td>
				<td><div class="topic_pub_form">
						<textarea class="f-textarea pubform" name="content" id="con"
							style="width:; height: 60px;"></textarea>
						<div class="blank"></div>
						<div class="f_l"></div>
						<div id="face_box_hd_con">
							<div class="blank1"></div>
							<div class="emotion emotion_con wd" f="wd">

								<span href="javascript:void(0);" title="爱情" rel="爱情"><img
									src="http://www.360hunjia.com/public/expression/wd/aiqing.png" /></span>
								<span href="javascript:void(0);" title="蛋糕" rel="蛋糕"><img
									src="http://www.360hunjia.com/public/expression/wd/dangao.png" /></span>
								<span href="javascript:void(0);" title="发财" rel="发财"><img
									src="http://www.360hunjia.com/public/expression/wd/facai.png" /></span>
								<span href="javascript:void(0);" title="给力" rel="给力"><img
									src="http://www.360hunjia.com/public/expression/wd/geili.gif" /></span>
								<span href="javascript:void(0);" title="恭喜" rel="恭喜"><img
									src="http://www.360hunjia.com/public/expression/wd/gongxi.png" /></span>
								<span href="javascript:void(0);" title="鼓掌" rel="鼓掌"><img
									src="http://www.360hunjia.com/public/expression/wd/guzhang.gif" /></span>
								<span href="javascript:void(0);" title="坏笑" rel="坏笑"><img
									src="http://www.360hunjia.com/public/expression/wd/huaixiao.gif" /></span>
								<span href="javascript:void(0);" title="婚纱" rel="婚纱"><img
									src="http://www.360hunjia.com/public/expression/wd/hunsha.png" /></span>
								<span href="javascript:void(0);" title="婚鞋" rel="婚鞋"><img
									src="http://www.360hunjia.com/public/expression/wd/hunxie.png" /></span>
								<span href="javascript:void(0);" title="脚印" rel="脚印"><img
									src="http://www.360hunjia.com/public/expression/wd/jiaoyin.png" /></span>
								<span href="javascript:void(0);" title="礼花" rel="礼花"><img
									src="http://www.360hunjia.com/public/expression/wd/lihua.png" /></span>
								<span href="javascript:void(0);" title="玫瑰" rel="玫瑰"><img
									src="http://www.360hunjia.com/public/expression/wd/meigui.png" /></span>
								<span href="javascript:void(0);" title="闪电" rel="闪电"><img
									src="http://www.360hunjia.com/public/expression/wd/shandian.png" /></span>
								<span href="javascript:void(0);" title="帅" rel="帅"><img
									src="http://www.360hunjia.com/public/expression/wd/shuai.png" /></span>
								<span href="javascript:void(0);" title="喜庆" rel="喜庆"><img
									src="http://www.360hunjia.com/public/expression/wd/xiqing.png" /></span>
								<span href="javascript:void(0);" title="携手" rel="携手"><img
									src="http://www.360hunjia.com/public/expression/wd/xieshou.gif" /></span>
								<span href="javascript:void(0);" title="心形" rel="心形"><img
									src="http://www.360hunjia.com/public/expression/wd/xinxing.png" /></span>
								<span href="javascript:void(0);" title="心意" rel="心意"><img
									src="http://www.360hunjia.com/public/expression/wd/xinyi.png" /></span>
								<span href="javascript:void(0);" title="信" rel="信"><img
									src="http://www.360hunjia.com/public/expression/wd/xin.gif" /></span>
								<span href="javascript:void(0);" title="钻戒" rel="钻戒"><img
									src="http://www.360hunjia.com/public/expression/wd/zuanjie.png" /></span>
								<span href="javascript:void(0);" title="棒棒糖" rel="棒棒糖"><img
									src="http://www.360hunjia.com/public/expression/wd/bangbangtang.png" /></span>
								<span href="javascript:void(0);" title="气球" rel="气球"><img
									src="http://www.360hunjia.com/public/expression/wd/qiqiu.png" /></span>
								<span href="javascript:void(0);" title="飞机" rel="飞机"><img
									src="http://www.360hunjia.com/public/expression/wd/feiji.png" /></span>
								<span href="javascript:void(0);" title="钞票" rel="钞票"><img
									src="http://www.360hunjia.com/public/expression/wd/chaopiao.png" /></span>
							</div>
						</div>
						<div class="blank1"></div>
						<script type="text/javascript">
							function valid_length() {
								var c = $("#con").val();
								if (c.length > 5000) {
									$("#con").val(c.substr(0, 5000));
								}
							}
							$(document).ready(function() {
								init_con_form();
							});

							function init_con_form() {
								$("#con").bind("change keyup", function() {
									valid_length();
								});

								$(".form_face_con")
										.bind(
												"click",
												function() {
													var obj = this;
													var face_html = $(
															"#face_box_hd_con")
															.html();
													var face_tab_html = $(
															"#face_box_tab_con")
															.html();
													$.weeboxs.open(face_html, {
														boxid : 'form_pop_box',
														contentType : 'text',
														position : 'element',
														trigger : obj,
														draggable : false,
														modal : false,
														showButton : false,
														title : face_tab_html,
														width : 405
													});
													bind_con_set_expression();
												});

							}
							$(".emotion_con").find("span").bind(
									"click",
									function() {
										var o = $(this);
										insert_con_cnt("[" + $(o).attr("rel")
												+ "]");
									});

							function toogle_mo(o) {
								$(o).blur();
								$(o).parent().parent().parent().parent()
										.parent().find(".emotion").hide();
								$(o).parent().parent().find("li").removeClass(
										"c");
								$(o).parent().addClass("c");
								$(o).parent().parent().parent().parent()
										.parent().find(
												".emotion[f='"
														+ $(o).parent().attr(
																"f") + "']")
										.show();
							}
							function insert_con_cnt(cnt) {
								var val = $("#con").val();
								$("#con").val(val + cnt);
							}
						</script>



					</div></td>
			</tr>

			<tr>
				<td><input type="hidden" name="xitieid" value="31671" /><input
					type="hidden" name="action" value="message" /></td>
				<td><input class="jiuba" type="submit" value="发表祝福" /></td>
			</tr>
		</table>
	</form>

	<style>
.gallery table td {
	padding-top: 5px;
	padding-bottom: 3px;
}

.gallery {
	padding: 5px;
}

.jiuba {
	width: 90%;
}

.redinput {
	width: 20px;
	height: 20px;
	border: 0px;
}
</style>

	<script>
		function show(v) {
			document.getElementById("con").innerHTML = v;
		}

		function checkform() {
			if ($("#name").val() == '') {
				alert("请留下你的大名");
				return false;
			}
		}
	</script>
	<div id="mes_con">


		<div class="contenbox">
			<h6>迈克</h6>
			<span class="contenboxspan">2014-10-22 10:47</span>
			<div class="clr"></div>
			<p>
				敬祝百年好合永结同心！<img
					src='http://www.360hunjia.com/public/expression/wd/aiqing.png'
					alt='爱情' /><img
					src='http://www.360hunjia.com/public/expression/wd/aiqing.png'
					alt='爱情' /><img
					src='http://www.360hunjia.com/public/expression/wd/aiqing.png'
					alt='爱情' /><img
					src='http://www.360hunjia.com/public/expression/wd/aiqing.png'
					alt='爱情' /><img
					src='http://www.360hunjia.com/public/expression/wd/aiqing.png'
					alt='爱情' />
			</p>
			<p style="text-align: right; color: #ccc">无法出席</p>
		</div>
		<div class="contenbox">
			<h6>冯澜</h6>
			<span class="contenboxspan">2014-09-28 12:28</span>
			<div class="clr"></div>
			<p>照片好美</p>
			<p style="text-align: right; color: #ccc">1人出席</p>
		</div>
		<div class="contenbox">
			<h6>朱国佳</h6>
			<span class="contenboxspan">2014-09-12 14:27</span>
			<div class="clr"></div>
			<p>
				<img src='http://www.360hunjia.com/public/expression/wd/xiqing.png'
					alt='喜庆' /><img
					src='http://www.360hunjia.com/public/expression/wd/xiqing.png'
					alt='喜庆' /><img
					src='http://www.360hunjia.com/public/expression/qq/guzhang.gif'
					alt='鼓掌' /><img
					src='http://www.360hunjia.com/public/expression/wd/facai.png'
					alt='发财' /><img
					src='http://www.360hunjia.com/public/expression/wd/chaopiao.png'
					alt='钞票' />
			</p>
			<p style="text-align: right; color: #ccc">5人出席</p>
		</div>
		<div class="contenbox">
			<h6>刘传安</h6>
			<span class="contenboxspan">2014-09-03 17:32</span>
			<div class="clr"></div>
			<p>
				<img src='http://www.360hunjia.com/public/expression/wd/xiqing.png'
					alt='喜庆' /><img
					src='http://www.360hunjia.com/public/expression/wd/xiqing.png'
					alt='喜庆' />
			</p>
			<p style="text-align: right; color: #ccc">1人出席</p>
		</div>
		<div class="contenbox">
			<h6>张培</h6>
			<span class="contenboxspan">2014-09-03 13:10</span>
			<div class="clr"></div>
			<p>
				好棒噢！<img
					src='http://www.360hunjia.com/public/expression/qq/geili.gif'
					alt='给力' /><img
					src='http://www.360hunjia.com/public/expression/qq/guzhang.gif'
					alt='鼓掌' /><img
					src='http://www.360hunjia.com/public/expression/wd/aiqing.png'
					alt='爱情' /><img
					src='http://www.360hunjia.com/public/expression/wd/xinyi.png'
					alt='心意' /><img
					src='http://www.360hunjia.com/public/expression/wd/xiqing.png'
					alt='喜庆' /><img
					src='http://www.360hunjia.com/public/expression/wd/meigui.png'
					alt='玫瑰' />
			</p>
			<p style="text-align: right; color: #ccc">2人出席</p>
		</div>
		<div class="contenbox">
			<h6>朱奕倩</h6>
			<span class="contenboxspan">2014-09-03 11:21</span>
			<div class="clr"></div>
			<p>
				<img src='http://www.360hunjia.com/public/expression/qq/geili.gif'
					alt='给力' /><img
					src='http://www.360hunjia.com/public/expression/qq/geili.gif'
					alt='给力' /><img
					src='http://www.360hunjia.com/public/expression/qq/geili.gif'
					alt='给力' />这个不错哦
			</p>
			<p style="text-align: right; color: #ccc">2人出席</p>
		</div>
		<div class="contenbox">
			<h6>张斌</h6>
			<span class="contenboxspan">2014-09-03 11:20</span>
			<div class="clr"></div>
			<p>
				敬祝百年好合永结同心！<img
					src='http://www.360hunjia.com/public/expression/wd/xieshou.gif'
					alt='携手' /><img
					src='http://www.360hunjia.com/public/expression/wd/xiqing.png'
					alt='喜庆' /><img
					src='http://www.360hunjia.com/public/expression/wd/aiqing.png'
					alt='爱情' /><img
					src='http://www.360hunjia.com/public/expression/wd/chaopiao.png'
					alt='钞票' /><img
					src='http://www.360hunjia.com/public/expression/wd/lihua.png'
					alt='礼花' /><img
					src='http://www.360hunjia.com/public/expression/wd/xin.gif' alt='信' /><img
					src='http://www.360hunjia.com/public/expression/qq/huaixiao.gif'
					alt='坏笑' /><img
					src='http://www.360hunjia.com/public/expression/qq/guzhang.gif'
					alt='鼓掌' /><img
					src='http://www.360hunjia.com/public/expression/qq/guzhang.gif'
					alt='鼓掌' />
			</p>
			<p style="text-align: right; color: #ccc">1人出席</p>
		</div>
		<div class="contenbox">
			<h6>徐亮</h6>
			<span class="contenboxspan">2014-09-03 10:54</span>
			<div class="clr"></div>
			<p>
				<img
					src='http://www.360hunjia.com/public/expression/qq/huaixiao.gif'
					alt='坏笑' />好美
			</p>
			<p style="text-align: right; color: #ccc">3人出席</p>
		</div>
		<div class="contenbox">
			<h6>任昱璇</h6>
			<span class="contenboxspan">2014-09-03 10:51</span>
			<div class="clr"></div>
			<p>
				<img src='http://www.360hunjia.com/public/expression/qq/guzhang.gif'
					alt='鼓掌' /><img
					src='http://www.360hunjia.com/public/expression/qq/guzhang.gif'
					alt='鼓掌' /><img
					src='http://www.360hunjia.com/public/expression/qq/guzhang.gif'
					alt='鼓掌' />
			</p>
			<p style="text-align: right; color: #ccc">2人出席</p>
		</div>
		<div class="contenbox">
			<h6>巫苏晓</h6>
			<span class="contenboxspan">2014-09-03 10:48</span>
			<div class="clr"></div>
			<p></p>
			<p style="text-align: right; color: #ccc">2人出席</p>
		</div>
	</div>
</div>
