<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@include file="../common/includes.jsp" %>
<div class="row">
	<div class="span12">
		<div class="well">
		正在提交...
	<%
		////////////////////////////////////请求参数//////////////////////////////////////
		String service = (String) request.getAttribute("service");
		String partner = (String) request.getAttribute("partner");
		String _input_charset = (String) request.getAttribute("_input_charset");
		
		//支付类型
		String payment_type = (String) request.getAttribute("payment_type");
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = (String) request.getAttribute("notify_url");
		//需http://格式的完整路径，不能加?id=123这类自定义参数		//页面跳转同步通知页面路径
		String return_url = (String) request.getAttribute("return_url");
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/		//卖家支付宝帐户
		String seller_email = (String) request.getAttribute("WIDseller_email");
		//必填		//商户订单号
		String out_trade_no = (String) request.getAttribute("WIDout_trade_no");
		//商户网站订单系统中唯一订单号，必填		//订单名称
		String subject = (String) request.getAttribute("WIDsubject");
		//必填		//付款金额
		String total_fee = (String) request.getAttribute("WIDtotal_fee");
		//必填		//订单描述		
		String body = (String) request.getAttribute("WIDbody");
		//商品展示地址
		String show_url = (String) request.getAttribute("WIDshow_url");
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html		//防钓鱼时间戳
		String anti_phishing_key = (String) request.getAttribute("anti_phishing_key");
		//若要使用请调用类文件submit中的query_timestamp函数		//客户端的IP地址
		String exter_invoke_ip = (String) request.getAttribute("exter_invoke_ip");
		//非局域网的外网IP地址，如：221.0.0.1
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", service);
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", _input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"post","确认");
		out.println(sHtmlText);
	%>
		</div>
		<div>
			
		</div>
	</div>
	
</div>
