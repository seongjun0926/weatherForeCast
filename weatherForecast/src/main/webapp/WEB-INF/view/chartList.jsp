<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<html>
	<head>
<!-- webjar -->
<script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>
<link rel="stylesheet"
	href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
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
<script type="text/javascript">
		
	$(document).ready(function(){
		<!-- 주간 오전 날씨 분석 -->
		var weatherAm = $("#pie-weatherAm");

		var dataIOS = [],series = "${fn:length(am)}";
		<c:forEach var="amList" items="${am}" varStatus="status">
			dataIOS["${status.index}"]={
									label:"${amList.weatherType}",
									data:"${amList.weatherCount}"
								  }
		</c:forEach>
			
		$.plot(weatherAm, dataIOS, {
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

		var dataIOS = [],series = "${fn:length(pm)}";
		<c:forEach var="pmList" items="${pm}" varStatus="status">
			dataIOS["${status.index}"]={
									label:"${pmList.weatherType}",
									data:"${pmList.weatherCount}"
								  }
		</c:forEach>
			
		$.plot(weatherPm, dataIOS, {
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
	
		var dataIOS = [],series = "${fn:length(max)}";
		<c:forEach var="maxList" items="${max}" varStatus="status">
			dataIOS["${status.index}"]={
									label:"${maxList.temperatureType}",
									data:"${maxList.temperatureCount}"
								  }
		</c:forEach>
			
		$.plot(temperatureMax, dataIOS, {
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
		
		var dataIOS = [],series = "${fn:length(min)}";
		<c:forEach var="minList" items="${min}" varStatus="status">
			dataIOS["${status.index}"]={
									label:"${minList.temperatureType}",
									data:"${minList.temperatureCount}"
								  }
		</c:forEach>
			
		$.plot(temperatureMin, dataIOS, {
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
	});
</script>
	</head> 
	<body>
		<div class="container">
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
	</body>
</html>
