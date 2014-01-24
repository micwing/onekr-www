<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="common/includes.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>控制台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="/assets/css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="/assets/js/html5shiv.js"></script>
    <![endif]-->
    
    <script src="/assets/js/jquery.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/jquery.json.js"></script>
    
    <script src="/assets/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="/assets/js/validate/more_rules.js" type="text/javascript"></script>
	<script src="/assets/js/validate/message_cn.js" type="text/javascript"></script>
	
	<script src="/assets/js/console.customer.js"></script>

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="/assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="/assets/ico/favicon.png">
  </head>

  <body>

	<tiles:insertAttribute name="topbar"/>

    <div class="container">
      <div class="row">
        <div class="span3">
        	<tiles:insertAttribute name="navbar"/>
          
        </div><!--/span-->
        
        <div class="span9">
        	<div class="well" style="background-color: transparent;">
				<tiles:insertAttribute name="content"/>
        	</div>
        </div><!--/span-->
      </div><!--/row-->

      <hr>

	  <tiles:insertAttribute name="footer"/>

    </div><!--/.fluid-container-->

  </body>
</html>





