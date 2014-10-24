<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<%@page import="java.io.File" %>
<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer" %>
<%@page import="onekr.framework.utils.FileUtil" %>
<%@page import="org.apache.commons.io.FileUtils" %>
<% 
File file = (File) request.getAttribute("file");
String dir = (String) request.getAttribute("dir");
String pdir = (String) request.getAttribute("pdir");
String fileUploadDir = CustomizedPropertyPlaceholderConfigurer.getContextProperty("file.fileUploadDir");
String fileManagerUrl = CustomizedPropertyPlaceholderConfigurer.getContextProperty("file.fileManagerUrl");
%>
<div>
<h4>
<a 
<% 
if (dir.equals(new File(fileUploadDir).getPath())) { 
%>
	href="#" 
<% } else { %>
	href="/console/file/list?dir=<%=pdir%>" 
<% } %>
class="btn" title="上一级"><i class="icon-chevron-up"></i></a>
当前目录：<%=dir%> 
</h4>
</div>
<table class="table table-hover table-bordered table-striped">
	<tr>
		<th>名称</th>
		<th>大小</th>
		<th>内部数量</th>
		<th>操作(右键另存为可下载)</th>
	</tr>
	<% File[] files = file.listFiles(); 
	   for (File f : files) {
	%>
	<tr>
		<% if (f.isFile()) { %>
			<% if (FileUtil.isImage(f)) { %>
				<td><i class="icon-file"></i> <%=f.getName() %> </td>
			<% } else { %>
				<td><i class="icon-file"></i> <%=f.getName() %> </td>
			<% } %>
		<% } else { %>
			<td><a href="/console/file/list?dir=<%=(dir+File.separator+f.getName())%>"><i class="icon-folder-open"></i> <%=f.getName() %></a></td>
		<% } %>
		
		<% if (f.isDirectory()) { %>
			<td><%=FileUtil.formetFileSize(FileUtils.sizeOfDirectory(f)) %></td>
		<% } else { %>
			<td><%=FileUtil.formetFileSize(FileUtils.sizeOf(f)) %></td>
		<% } %>
		
		<% if (f.isDirectory()) { %>
			<td><%=FileUtil.getFolderCount(f) %></td>
		<% } else { %>
			<td>-</td>
		<% } %>
		
		<td>
		
		<% if (f.isFile()) { %>
			<% if (FileUtil.isImage(f)) { %>
				<a class="btn fancybox" rel="group1" href="<%=FileUtil.cvtUrl(f,fileUploadDir,fileManagerUrl)%>" title="<%=f.getName() %>"><i class="icon-eye-open"></i> 查看</a>
			<% } else { %>
				<a class="btn" href="<%=FileUtil.cvtUrl(f,fileUploadDir,fileManagerUrl)%>" target="_blank" title="下载 <%=f.getName() %>"><i class="icon-download-alt"></i> 下载</a>
			<% } %>
		<% } %>
		<button type="button" class="btn" onclick="ns.doDelete('<%=(dir+File.separator+f.getName()).replace("\\", "\\\\")%>');"><i class="icon-remove-sign"></i> 删除</button>
		
		</td>
	</tr>
	<% } %>
</table>

<script type="text/javascript" src="/assets/js/fancyBox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
<script type="text/javascript" src="/assets/js/fancyBox/source/jquery.fancybox.js?v=2.1.5"></script>
<link rel="stylesheet" type="text/css" href="/assets/js/fancyBox/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<link rel="stylesheet" type="text/css" href="/assets/js/fancyBox/source/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<script type="text/javascript" src="/assets/js/fancyBox/source/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
<link rel="stylesheet" type="text/css" href="/assets/js/fancyBox/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />
<script type="text/javascript" src="/assets/js/fancyBox/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
<script type="text/javascript" src="/assets/js/fancyBox/source/helpers/jquery.fancybox-media.js?v=1.0.6"></script>

<script>
var ns = ns || {};
ns.doDelete = function(dir) {
	if (!confirm('确定要删除 '+dir+' 吗？')) {
		return;
	}
	$.ajax({
		url : "/console/file/doDelete",
		type : 'post',
        dataType : 'json',
		data : {dir:dir},
		success : function(data) {
			alert(data.message);
			location.reload();
		}
	});
};

$(".fancybox").fancybox({

	prevEffect : 'elastic',
	nextEffect : 'elastic',

	openEffect : 'elastic',
	openSpeed  : 150,

	closeEffect : 'elastic',
	closeSpeed  : 150,
	
	closeBtn  : false,
	
	helpers: {
		title : {
			type : 'outside'
		},
		overlay : {
			speedOut : 0
		},
		thumbs : {
			width  : 50,
			height : 50
		},
		buttons	: {}
	},
	
	afterLoad : function() {
		this.title = '[ ' + (this.index + 1) + ' / ' + this.group.length + (this.title ? ' ] ' + this.title : '');
	}
});
</script>

