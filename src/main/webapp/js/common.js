
    // html dom 이 다 로딩된 후 실행된다.
    $(document).ready(function(){
        // memu 클래스 바로 하위에 있는 a 태그를 클릭했을때
        $(".collapse navbar-collapse > li").click(function(){
            // 현재 클릭한 태그가 a 이기 때문에
            // a 옆의 태그중 ul 태그에 hide 클래스 태그를 넣던지 빼던지 한다.
            $(this).next("ul").toggleClass("depth2");
        });
    });




/*
$(function(){
	$(".navbar-nav mx-auto > li").mouseover(function(){
		$(this).find(".depth2").stop().slideDown();
	}).mouseleave(function(){
		$(this).find(".depth2").stop().slideup();
	});
});


*/