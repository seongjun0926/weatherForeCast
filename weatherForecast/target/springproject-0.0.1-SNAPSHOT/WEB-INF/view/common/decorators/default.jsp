<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<title><decorator:title default="springproject" /></title>
	
	<link href="/favicon.ico?" rel="shortcut icon" type="image/x-icon" />
	
	<!-- Bootstrap Core CSS -->
    <link href="/dist/css/bootstrap.css" rel="stylesheet">
   	
	<decorator:head/>
</head>


<body onload="" onunload="">
    <div>
        <div>
            <div>
                <%--<page:apply-decorator name="panel" page="/top.do" /> --%>
                <jsp:include page="/WEB-INF/view/common/include/Top.jsp" />
            </div>        
            <div>
                <div>
                   <%--<page:apply-decorator name="panel" page="/left.do" />  --%>
                   <jsp:include page="/WEB-INF/view/common/include/Left.jsp" />
                </div>
				<decorator:body />		
            </div>
         </div>
         <div>
			<jsp:include page="/WEB-INF/view/common/include/Footer.jsp" />
        </div>
    </div>
</body>
</html>
