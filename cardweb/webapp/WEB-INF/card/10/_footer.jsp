<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<div style="width:100%;color:#666; font-size:small; line-height:12px; padding-top:5px; text-align: center;">
<a href="http://onekr.taobao.com" target="_blank" style="color:#666">http://onekr.taobao.com</a>
</div>
<script type="text/javascript">
var contentModel = {    
	"img_url": "<%=basePath %>${fn:replace(coverPhoto.storePath, '\\', '/')}",     
	"title": "${card.title} ",     
	"src": "诚挚邀请您共同分享幸福与喜悦" 
};
</script>

<!-- <script type="text/javascript" src="assets/js/share.js"  ></script> -->