
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

function setEditor(holder){
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: holder,
		sSkinURI: "/thejoun/smarteditor/SmartEditor2Skin.html",	
		htParams : {
			bUseToolbar : true,				
			bUseVerticalResizer : true,		
			bUseModeChanger : true,			
			fOnBeforeUnload : function(){
			}
		}, 
		fOnAppLoad : function(){
		},
		fCreator: "createSEditor2"
	});
	
	return oEditors;
}

$(function(){
	$('li').click(function(){
		$('li').removeClass()
			$(this).addClass('on')
	})
	
})