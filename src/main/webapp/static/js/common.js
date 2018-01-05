$(function(){
	var minHeight=$(window).height();
	var bodyHeight=$(document.body).outerHeight(true);
//	console.log(bodyHeight)
	$('.sidebar,.sideSubset,.subject').css({'min-height':minHeight-80})
//	console.log(bodyHeight)
	$(window).resize(function(){
	var minHeight=$(window).height();
	var bodyHeight=$(document.body).outerHeight(true);
//	console.log(bodyHeight)
	$('.sidebar,.sideSubset,.subject').css({'min-height':minHeight-80})
	})
	
	
	if($('.rightCon>div:first').hasClass('subHead')){
		var minHeight=$(window).height();
		$('.subject').css({'min-height':minHeight-220})
		$(window).resize(function(){
		var minHeight=$(window).height();
//		var bodyHeight=$(document.body).outerHeight(true);
	//	console.log(bodyHeight)
		$('.subject').css({'min-height':minHeight-220})
		})
	}
})
