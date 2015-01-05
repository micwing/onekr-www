<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../../common/includes.jsp" %>
<div style="width:100%;color:#666; font-size:small; line-height:12px; padding-top:5px; text-align: center;">
Power by <a href="http://www.onekr.com" style="color:#666;text-decoration:none">www.onekr.com</a>
</div>
<script type="text/javascript">
var contentModel = {    
	"img_url": "<%=basePath %>attached${fn:replace(coverPhoto.thumb.storePath, '\\', '/')}",     
	"title": "${card.title}",     
	"src": "诚挚邀请您共同分享幸福与喜悦", 
	"link": "<%=basePath %>card/cover/${card.id}"
};
</script>
<script type="text/javascript" src="assets/js/share.js"  ></script> 