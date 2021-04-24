// window.times = [0.7, 0.8, 0.9];
// window.widths = [695, 700, 705];
// window.count = 0;

// function imagAnimation(selector) {
// 	var time = times[count++];
// 	count = count < 3 ? count : count - 3;
// 	console.log(count);
// 	console.log(time);
// 	$("." + selector).css("opacity", time);
// }

$(function() {
	$('#fullpage').fullpage({
		"navigation": true, //是否显示项目导航
		"loopBottom": true
	}); //fullpage接收的是对象，json对象,具体可以查看文档；
	//我们用jquery来做一点动画看看.
	// setInterval(function() {
	// 	var time = times[count];
	// 	//这里有个逻辑不太明白.
	// 	console.log(time);
	// 	count++;
	// 	count = count < 3 ? count : count - 3;
	// 	$(".bgc-image").css("opacity", time);
	// }, 3000);
	
	$(".tiaozhuan").click(function () {
		var num = $(this).attr("num");
		// console.log(num.toInteger());
		$.fn.fullpage.moveTo(num);
		return false;
	});
});
