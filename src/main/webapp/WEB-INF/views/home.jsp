<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("contextName", request.getServletContext().getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<center><h1 class="white">Welcome KK</h1></center>
	<div class="col-md12">
		<div id="demo" class="carousel slide col-md-8" style="margin:auto;" data-ride="carousel">
	
	  <!-- Indicators -->
	  <ul class="carousel-indicators">
	    <li data-target="#demo" data-slide-to="0" class="active"></li>
	    <li data-target="#demo" data-slide-to="1"></li>
	    <li data-target="#demo" data-slide-to="2"></li>
	  </ul>
	
	  <!-- The slideshow -->
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="${contextName}/assets/img/re4.jpg" alt="Los Angeles">
	    </div>
	    <div class="carousel-item">
	      <img src="${contextName}/assets/img/re6_1.jpg" alt="Chicago">
	    </div>
	    <div class="carousel-item">
	      <img src="${contextName}/assets/img/jorok.jpg" alt="New York">
	    </div>
	  </div>
	
	  <!-- Left and right controls -->
	  <a class="carousel-control-prev" href="#demo" data-slide="prev">
	    <span class="carousel-control-prev-icon"></span>
	  </a>
	  <a class="carousel-control-next" href="#demo" data-slide="next">
	    <span class="carousel-control-next-icon"></span>
	  </a>
	
	</div>
</div>
</body>
</html>