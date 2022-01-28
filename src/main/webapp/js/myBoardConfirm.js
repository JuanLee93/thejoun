$(function(){
		$(".board_title").click(function() {
        $(".board_title").removeClass("on"); // board_title클래스 선택자에 on클래스 제거
        $(this).addClass("on"); // 클릭한 선택자에 on클래스 추가
        console.log($(this).text());
        if ($(this).text() == '자유게시판') {
            $("#board_data").hide();
            $("#board_notice").show();
        } else {
            $("#board_data").show();
            $("#board_notice").hide();
        }
    });
});