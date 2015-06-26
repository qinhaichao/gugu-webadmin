var pop_success = "";
var pop_error = "";
var pop_html = "";
window.onerror = function(){
	hiddenloading();
}
pop_html   =	$('<div class="col-md-3">'+                            
				    '<div class="block">'+                                
				        '<div class="block-title">'+
				            '<h2><strong>Success</strong> Alert</h2>'+
				        '</div>'+
				        '<div class="alert alert-success alert-dismissable">'+
				            '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'+
				            '<h4><i class="fa fa-check-circle"></i> Success</h4> Settings <a href="javascript:void(0)" class="alert-link">updated</a>!'+
				        '</div>'+
				    '</div>'+
				'</div>')


function showPop (msg,num) {//msg:提示信息；num：error_code
	var poptype = "";
	if (num == 0) { //当error_code为0是 弹出成功提示
		poptype = "success";
		popmsg = "Success";	
		setTimeout(function(){
			closePop();
		},1500)	
	}else{ //否则弹出失败提示
		poptype = "danger";
		popmsg = "Error";
	};
	var pop_html = "";
	pop_html   =	$('<div class="col-md-3 pop_box">'+                            
					    '<div class="block">'+                                
					        '<div class="block-title">'+
					            '<h2><strong>'+popmsg+'</strong> Alert</h2>'+
					            '<button type="button" class="close_pop" data-dismiss="alert" aria-hidden="true" onclick="closePop()">×</button>'+
					        '</div>'+
					        '<div class="alert alert-'+poptype+' alert-dismissable">'+					            
					            '<h4><i class="fa fa-check-circle"></i> '+popmsg+'</h4>  <a href="javascript:void(0)" class="alert-link">'+msg+'</a>!'+
					        '</div>'+
					    '</div>'+
					'</div>')
	$("body").append(pop_html);
	pop_html.slideDown(400);
}
function closePop (){
	$(".pop_box").slideUp(400,function(){
		$(this).remove();
	});
}

function showloading(){
	$(".page-loading-overlay").removeClass('loaded');
}
function hiddenloading(){
	$(".page-loading-overlay").addClass('loaded');
}