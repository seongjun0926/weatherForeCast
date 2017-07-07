function moveImg(){
	var x1=220;
	var y1=780;
	var x2=220;
	var y2=940;
	var img=$('#easterEggSub1');
	var img2=$('#easterEggSub2');
	x1=x1+parseInt(Math.random()*30)+1;
	y1=y1+parseInt(Math.random()*30)+1;
	x2=x2+parseInt(Math.random()*30)+1;
	y2=y2+parseInt(Math.random()*30)+1;
	img.animate({
		top: x1+"px",
		left: y1+"px"
	},1000);

	img2.animate({
		top: x2+"px",
		left: y2+"px"
	},1000);
}
function resetImg(){
	var x1=220;
	var y1=630;
	var x2=220;
	var y2=800;
	var img=$('#easterEggSub1');
	var img2=$('#easterEggSub2');
	img.animate({
		top: x1+"px",
		left: y1+"px"
	},1000);

	img2.animate({
		top: x2+"px",
		left: y2+"px"
	},1000);
}