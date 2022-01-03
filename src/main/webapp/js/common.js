
$(function(){
	$(".depth2").hide();
	$(".depth1 > li").mouseover(function(){
		$(this).find(".depth2").stop().slideDown(500);
		//$(this).find(".depth2").show();
	}).mouseleave(function(){
		$(this).find(".depth2").stop().slideUp(500);
		//$(this).find(".depth2").hide();
	});
});
 