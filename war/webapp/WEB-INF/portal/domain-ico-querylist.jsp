<%@page import="onekr.commonservice.utils.GlobalConstants"%>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.domain.dto.DomainDto"%>
<%@page import="java.util.*"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<%@include file="../common/includes.jsp"%>
<div class="row">
	<div class="span12" style="margin-bottom: 2px">
		<div class="row">
			<div class="span4" style="text-align: left;">
				<span id="info-total">0</span>个域名，已查询<span id="info-query">0</span>个，可注册<span
					id="info-available">0</span>个
			</div>
			<div class="span7" style="text-align: right;">
				<input type="checkbox" style="margin-bottom: 4px;"
					id="hideNotAvailable"> 仅显示可注册域名
			</div>
			<div class="span1" style="text-align: right;">
				<button class="btn btn-small btn-warning" id="domain-query-stop">暂停</button>
				<button class="btn btn-small btn-success" id="domain-query-continue"
					style="display: none;">继续</button>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="span12">

		<table id="domian-table"
			class="table table-striped table-hover table-condensed table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>域名</th>
					<th>长度</th>
					<th style="width: 260px">状态</th>
					<th style="width: 260px">推荐</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty domainDtoList}">
					<c:forEach items="${domainDtoList}" var="domainDto" varStatus="st">
						<tr index="${st.index+1}">
							<td>${st.index+1}</td>
							<td class="domain-td">${domainDto.name}.<span
								style="color: red">${domainDto.suffix}</span></td>
							<td>${fn:length(domainDto.name)}</td>
							<td class="result-td"></td>
							<td class="baidu-td"></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

	</div>

</div>
<script type="text/javascript">
                    var stopFlag = false;
                    var currentIndex = 1;
                    
                    $('#domain-query-stop').click(function() {
                    	stopFlag = true;
                    	$('#domain-query-stop').attr('disabled', true);
                    	$('#domain-query-continue').attr('disabled', true);
                    	$('#domain-query-stop').hide();
                    	$('#domain-query-continue').show();
                    });
					$('#domain-query-continue').click(function() {
						stopFlag = false;
						$('#domain-query-stop').attr('disabled', true);
                    	$('#domain-query-continue').attr('disabled', true);
                    	$('#domain-query-stop').show();
                    	$('#domain-query-continue').hide();
                    	queryDomain(currentIndex);
                    	queryDomain(currentIndex++);
                    	queryDomain(currentIndex++);
                    });
                    $('#hideNotAvailable').click(function() {
                    	var checked = $(this).attr('checked');
                    	if (checked) {
                    		hideNotAvailable();
                    	} else {
                    		$('#domian-table tbody tr').show(100);
                    	}
                    });
                    function hideNotAvailable() {
	                    $('#domian-table tbody tr td .label-important').each(function() {
	                		$(this).closest('tr').hide(100);
	                	});
                    }
                    
                    function queryDomain(index) {
                    	
                    	if (stopFlag) {
                    		return;
                    	}
                    	
                    	var $tr = $('#domian-table tbody tr[index='+index+']');
                    	if ($tr.size() == 0) {
                    		return;
                    	}
                    	
                    	if ($tr.find('.result-td').html()) {
                    		queryDomain(currentIndex++);
                    		return;
                    	}
                    	
                    	$tr.find('.result-td').html('<img src="/assets/images/loading.gif"/>');
                    	
                    	var domain = $tr.find('.domain-td').text();
                    	$.ajax({
                    		url : "/domain/domainAvailable",
                    		type : 'post',
                            dataType : 'json',
                    		data : {domain:domain},
                    		success : function(data) {
                    			if (data && data.value && data.value && data.value.available !=null) {
	                    			var isavailable = data.value.available;
	                    			var baidu = data.value.baiduSuggest;
	                    			var recommend = data.value.recommend;
                    				if (isavailable) {
	                    				$tr.find('.result-td').html('<span class="label label-success">未注册</span>');
	                    				if (baidu) {
		                    				$tr.find('.baidu-td').html('<img src="/assets/images/icon_b.gif"/> '+baidu
		                    						+(recommend?' <span class="label label-success">推荐</span>':' <span class="label label-warning">相似</span>'));
		                    				$('#info-available').text(parseInt($('#info-available').text())+1);
	                    				}
                    					
                    				} else {
	                    				$tr.find('.result-td').html('<span class="label label-important">已注册</span>&nbsp;&nbsp;<a href="/domain/whois?domain='+domain+'" target="_blank">查询whois</a>');
	                                	if ( $('#hideNotAvailable').attr('checked') ) {
	                                		$tr.hide();
	                                	}
                    				}
	                    			
                    			} else {
                    				$tr.find('.result-td').html('<span class="label label-warning">查询失败</span>&nbsp;&nbsp;<a href="/domain/whois?domain='+domain+'" target="_blank">查询whois</a>');
                    			}
                    			$('#info-query').text(parseInt($('#info-query').text())+1);
                    			$('#domain-query-stop').attr('disabled', false);
                            	$('#domain-query-continue').attr('disabled', false);
                    			queryDomain(currentIndex);
                    		}
                    	});
                    }
                    $(function() {
                    	$('#info-total').text($('#domian-table tbody tr').size());
                    	queryDomain(1);
                    	queryDomain(2);
                    	queryDomain(3);
                    });
                    </script>