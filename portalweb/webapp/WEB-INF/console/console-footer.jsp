<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@page import="onekr.framework.utils.DateUtil"%>
<%@include file="../common/includes.jsp" %>
<footer>
  <p>&copy; ONEKR.com 2013 - <%= DateUtil.getYear(new Date()) %></p>
</footer>