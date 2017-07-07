<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>	
<head>
<meta charset="utf-8">
<meta   data-duration="3s">
<title>Welcome Weather</title>
<!-- webjar -->
<script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
<script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script>
<link href="/dist/css/weather.css" rel="stylesheet">
<link href="/dist/css/metisMenu.css" rel="stylesheet">
<link href="/dist/css/timeline.css" rel="stylesheet">
<link href="/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Flot Charts JavaScript -->
<script src="/dist/flot/excanvas.min.js"></script>
<script src="/dist/flot/jquery.flot.js"></script>
<script src="/dist/flot/jquery.flot.pie.js"></script>
<script src="/dist/flot/jquery.flot.resize.js"></script>
<script src="/dist/flot/jquery.flot.time.js"></script>
<script src="/dist/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>

<!-- Fall -->
<link rel="stylesheet" type="text/css" href="/dist/css/default.css" />
<link rel="stylesheet" type="text/css" href="/dist/css/multilevelmenu.css" />
<link rel="stylesheet" type="text/css" href="/dist/css/component.css" />
<link rel="stylesheet" type="text/css" href="/dist/css/animations.css" />
<link rel="stylesheet" type="text/css" href="/dist/css/loading.css" />
<script src="/dist/js/modernizr.custom.js"></script>

<!-- loading -->
<script src="/dist/js/loading.js"></script>
<script src="/dist/js/flotChart.js"></script>


<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=c25bb867ecc3d0784d753a6bce9c127a"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var areas=[
				<c:forEach var="poligons" items="${poligonList}">
					{name:
						'<div><table class="table table-striped" style="color:#ff019a; font-weight:900">'+
							'<thead><tr><td>${poligons.vlVo.msrsteName}</td></tr></thead>'+
				            '<tbody>'+
								'<tr>'+
									'<td>통합 대기 환경지수</td>'+
									'<td>${poligons.vlVo.maxIndex}</td>'+
								'</tr>'+
								'<tr>'+
									'<td>통합 대기 환경지수 등급</td>'+
									'<td>${poligons.vlVo.grade}</td>'+
								'</tr>'+
								'<tr>'+
									'<td>이산화질소(단위:ppm)</td>'+
									'<td>${poligons.vlVo.nitrogen}</td>'+
								'</tr>'+
								'<tr>'+
									'<td>오존(단위:ppm)</td>'+
									'<td>${poligons.vlVo.ozone}</td>'+
								'</tr>'+
								'<tr>'+
									'<td>일산화탄소(단위:ppm)</td>'+
									'<td>${poligons.vlVo.carbon}</td>'+
								'</tr>'+
								'<tr>'+
									'<td>아황산가스(단위:ppm)</td>'+
									'<td>${poligons.vlVo.sulfurous}</td>'+
								'</tr>'+
								'<tr>'+
									'<td>미세먼지(단위:㎍/㎥)</td>'+
									'<td>${poligons.vlVo.pm10}</td>'+
								'</tr>'+
								'<tr>'+
									'<td>초미세먼지(단위:㎍/㎥)</td>'+
								'<td>${poligons.vlVo.pm25}</td>'+
								'</tr>'+
							'</tbody>'+
						'</table></div>'
						,
					path : [
						<c:forEach var="llVo" items="${poligons.llVo}">
						new daum.maps.LatLng(${llVo.lng},${llVo.lnt}),
						</c:forEach>
						]
					},
				</c:forEach>
		];
		
		<!--loading-->
		start();
		stop();
		<!--위성 사진-->
		var dataImg0=[],dataImg1=[],dataImg2=[],dataImg3=[],dataImg4=[],dataImg5=[];
		<c:forEach var="imageList" items="${weatherImageListType0}" varStatus="status">
		dataImg0["${status.index}"]={img:"${imageList.imgLocation}"}
		</c:forEach>
		<c:forEach var="imageList" items="${weatherImageListType1}" varStatus="status">
			dataImg1["${status.index}"]={img:"${imageList.imgLocation}"}
		</c:forEach>
		<c:forEach var="imageList" items="${weatherImageListType2}" varStatus="status">
			dataImg2["${status.index}"]={img:"${imageList.imgLocation}"}
		</c:forEach>
		<c:forEach var="imageList" items="${weatherImageListType3}" varStatus="status">
			dataImg3["${status.index}"]={img:"${imageList.imgLocation}"}
		</c:forEach>
		<c:forEach var="imageList" items="${weatherImageListType4}" varStatus="status">
			dataImg4["${status.index}"]={img:"${imageList.imgLocation}"}
		</c:forEach>
		<c:forEach var="imageList" items="${weatherImageListType5}" varStatus="status">
			dataImg5["${status.index}"]={img:"${imageList.imgLocation}"}
		</c:forEach>
		setInterval(function(){
			if(numberOne>51)
				numberOne=0;
			numberOne++
			changeImage('#imagePlace1',numberOne,dataImg1)
			},2300);
		setInterval(function(){
			if(number>83)
				number=0;
			number++
			changeImage('#imagePlace0',number,dataImg0)
			changeImage('#imagePlace2',number,dataImg2)
			changeImage('#imagePlace3',number,dataImg3)
			changeImage('#imagePlace4',number,dataImg4)
			changeImage('#imagePlace5',number,dataImg5)
			},1500);
		
		<!-- 주간 오전 날씨 분석 -->
		var weatherAm = $("#pie-weatherAm");

		var dataAm = [],series = "${fn:length(am)}";
		<c:forEach var="amList" items="${am}" varStatus="status">
		dataAm["${status.index}"]={
									label:"${amList.weatherType}",
									data:"${amList.weatherCount}"
								  }
		</c:forEach>
			
		$.plot(weatherAm, dataAm, {
            series: {
                pie: {
                    show: true
                }
            },
            grid: {
                hoverable: true
            },
            tooltip: true,
            tooltipOpts: {
                content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
                shifts: {
                    x: 20,
                    y: 0
                },
                defaultTheme: false
            }
        });

		<!-- 주간 오후 날씨 분석 -->
		var weatherPm = $("#pie-weatherPm");

		var dataPm = [],series = "${fn:length(pm)}";
		<c:forEach var="pmList" items="${pm}" varStatus="status">
		dataPm["${status.index}"]={
									label:"${pmList.weatherType}",
									data:"${pmList.weatherCount}"
								  }
		</c:forEach>
			
		$.plot(weatherPm, dataPm, {
            series: {
                pie: {
                    show: true
                }
            },
            grid: {
                hoverable: true
            },
            tooltip: true,
            tooltipOpts: {
                content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
                shifts: {
                    x: 20,
                    y: 0
                },
                defaultTheme: false
            }
        });
        
	

		<!-- 주간 최고 기온 분석 -->
		var temperatureMax = $("#pie-temperatureMax");
	
		var dataMax = [],series = "${fn:length(max)}";
		<c:forEach var="maxList" items="${max}" varStatus="status">
		dataMax["${status.index}"]={
									label:"${maxList.temperatureType}",
									data:"${maxList.temperatureCount}"
								  }
		</c:forEach>
			
		$.plot(temperatureMax, dataMax, {
	        series: {
	            pie: {
	                show: true
	            }
	        },
	        grid: {
	            hoverable: true
	        },
	        tooltip: true,
	        tooltipOpts: {
	            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
	            shifts: {
	                x: 20,
	                y: 0
	            },
	            defaultTheme: false
	        }
	    });

		<!-- 주간 최저 기온 분석 -->
		var temperatureMin = $("#pie-temperatureMin");
		
		var dataMin = [],series = "${fn:length(min)}";
		<c:forEach var="minList" items="${min}" varStatus="status">
		dataMin["${status.index}"]={
									label:"${minList.temperatureType}",
									data:"${minList.temperatureCount}"
								  }
		</c:forEach>
			
		$.plot(temperatureMin, dataMin, {
		    series: {
		        pie: {
		            show: true
		        }
		    },
		    grid: {
		        hoverable: true
		    },
		    tooltip: true,
		    tooltipOpts: {
		        content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
		        shifts: {
		            x: 20,
		            y: 0
		        },
		        defaultTheme: false
		    }
		});
		
		$('#bottomUp').on('click',function(){
			$( "#bottomDiv" ).animate({height: "100%"}, 500);
			$('ul.forecast').css('max-height', '100%');
			
		});
		$(".go-go-down").on('click',function(){
			$( "#bottomDiv" ).animate({height: "155px"}, 500);
			$('ul.forecast').css('max-height', '155px');
		});

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 8 // 지도의 확대 레벨
	    };

	var map = new daum.maps.Map(mapContainer, mapOption),
	    customOverlay = new daum.maps.CustomOverlay({}),
	    infowindow = new daum.maps.InfoWindow({removable: true});

	// 지도에 영역데이터를 폴리곤으로 표시합니다 
	for (var i = 0, len = areas.length; i < len; i++) {
	    displayArea(areas[i]);
	}

	// 다각형을 생상하고 이벤트를 등록하는 함수입니다
	function displayArea(area) {

	    // 다각형을 생성합니다 
	    var polygon = new daum.maps.Polygon({
	        map: map, // 다각형을 표시할 지도 객체
	        path: area.path,
	        strokeWeight: 2,
	        strokeColor: '#004c80',
	        strokeOpacity: 0.8,
	        fillColor: '#fff',
	        fillOpacity: 0.7 
	    });

	    // 다각형에 mouseover 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 변경합니다 
	    // 지역명을 표시하는 커스텀오버레이를 지도위에 표시합니다
	    daum.maps.event.addListener(polygon, 'mouseover', function(mouseEvent) {
	        polygon.setOptions({fillColor: '#09f'});

	        customOverlay.setContent('<div class="area">' + area.name + '</div>');
	        
	        customOverlay.setPosition(mouseEvent.latLng); 
	        customOverlay.setMap(map);
	    });

	    // 다각형에 mousemove 이벤트를 등록하고 이벤트가 발생하면 커스텀 오버레이의 위치를 변경합니다 
	    daum.maps.event.addListener(polygon, 'mousemove', function(mouseEvent) {
	        
	        customOverlay.setPosition(mouseEvent.latLng); 
	    });

	    // 다각형에 mouseout 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 원래색으로 변경합니다
	    // 커스텀 오버레이를 지도에서 제거합니다 
	    daum.maps.event.addListener(polygon, 'mouseout', function() {
	        polygon.setOptions({fillColor: '#fff'});
	        customOverlay.setMap(null);
	    }); 

	    // 다각형에 click 이벤트를 등록하고 이벤트가 발생하면 다각형의 이름과 면적을 인포윈도우에 표시합니다 
	    daum.maps.event.addListener(polygon, 'click', function(mouseEvent) {
	        var content = '<div class="info">' + 
	                    '   <div class="title">' + area.name + '</div>' +
	                    '   <div class="size">총 면적 : 약 ' + Math.floor(polygon.getArea()) + ' m<sup>2</sup></area>' +
	                    '</div>';

	        infowindow.setContent(content); 
	        infowindow.setPosition(mouseEvent.latLng); 
	        infowindow.setMap(map);
	    });
	}
	});
</script>

</head>
<body>
<div id="loading" class="loader-moving">
  <span class="left"></span>
  <span class="right"></span>
</div>
     	<div class="pt-triggers">
			<div id="dl-menu" class="dl-menuwrapper">
				<ul class="dl-menu dl-menuopen">
							<li data-animation="36"><a href="#">Next</a></li>
				</ul>
			</div><!-- /dl-menu-wrapper-->
		</div><!-- /triggers -->

		<div id="pt-main" class="pt-perspective">
			<!-- 첫 화면 -->
			<div class="pt-page pt-page-1" style="color: #ffffff;">
				<div class="col" style="margin-top: -30px;">
					<div class="weather-card one">
						<div class="top">
							<div class="wrapper go-go-down">
								<h1 class="heading">
									<c:out value="${weatherList.get(0).am}"/>/<c:out value="${weatherList.get(0).pm}"/> 
								</h1>
								<h3 class="location">서울</h3>
								<p class="temp">
									<span class="temp-value">20</span> 
									<span class="deg">0</span> 
									<a href="javascript:;">
										<span class="temp-type">C</span>
									</a>
									<span class="temp-value">/</span>
									<span class="temp-value">
										<c:out value="${temperatureList.get(0).max}"/>
									</span> 
									<span class="deg">0</span> 
									<a href="javascript:;">
										<span class="temp-type">C</span>
									</a>
								</p>
							</div>
							<div class="center">
								<span style="color: #f8efef">
									<c:out value="${weatherDetail.detail}"/>
								</span>
							</div>
						</div>
						<div class="bottom" id="bottomDiv">
							<div class="wrapper">
								<ul class="forecast">
									<a href="#">
										<span id="bottomUp" class="lnr lnr-chevron-up go-up"></span>
									</a>
									<li class="active">
										<span class="date">+1 Day</span>
										<span class="lnr condition"> 
											<div class="row">
												<span class="temp">
													<c:out value="${weatherList.get(1).am}"/>/
													<c:out value="${temperatureList.get(1).min}"/>
													<span class="deg">0</span>
													<span class="temp-type">C</span>
												</span>
											</div>
											<div class="row">
												<span class="temp">
													<c:out value="${weatherList.get(1).pm}"/>/
													<c:out value="${temperatureList.get(1).max}"/>
													<span class="deg">0</span>
													<span class="temp-type">C</span>
												</span>
											</div>
										</span>
									</li>
									<c:forEach var="list" items="${weatherList}" varStatus="status">
										<li>
											<span class="date">+${status.index+2} Day</span>
											<span class="lnr condition"> 
												<div class="row">
													<span class="temp">
														<c:out value="${list.am}"/>/
														<c:out value="${temperatureList.get(status.index).min}"/>
														<span class="deg">0</span>
														<span class="temp-type">C</span>
													</span>
												</div>
												<div class="row">
													<span class="temp">
														<c:out value="${list.pm}"/>/
														<c:out value="${temperatureList.get(status.index).max}"/>
														<span class="deg">0</span>
														<span class="temp-type">C</span>
													</span>
												</div>
											</span>
										</li>	
									</c:forEach>
									
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>	
			<!-- 둘째 화면 -->
			<div class="pt-page pt-page-2" style="color: #ffffff;">
				<br><br><br>
				 <!-- 주간 오전 날씨 -->
			     <div class="col-lg-6">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">Weekly AM Weather Analysis</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
			                     <div id="pie-weatherAm" class="demo-placeholder"
			                           style="width: 450px; height: 250px"></div>
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>
			     
			     	<!-- 주간 오후 날씨 -->
			     <div class="col-lg-6">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">Weekly PM Weather Analysis</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
			                     <div id="pie-weatherPm" class="demo-placeholder"
			                           style="width: 450px; height: 250px"></div>
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>
			
						<!-- 주간 최고 기온 -->
			     <div class="col-lg-6">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">Weekly Max Temperature Analysis</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
			                     <div id="pie-temperatureMax" class="demo-placeholder"
			                           style="width: 450px; height: 250px"></div>
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>
			     
			     	<!-- 주간 오후 기온 -->
			     <div class="col-lg-6">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">Weekly Min Temperature Analysis</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
			                     <div id="pie-temperatureMin" class="demo-placeholder"
			                           style="width: 450px; height: 250px"></div>
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			    </div>
			</div>
			<!-- 셋째 화면 -->
			<div class="pt-page pt-page-3" style="color: #ffffff;">
				<div id="map" style="width:100%;height:90%;top:5%;"></div>
			</div>
			<!-- 넷째 화면 -->
			<div class="pt-page pt-page-4" style="color: #ffffff;">
				<br>
				<div class="col-lg-4">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">적외 영상</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
			                    <img id="imagePlace0" src="" style="width:80%; height:280px">
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>
			     <div class="col-lg-4">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">가시 영상</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
								<img id="imagePlace1" src="" style="width:80%; height:280px">
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>
			     <div class="col-lg-4">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">수증기 영상</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
								<img id="imagePlace2" src="" style="width:80%; height:280px">
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>
			     <div class="col-lg-4">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">단파적외 영상</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
								<img id="imagePlace3" src="" style="width:80%; height:280px">
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>					
				<div class="col-lg-4">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">합성 영상</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
								<img id="imagePlace4" src="" style="width:80%; height:280px">
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>	
				 <div class="col-lg-4">
			           <div class="panel panel-default">
			                <div class="panel-heading">
			                     <label class="text-center">적외강조 영상</label>
			                </div>
			                <!-- /.panel-heading -->
			                <div class="panel-body">
								<img id="imagePlace5" src="" style="width:80%; height:280px">
			                </div>
			                <!-- /.panel-body -->
			           </div>
			           <!-- /.panel -->
			     </div>		
					
					
					
					
			</div>
		</div>
		<script src="/dist/js/jquery.dlmenu.js"></script>
		<script src="/dist/js/pagetransitions.js"></script>

</body>
</html>
