<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer"%>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<%@page import="java.io.File" %>
<%@page import="onekr.framework.spring.property.CustomizedPropertyPlaceholderConfigurer" %>
<%@page import="onekr.commonservice.filestore.intf.FileBiz" %>
<%@page import="onekr.framework.utils.FileUtil" %>
<%@page import="org.apache.commons.io.FileUtils" %>
<h4>
上传位置：<%=new File(CustomizedPropertyPlaceholderConfigurer.getContextProperty("file.fileUploadDir") + FileBiz.fileUploadDirMore).getPath() %>
</h4>
<form class="form-horizontal" action="console/file/doUploadFile2" method="post" enctype="multipart/form-data" id="file-form">
	<div class="control-group">
		<label class="control-label" for="file">选择图片</label>
		<div class="controls">
			<input type="file" id="file" name="file" multiple value="选择文件" placeholder="file"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="uploadType">上传图片</label>
		<div class="controls">
			<input class="btn" type="submit" id="uploadButton" value="上传" />
		</div>
	</div>
</form>
<link rel="stylesheet" href="assets/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="assets/js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="assets/js/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="assets/js/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="assets/js/kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript">
    $('#file-form').validate({
    	rules: {
    		file: {
    			required: true
    		}
    	},
    	errorClass: "help-inline",
    	errorElement: "span",
    	highlight:function(element, errorClass, validClass) {
    		$(element).parents('.control-group').removeClass('success');
    		$(element).parents('.control-group').addClass('error');
    	},
    	unhighlight: function(element, errorClass, validClass) {
    		$(element).parents('.control-group').removeClass('error');
    		$(element).parents('.control-group').addClass('success');
    	}
    });
    </script>

