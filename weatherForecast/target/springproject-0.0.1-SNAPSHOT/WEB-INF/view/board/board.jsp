<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h2>예제 테이블</h2>
		<table>
        	<thead>
	            <tr>
					<th class="text-center" width="20%" data-priority="1">번호</th>
					<th class="text-center" width="*" data-priority="2">제목</th>
	            </tr>
            </thead>
            <tbody>
	            <tr>
					<td>${BoardVO.ot_ttl}</td>
					<td>${BoardVO.ot_cntt}</td>
				</tr>
            </tbody>
        </table>
	</body>
</html>
