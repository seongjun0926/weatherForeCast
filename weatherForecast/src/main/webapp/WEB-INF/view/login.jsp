<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="/dist/css/EasterEgg.css">
<script language="javascript" type="text/javascript" src="/dist/js/EasterEgg.js"></script>
<!-- webjar -->
<script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
<script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script>
<script>
		$(document).ready(function(){
			$('#easterEggMain').on('click',function(){
				moveImg();				
			});
			$('#easterEggSub1').on('click',function(){
				resetImg();
			});
			$('#easterEggSub2').on('click',function(){
				resetImg();
			});
		});
	</script>
</head>
<body>
	<div class=container align="center" style="width:100%">
		<img src="/dist/images/Easter_Egg1.png" alt="피까피까" id="easterEggMain">
		<img src="/dist/images/Easter_Egg2.png" alt="눈" id="easterEggSub1" class="easterEggSub1">
		<img src="/dist/images/Easter_Egg3.png" alt="눈" id="easterEggSub2" class="easterEggSub2">
		
		
		<form name="f" action="/j_spring_security_check" method="POST">
			<table>
	  			<tbody>
	  				<tr>
	  					<td>User:</td>
	  					<td><input class="form-control" type="text" name="j_username" value=""></td>
	  				</tr>
   				 	<tr>
   				 		<td>Password:</td>
   				 		<td><input class="form-control"  type="password" name="j_password"></td>
   				 	</tr>
    				<tr class="text-right">
    					<td colspan="2"><input class="btn btn-default" name="submit" type="submit" value="Login"></td>
    				</tr>
  				</tbody>
  			</table>
		</form>
	</div>
</body>
</html>