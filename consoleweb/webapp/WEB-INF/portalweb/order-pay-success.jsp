<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@include file="../common/includes.jsp" %>
<div class="row">
	<div class="span12">
		<div class="well">
			<h3 style="color: green">恭喜您，支付成功！</h3>
			<div class="well" style="background-color: #FFF;">
			    <form class="form-horizontal">
				    <div class="control-group">
					    <label class="control-label" for="">订单号</label>
					    <div class="controls">
						    <label class="checkbox">
						    ${order.id}
						    </label>
					    </div>
				    </div>
				    <div class="control-group">
					    <label class="control-label" for="">制作码</label>
					    <div class="controls">
						    <label class="checkbox">
						    <span style="color: red;font-size: 20px;">${order.remark}</span>
						    </label>
					    </div>
				    </div>
				    <div class="control-group">
					    <div class="controls">
					    	<label class="checkbox">
						    <a href="console/card/info/makecodeinput?makecode=${order.remark}" class="btn btn-primary btn-large">立刻去制作电子请柬</a>
						    </label>
					    </div>
				    </div>
			    </form>
			</div>
		</div>
		<div>
			
		</div>
	</div>
	
</div>
