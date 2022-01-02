
$(function(){
	$(".navbar-nav mx-auto > li").mouseover(function(){
		$(this).find(".depth2").stop().slideDown();
	}).mouseleave(function(){
		$(this).find(".depth2").stop().slideup();
	});
});
