<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		
		<title>DataTables적용</title>
		
		<!-- Bootstrap Core CSS -->
	    <link href="/dist/css/bootstrap.css" rel="stylesheet">
	    <!-- MetisMenu CSS -->
	    <link href="/dist/css/metisMenu.css" rel="stylesheet">
	    <!-- Timeline CSS -->
	    <link href="/dist/css/timeline.css" rel="stylesheet">
	    <!-- DataTable CSS -->
	    <link href="/dist/css/dataTables.bootstrap.css" rel="stylesheet">
	    <!-- Custom CSS -->
	    <link href="/dist/css/sb-admin-2.css" rel="stylesheet">
	    <!-- Morris Charts CSS -->
	    <link href="/dist/css/morris.css" rel="stylesheet">
	    <!-- Custom Fonts -->
	    <link href="/dist/css/font-awesome.css" rel="stylesheet" type="text/css">
		<!-- Date Picker CSS -->
		<link href="/dist/css/bootstrap-datetimepicker.css" rel="stylesheet" />
		<!-- tabletools -->
		<link href="/dist/css/dataTables.tableTools.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="/dist/css/responsive.bootstrap.css">
		
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
		<!-- jQuery v2.1.4 -->
	    <script src="/dist/js/jquery.js"></script>
	    <!-- Bootstrap Core JavaScript -->
	    <script src="/dist/js/bootstrap.js"></script>
	    <!-- Custom Theme JavaScript -->
	    <!-- DatePicker JavaScript -->
		<script src="/dist/js/moment-ko.js"></script>
		<script src="/dist/js/transition.js"></script>
		<script src="/dist/js/collapse.js"></script>
		<script src="/dist/js/bootstrap-datetimepicker.js"></script>
		<!-- DataTables JavaScript -->
	    <script src="/dist/js/jquery.dataTables.js"></script>
	    <script src="/dist/js/dataTables.bootstrap.js"></script>
	    <script src="/dist/js/dataTables.responsive.js"></script>
		<script src="/dist/js/responsive.bootstrap.js"></script>
		<!-- tabletools -->
		<script src="/dist/js/dataTables.tableTools.js"></script>    
    
		<script  type="text/javascript">
		$(document).ready(function($) {
			$(document).ready(function() {
		        $('#dataTables-ex04').DataTable({
		            responsive: true
		            ,ordering: true
		            ,"bAutoWidth": true
	                ,"columnDefs": [
	                               { "orderable": false, "targets": 0 }
	                             ]
		        });
		    });
		});
		
		function fn_goDetail(seq){
			document.getElementById("ot_seq").value = seq;
			
			var form = document.getElementById("BoardVO");
			form.action = "/boardDetail.do";
			form.submit();
		}
		
		function fn_create(){
		
		}
		</script>
	</head> 
	<body>
		<form:form commandName="BoardVO" method="post" onsubmit="return false;">
		<form:hidden path="ot_seq"/>
		
		<div class="row">
	        <div class="col-lg-12">
	        	<div class="col-lg-8">
	            <div class="panel panel-default">
	                <div class="panel-heading">게시판 목록조회</div>
	                <!-- /.panel-heading -->
	                <div class="panel-body">                	
	                    <div class="dataTable_wrapper">
	                        <table class="table table-striped table-bordered table-hover dt-responsive" id="dataTables-ex04" width="100%" 
	                        	data-order='[[ 0, "asc" ],[ 1, "asc" ]]' data-page-length='10'>
	                            <thead>
	                                <tr>	
	                                	<th class="text-center" width="25%">번호</th>					
	                                    <th class="text-center" width="*">제목</th>
	                                </tr>
	                            </thead>
	                            <tbody>							
					            <c:forEach var="list" items="${boardModelList}" varStatus="status">
						            <tr>
										<td class="text-center" style="vertical-align:middle;">						
												${list.ot_seq}							
										</td>
										<td class="text-center" style="vertical-align:middle;">
											<a href="javascript:fn_goDetail(<c:out value="${list.ot_seq}" />)">
												${list.ot_ttl}
											</a>
										</td>
									</tr>
								</c:forEach>
	                            </tbody>
	                        </table>
	                    </div>
						<button type="button" class="btn btn-primary" onclick="fn_create();">등록</button>
	                </div>
	                <!-- /.panel-body -->
	            </div>
	            <!-- /.panel -->            
	       	 </div>
	        </div>
	        <!-- /.col-lg-12 -->       
	    </div>   
		</form:form>
	</body>
</html>
