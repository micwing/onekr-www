<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/includes.jsp" %>
        <div id="wrap">

            <header data-stellar-background-ratio="0">
                <div class="container home" style="min-height: 550px">

                    <div class="container form-comments">
            		<div class="row">
            			<div class="span1 center">
                        </div>
            			<div class="span10 center">
                            <input type="text" id="searchInput" style="margin-top: 200px;width: 400px;" class="input-xlarge" placeholder="文章/域名"/>
                            <button style="margin-top: 200px;width: 20px" class="btn btn-landing" id="searchBtn" onclick="">搜一刻</button>
		                    <script>
							$('#searchInput').keyup(function(event) {
								 if(event.keyCode == 13){
							         $('#searchBtn').trigger('click');
							     }
							});
							$('#searchBtn').click(function() {
								var v = $('#searchInput').val();
								if (/[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/.test(v)) {
									location.href='/domain/whois?domain='+v;
								} else {									
									location.href='/article/search/'+$('#searchInput').val();
								}
							});
							</script>
					
					
                        </div>
                        <div class="span1 center">
                        </div>
            		</div>
            	</div>

                </div>

            </header>
            
            <section class="index-description">

                <div class="container">
                    <div class="row">
                        <div class="span12 center">
                            <h1>欢迎来到一刻工作室<br>Hi，我是迈克，很高兴能认识你！</h1>
                            <h4 class="sub-heading">Welcome to oneKr.com</h4>
                        </div>
                    </div>

                </div>

            </section>


        </div>