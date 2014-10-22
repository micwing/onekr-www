<%@page import="onekr.portalservice.utils.GlobalConstants"%>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.domain.dto.DomainDto" %>
<%@page import="java.util.*" %>
<%@page import="org.springframework.util.CollectionUtils" %>
<%@include file="../common/includes.jsp" %>
<%
String[] allSuffix = GlobalConstants.DOMAIN_ALL_SUFFIX;
List<String> suffix = (List<String>) request.getAttribute("suffix");
%>
<style>
.checkbox.inline + .checkbox.inline {
	margin-left: 0px;
}
</style>
            <header>
                <div class="container">
                    <h2>过期域名查询</h2>
                </div>
            </header>
            <section style="min-height:  600px">
                <div class="container">
                	<div class="row">
                		<div class="span12">
	            		<form class="form-horizontal" action="/domain/expired" method="get" id="expired-form">
	            			<div class="control-group">
							    <label class="control-label" for="">关键字</label>
							    <div class="controls">
							    	<input type="text" name="key" value="${key}" />
							    </div>
						    </div>
						    <div class="control-group">
							    <label class="control-label" for="">位置</label>
							    <div class="controls">
							    	<label class="radio inline">
										<input type="radio" name="keypos" value="0" ${keyPos==0?'checked':''}/> 任意
									</label>
									<label class="radio inline">
										<input type="radio" name="keypos" value="1" ${keyPos==1?'checked':''}/> 左
									</label>
									<label class="radio inline">
										<input type="radio" name="keypos" value="2" ${keyPos==2?'checked':''}/> 右
									</label>
									<label class="radio inline">
										<input type="radio" name="keypos" value="3" ${keyPos==3?'checked':''}/> 左或右
									</label>
							    </div>
						    </div>
						    <div class="control-group">
							    <label class="control-label" for="">总长度</label>
							    <div class="controls">
							    	<input type="text" class="input-mini" name="minlength" value="${minlength}" /> - <input type="text" class="input-mini" name="maxlength" value="${maxlength}" />
							    </div>
						    </div>
						    <div class="control-group">
							    <label class="control-label" for="">域名结构</label>
							    <div class="controls">
							    	<label class="checkbox inline">
										<input type="checkbox" name="texttype" value="1" ${fn:contains(textType, 1) ? 'checked':''}/> 字母
									</label>
									<label class="checkbox inline">
										<input type="checkbox" name="texttype" value="2" ${fn:contains(textType, 2) ? 'checked':''}/> 数字
									</label>
									<label class="checkbox inline">
										<input type="checkbox" name="texttype" value="3" ${fn:contains(textType, 3) ? 'checked':''}/> 中划线
									</label>
							    </div>
						    </div>
						    <div class="control-group">
							    <label class="control-label" for="">拼音结构</label>
							    <div class="controls">
							    	<select name="pinyintype">
							    		<option value=""></option>
							    		<option value="9" ${pinyinType==9?'selected':''}>全部拼音</option>
							    		<option value="1" ${pinyinType==1?'selected':''}>单拼音</option>
							    		<option value="2" ${pinyinType==2?'selected':''}>双拼音</option>
							    		<option value="3" ${pinyinType==3?'selected':''}>三拼音</option>
							    		<option value="4" ${pinyinType==4?'selected':''}>四拼音</option>
							    	</select>
							    </div>
						    </div>
						    <div class="control-group">
							    <label class="control-label" for="">域名后缀</label>
							    <div class="controls">
							    	<%for (String suf : allSuffix) {%>
									<label class="checkbox inline">
										<input name="suffix" type="checkbox" value="<%=suf%>" id="<%=suf%>suffix" <%=suffix.contains(suf)?"checked":"" %>/> <%=suf%>
									</label>
                       				<%}%>
                       				<label class="checkbox inline">
										<a href="javascript:;" onclick="$(this).closest('.controls').find('input:checkbox').attr('checked',true)">全选</a>/
										<a href="javascript:;" onclick="$(this).closest('.controls').find('input:checkbox').attr('checked',false)">全不选</a>
									</label>
							    </div>
						    </div>
						    <div class="control-group">
							    <label class="control-label" for="">删除日期</label>
							    <div class="controls">
								    <select name="date">
			           					<c:forEach items="${dates}" var="datei">
				           					<option value="${datei}" ${datei==date?'selected':''}>${datei}</option>
			           					</c:forEach>
			           				</select>
							    </div>
						    </div>
						    <div class="control-group">
							    <div class="controls">
								    <button type="submit" class="btn btn-primary" id="suffix-query">查询</button>
							    </div>
						    </div>
						    
					    </form>
					    </div>
                	</div>
	                
	                <hr>
	                <jsp:include page="domain-ico-querylist.jsp"></jsp:include>
					
                </div>
            </section>

