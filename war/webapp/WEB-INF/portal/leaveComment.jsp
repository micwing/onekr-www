<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="onekr.commonservice.utils.GlobalConstants" %>
<%@include file="../common/includes.jsp" %>
        <div id="wrap">

            <!--################ HEADER START ################-->

            <header class="page-title">
                <div class="container">
                    <h2>留言</h2>
                </div>
            </header>

            <section class="single-blog comments-section">

                <div class="container">
                    <div class="row">
                        <div class="span8">
                            <h2>大家都说啥</h2>
                        </div>
                        <div class="span4"></div>
                    </div>
                </div>

            </section>
            
            <section class="single-blog rev last">

                <div class="container">
                    <div class="row">
                        <div class="span9">

                            <div class="comments" style="box-shadow:0 0 0 0">
                            
                            <c:forEach items="${comments}" var="comment" varStatus="st">
                            	<div class="comment">
                                    <div class="row">
                                        <div class="span2">
                                            <img src="/assets/images/default-user.jpg" class="img-circle author_pic">

                                            <div class="btn-toolbar">
                                                <div class="btn-group margintop20" style="margin-left: 20px;">
                                                    <a href="javascript:;" onclick="ns.doAddGoodBad(${comment.id},true,this)"  class="btn btn-comments"><span class="comment-good-bad">${comment.good}</span> <i class="icon-white icon-thumbs-up"></i></a>
                                                    <a href="javascript:;" onclick="ns.doAddGoodBad(${comment.id},false,this)" class="btn btn-comments"><span class="comment-good-bad">${comment.bad}</span> <i class="icon-white icon-thumbs-down"></i></a>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="span7">
                                            <div class="date">
                                                <fmt:formatDate value="${comment.createAt}" type="both"/>
                                            </div>
                                            <div class="name">
                                                ${comment.userName}:

                                            </div>
                                            <div class="response">
                                                ${fn:replace(
                                                	fn:replace(
	                                                	fn:replace(
		                                                	fn:replace(
			                                                	comment.content,
			                                                	'&',
			                                                	'&amp;'
			                                                ),
		                                                	'<',
		                                                	'&lt;'
		                                                ),
	                                                	'>',
	                                                	'&gt;'
	                                                ),
                                                	'"',
                                                	'&quot;'
                                                )}
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${!empty comment.subComment}">
	                                    <div class="row">
	                                        <div class="span1">
	                                        </div>
	
	                                        <div class="span8" style="background-color: #F5F5F5;border-radius: 4px;width: 610px;margin-bottom: 10px;">
	                                        	<div class="span2">
		                                        	<img src="/assets/images/admin.jpg" class="img-circle author_pic">
	                                       		</div>
												<div class="span5">
		                                            <div class="date">
		                                                <fmt:formatDate value="${comment.subComment.createAt}" type="both"/>
		                                            </div>
		                                            <div class="name">
		                                                ${comment.subComment.userName} 回复 ${comment.userName}:
		                                            </div>
		                                            <div class="response">
		                                                ${fn:replace(
                                                	fn:replace(
	                                                	fn:replace(
		                                                	fn:replace(
			                                                	comment.subComment.content,
			                                                	'&',
			                                                	'&amp;'
			                                                ),
		                                                	'<',
		                                                	'&lt;'
		                                                ),
	                                                	'>',
	                                                	'&gt;'
	                                                ),
                                                	'"',
                                                	'&quot;'
                                                )}
		                                            </div>
												</div>
	                                        </div>
	                                    </div>
                                    </c:if>
                                </div>
                            </c:forEach>

                                
                            </div>

                        </div>
                        <div class="span4"></div>
                    </div>
                </div>
            </section>
            
            <section class="single-blog comments-section">

                <div class="container">
                    <div class="row">
                        <div class="span8">
                            <h2>给我留言</h2>
                            <br />
                            <form class="form-comments" _lpchecked="1"  id="comment-form">

                                <fieldset>

                                    <div class="row">
                                        <div class="span3">
                                            <label for="senderName"><span>名称:</span></label>
                                            <div class="input">
                                                <input id="senderName" name="senderName" type="text" value="" class="input-xlarge">
                                                <input id="avatar" name="avatar" type="hidden" value="" >

                                            </div>
                                        </div>
                                        <div class="span1"> 
                                        </div>
                                        <div class="span4">

                                            <label for="senderEmail"><span>Email:</span></label>
                                            <div class="input">
                                                <input id="senderEmail" name="senderEmail" type="text" value="" class="input-xlarge">

                                            </div>
                                        </div>
                                    </div>

                                    <div class="clearfix">
                                        <label for="content"><span>内容:</span></label>
                                        <div class="input">
                                            <textarea tabindex="3" class="input-xlarge" id="content" name="content" rows="7"></textarea>
                                        </div>
                                    </div>

                                    <div class="actions">
                                        <button tabindex="3" type="button" class="btn btn-landing " id="save">提交</button>
                                    </div>
                                </fieldset>

                            </form>
                            <script type="text/javascript">
                            var ns = ns || {};
                            ns.doAddGoodBad = function(commentId, isGood, obj) {
                            	$.ajax({
                        			url : "/leaveMessage/doAddGoodBad",
                        			type : 'post',
                        	        dataType : 'json',
                        			data : {commentId:commentId,isGood:isGood},
                        			success : function(data) {
                        				if (isGood) {
                        					$(obj).find('.comment-good-bad').text(data.value);
                        				} else {
                        					$(obj).find('.comment-good-bad').text(data.value);
                        				}
                        			}
                        		});
                            };
                            ns.checkInput = function() {
                            	$('#senderName,#senderEmail,#content').css('border-color','#81A74C');
                            	if ($('#senderName').val() == '') {
                            		$('#senderName').css('border-color','red');
                            		$('#senderName').focus();
                            		return false;
                            	}
                            	if ($('#senderEmail').val() == '') {
                            		$('#senderEmail').css('border-color','red');
                            		$('#senderEmail').focus();
                            		return false;
                            	}
                            	if ($('#content').val() == '') {
                            		$('#content').css('border-color','red');
                            		$('#content').focus();
                            		return false;
                            	}
                            	return true;
                            };
                            ns.doSave = function(btnId) {
                            	$(btnId).attr('disabled', true);
                            	if (!ns.checkInput()) {
                            		$(btnId).attr('disabled', false);
                            		return;
                            	}
                            	$.ajax({
                        			url : "/leaveMessage/doSave",
                        			type : 'post',
                        	        dataType : 'json',
                        			data : $("#comment-form").serialize(),
                        			success : function(data) {
                           				alert(data.message);
                        				if (data.code == 0) {
                        					location.href = '/contact';
                        				}
                        				$(btnId).attr('disabled', false);
                        			}
                        		});
                            };
                            $('#save').click(function() {
                            	ns.doSave('#save');
                            });
                            var num =Math.round(Math.random()*(3-1))+1;
                            $('#avatar').val('/assets/images/'+num+'.jpg');
                            </script>
                        </div>
                        <div class="span4"></div>
                    </div>
                </div>

            </section>

        </div>