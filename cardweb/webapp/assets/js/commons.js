$(function(){$(document).on("click",".ajax-post",function(d){d.preventDefault();var b=$(this).attr("href");var c=$(this).attr("data-confirm");if(c){var a=confirm(c);if(!a){return false}}$.post(b,{},function(f,e,g){handleAjax(f)})});$(document).on("submit","form.ajax-form",function(f){f.preventDefault();if(typeof checkform=="function"){if(checkform()==false){return false}}var b=$(this);$("[type=submit]",b).addClass("disabled").attr("disabled","true");var d=$(this).attr("action");var g=$(this).attr("method");if(!d){return false}if(!g){g="get"}var c=$(this).serialize();var a;if(g=="post"){a=$.post}else{a=$.get}a(d,c,function(e){handleAjax(e);$("[type=submit]",b).removeClass("disabled").removeAttr("disabled")},"json");return false})});function uaredirect(i){try{if(document.getElementById("bdmark")!=null){return}var g=false;if(arguments[1]){var j=window.location.host;var h=window.location.href;if(isSubdomain(arguments[1],j)==1){i=i+"/#m/"+h;g=true}else{if(isSubdomain(arguments[1],j)==2){i=i+"/#m/"+h;g=true}else{i=h;g=false}}}else{g=true}if(g){var l=window.location.hash;if(!l.match("fromapp")){if((navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i))){location.replace(i)}}}}catch(k){}}function isSubdomain(h,g){this.getdomain=function(c){var d=c.indexOf("://");if(d>0){var a=c.substr(d+3)}else{var a=c}var b=/^www\./;if(b.test(a)){a=a.substr(4)}return a};if(h==g){return 1}else{var h=this.getdomain(h);var e=this.getdomain(g);if(h==e){return 1}else{h=h.replace(".","\\.");var f=new RegExp("\\."+h+"$");if(e.match(f)){return 2}else{return 0}}}}function op_success(b,a){toastr.options={"debug":false,"positionClass":"toast-center","onclick":null,"showDuration":"1000","hideDuration":"1000","timeOut":"2000","extendedTimeOut":"1000","showEasing":"swing","hideEasing":"linear","showMethod":"fadeIn","hideMethod":"fadeOut"};toastr.success(b,a)}function op_error(b,a){toastr.options={"debug":false,"positionClass":"toast-center","onclick":null,"showDuration":"1000","hideDuration":"1000","timeOut":"2000","extendedTimeOut":"1000","showEasing":"swing","hideEasing":"linear","showMethod":"fadeIn","hideMethod":"fadeOut"};toastr.error(b,a)}function handleAjax(b){if(b.url){b.info+="锛岄〉闈㈠嵆灏嗚烦杞綖"}if(b.status==1){if(b.msg){op_success(b.msg,"娓╅Θ鎻愮ず")}if(b.html){$("#mes_con").prepend(b.html).hide().fadeIn("slow")}}else{op_error(b.msg,"娓╅Θ鎻愮ず")}var c=1500;if(b.url=="refresh"){setTimeout(function(){location.href=location.href},c)}else{if(b.url){setTimeout(function(){location.href=b.url},c)}}};