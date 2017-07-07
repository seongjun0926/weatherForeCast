/**
 * 화면에 뿌려질 위성사진을 그리기위해 사용하는 JS
 */
var imgtimer;
var number=0;
var numberOne=0;// 52

<!--image -->
function changeImage(img,num,data){
	var imagePlace = $(img);
	imagePlace.attr('src',data[num].img);
}