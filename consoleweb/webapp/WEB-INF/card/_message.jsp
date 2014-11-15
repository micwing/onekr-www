<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp"%>
<c:if test="${!empty result}">
<div class="alert ${result.code == 0 ? 'alert-success' : 'alert-error'}">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    ${result.message}
</div>
</c:if>
