/**
 * 로딩 화면 보여주기 위한 JS
 */

var loading;
<!--로딩 정보-->
function start(){
	loading=setInterval(timer,2500);
}

function timer(){
	$('#loading').addClass('end');
}
function stop(){
	setTimeout(function(){
		clearInterval(loading);
		},3000);
}