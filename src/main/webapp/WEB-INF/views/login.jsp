<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("contextName", request.getServletContext().getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body style="background:url('${contextName}/assets/img/einstein.jpg');">
<div class="col-md-3" style="margin:auto;margin-top:10%;">
<div class="card">
	<div class="card-header bg-success text-white">
		<b>LOGIN</b>
	</div>
	<div class="card-body">
	<form action="${contextName}/j_spring_security_check" method="POST">
		<table>
			<tr>
				<td>User ID &nbsp;</td>
				<td><input type="text" name="username" class="form-control"></td>
			</tr>
			<tr>
				<td>User Password &nbsp;</td>
				<td><input type="password" name="password" class="form-control"></td>
			</tr>
			<tr>
			
			<td colspan="2"><button type="submit" value="Sign-in" class="btn btn-info" style="float:right">Sign-In</button></td>
		</tr>
		</table>
	</form>
	</div>
	<div class="card-footer">
		
	</div>
</div>
</div>
</body>
</html>